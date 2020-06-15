package com.syh.uit.chat_server.service;

import com.syh.uit.chat_server.dao.ChatMessageMapper;
import com.syh.uit.chat_server.model.ChatMessage;
import com.syh.uit.chat_server.model.RelationInfo;
import com.syh.uit.chat_server.model.request.ChatMessageRequest;
import com.syh.uit.chat_server.model.response.ChatMessageReceipt;
import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.exception.exception.ResourceNoAuthException;
import com.syh.uit.exception.exception.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private ChatMessageMapper chatMessageMapper;
    private MessageIDService messageIDService;
    private RelationCheckService relationCheckService;
    @Autowired
    private void setChatMessageMapper(ChatMessageMapper chatMessageMapper) {
        this.chatMessageMapper = chatMessageMapper;
    }
    @Autowired
    private void setMessageIDService(MessageIDService messageIDService) {
        this.messageIDService = messageIDService;
    }
    @Autowired
    private void setRelationCheckService(RelationCheckService relationCheckService) {
        this.relationCheckService = relationCheckService;
    }

    private int getAuth() throws ResourceNoAuthException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try{
            return Integer.parseInt(String.valueOf(authentication.getPrincipal()));
        }catch (NumberFormatException|NullPointerException e){
            throw new ResourceNoAuthException("Bad Auth");
        }

    }

    public ChatMessageReceipt handleRequest(ChatMessageRequest request)throws APIGeneralException {
        RelationInfo relationInfo = relationCheckService.getRelationInfo(request.getRelationID());
        if (relationInfo==null)throw new UnprocessableEntityException("relationID "+request.getRelationID()+" not found");
        if (relationInfo.getUid()!=getAuth()){
            throw new ResourceNoAuthException("No Auth to send");
        }
        long messageID = messageIDService.getNewID();
        long timestamp = System.currentTimeMillis()/1000;
        ChatMessage currentMessage = new ChatMessage(messageID,request.getRelationID(),request.getContent(),timestamp);
        //send
        chatMessageMapper.insertChatMessage(currentMessage);
        return new ChatMessageReceipt(messageID,timestamp);
    }
}
