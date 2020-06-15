package com.syh.uit.chat_server.controller;

import com.syh.uit.chat_server.model.request.ChatMessageRequest;
import com.syh.uit.chat_server.model.response.ChatMessageReceipt;
import com.syh.uit.chat_server.service.ChatService;
import com.syh.uit.exception.exception.APIGeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ChatAPIController {
    private ChatService chatService;
    @Autowired
    private void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    private ChatMessageReceipt receiveChatMessage(@RequestBody@Valid ChatMessageRequest chatRequest)throws APIGeneralException {
        return chatService.handleRequest(chatRequest);
    }
}
