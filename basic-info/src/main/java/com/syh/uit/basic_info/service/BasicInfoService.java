package com.syh.uit.basic_info.service;

import com.syh.uit.basic_info.dao.BasicInfoMapper;
import com.syh.uit.basic_info.model.BasicInfo;
import com.syh.uit.basic_info.model.request.UpdateBasicInfoRequest;
import com.syh.uit.basic_info.model.response.BasicInfoResponse;
import com.syh.uit.basic_info.model.response.SelfBasicInfoResponse;
import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.exception.exception.BadRequestException;
import com.syh.uit.exception.exception.ResourceNoAuthException;
import com.syh.uit.exception.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BasicInfoService {
    private BasicInfoMapper basicInfoMapper;
    private RelationQueryService relationQueryService;
    @Autowired
    private void setBasicInfoMapper(BasicInfoMapper basicInfoMapper) {
        this.basicInfoMapper = basicInfoMapper;
    }
    @Autowired
    public void setRelationQueryService(RelationQueryService relationQueryService) {
        this.relationQueryService = relationQueryService;
    }

    private int getAuth() throws ResourceNoAuthException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try{
            return Integer.parseInt(String.valueOf(authentication.getPrincipal()));
        }catch (NumberFormatException|NullPointerException e){
            throw new ResourceNoAuthException("Bad Auth");
        }

    }

    public BasicInfo getSelfBasisInfo()throws APIGeneralException {
        BasicInfo stored = basicInfoMapper.getBasicInfoByUID(getAuth());
        return new SelfBasicInfoResponse(stored);
    }
    public BasicInfo getBasicInfoById(int uid)throws APIGeneralException{
        BasicInfo stored = basicInfoMapper.getBasicInfoByUID(uid);
        if (stored==null){
            throw new ResourceNotFoundException(uid+" not found");
        }
        BasicInfoResponse response = new BasicInfoResponse(stored);
        try {
            boolean isFriend = relationQueryService.hasRelation(getAuth(),uid);
            response.setIsFriend(isFriend ? BasicInfoResponse.FriendStatus.TRUE.getCode() :
                    BasicInfoResponse.FriendStatus.FALSE.getCode());
        }catch (Exception e){
            response.setIsFriend(BasicInfoResponse.FriendStatus.UNKNOWN.getCode());
        }
        return response;
    }
    public void updateBasicInfo(UpdateBasicInfoRequest request) throws APIGeneralException{
        request.setUid(getAuth());
        BasicInfo stored = basicInfoMapper.getBasicInfoByUID(request.getUid());
        if (stored==null){
            throw new ResourceNotFoundException(request.getUid()+"not found");
        }
        //todo:所有更改的内容需要认证
        stored.setPetName(request.getPetName());
        stored.setSign(request.getSign());
        if (request.getGender()!=null){
            try{
                BasicInfo.Genders genders = BasicInfo.Genders.getByCode(request.getGender());
                stored.setGender(genders.getCode());
            }catch (IllegalArgumentException e){
                throw new BadRequestException(request.getGender()+" is illegal");
            }
        }
        stored.setIconPath(request.getIconPath());
        stored.setBirthday(request.getBirthday());
        basicInfoMapper.updateBasicInfo(stored);
    }
}
