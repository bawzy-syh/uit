package com.syh.uit.relation_group_info.service;

import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.exception.exception.ResourceNoAuthException;
import com.syh.uit.exception.exception.UnprocessableEntityException;
import com.syh.uit.relation_group_info.dao.FriendApplyMapper;
import com.syh.uit.relation_group_info.dao.GroupInfoMapper;
import com.syh.uit.relation_group_info.dao.RelationInfoMapper;
import com.syh.uit.relation_group_info.feign.IDGeneratorService;
import com.syh.uit.relation_group_info.model.GroupInfo;
import com.syh.uit.relation_group_info.model.request.UpdateGroupInfoListRequest;
import com.syh.uit.relation_group_info.model.request.UpdateGroupInfoRequest;
import com.syh.uit.relation_group_info.model.response.GroupInfoList;
import com.syh.uit.relation_group_info.model.response.GroupInfoResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GroupInfoService {
    private final FriendApplyMapper friendApplyMapper;
    private final GroupInfoMapper groupInfoMapper;
    private final RelationInfoMapper relationInfoMapper;
    private final IDGeneratorService idGeneratorService;

    public GroupInfoService(FriendApplyMapper friendApplyMapper, GroupInfoMapper groupInfoMapper, RelationInfoMapper relationInfoMapper, IDGeneratorService idGeneratorService) {
        this.friendApplyMapper = friendApplyMapper;
        this.groupInfoMapper = groupInfoMapper;
        this.relationInfoMapper = relationInfoMapper;
        this.idGeneratorService = idGeneratorService;
    }

    private int getAuth() throws ResourceNoAuthException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try{
            return Integer.parseInt(String.valueOf(authentication.getPrincipal()));
        }catch (NumberFormatException|NullPointerException e){
            throw new ResourceNoAuthException("Bad Auth");
        }
    }

    public GroupInfoList getSelfGroupList() throws APIGeneralException {
        List<GroupInfo> stored = groupInfoMapper.getGroupInfoByUID(getAuth());
        List<GroupInfo> responseList = new ArrayList<>();
        for (GroupInfo temp: stored){
            responseList.add(new GroupInfoResponse(temp));
        }
        return new GroupInfoList(responseList);
    }

    public void updateFullGroupList(UpdateGroupInfoListRequest request) throws APIGeneralException {
        List<UpdateGroupInfoRequest> requestGroups = request.getGroups();//请求的list
        List<GroupInfo> stored = groupInfoMapper.getGroupInfoByUID(getAuth());//储存的list
        List<GroupInfo> processed = new ArrayList<>();//处理后的list
        int uid = getAuth();
        //检查删除group的合法性
        List<Long> deleteGroupIDs = getDeleteGroupIDs(stored,requestGroups);
        for (long id: deleteGroupIDs){
            checkGroupUsage(id);
        }
        //检查groupID合法性,是否属于本人
        checkGroupIDAuth(stored,requestGroups);
        //防止错误,对请求中的order重排序编号,升序
        Collections.sort(requestGroups);
        //生成新的待储存GroupInfo
        int count = 0;
        for (UpdateGroupInfoRequest temp: requestGroups){
            if (temp.getGroupID()==null){
                processed.add(new GroupInfo(idGeneratorService.getID(),temp.getTitle(),uid,count));
            }else{
                processed.add(new GroupInfo(temp.getGroupID(),temp.getTitle(),uid,count));
            }
            count++;
        }
        groupInfoMapper.updateGroupList(processed);
        if (!deleteGroupIDs.isEmpty()){
            groupInfoMapper.deleteGroupList(deleteGroupIDs);
        }

    }

    /**
     * 判断是否对请求中的group有权限操作
     * @param stored 用户自己的group列表
     * @param requestList 此次更新请求的group列表
     * @throws ResourceNoAuthException 访问的Group不属于自己时抛出
     */
    private void checkGroupIDAuth(List<GroupInfo> stored, List<UpdateGroupInfoRequest> requestList) throws ResourceNoAuthException {
        for (UpdateGroupInfoRequest reqItem: requestList){
            if (reqItem.getGroupID()==null)continue;
            boolean isFound = false;
            for (GroupInfo storeItem: stored){
                if (storeItem.getGroupID().equals(reqItem.getGroupID())){
                    isFound = true;
                    break;
                }
            }
            if (!isFound)throw new ResourceNoAuthException("No Auth to Operate "+reqItem.getGroupID());
        }
    }

    /**
     * 找出要删除的group的id
     * @param stored 用户自己的group列表
     * @param requestList 此次更新请求的group列表
     * @return 要删除的Group的ID列表
     */
    private List<Long> getDeleteGroupIDs(List<GroupInfo> stored, List<UpdateGroupInfoRequest> requestList) {
        List<Long> result = new ArrayList<>();
        for (GroupInfo storeItem: stored){
            boolean isFound = false;
            for (UpdateGroupInfoRequest reqItem: requestList){
                if (storeItem.getGroupID().equals(reqItem.getGroupID())){
                    isFound=true;
                    break;
                }
            }
            if (isFound)continue;
            result.add(storeItem.getGroupID());
        }
        return result;
    }

    /**
     * 查询Group是否被其他服务使用,如好友请求和好友关系
     * @param groupID 待查询groupID
     * @throws UnprocessableEntityException 正在使用时抛出此异常
     */
    private void checkGroupUsage(Long groupID) throws UnprocessableEntityException{
        if (friendApplyMapper.getGroupUsageCount(groupID)!=0){
            throw new UnprocessableEntityException("this group is use in Friend Apply");
        }
        if (relationInfoMapper.getGroupUsageCount(groupID)!=0){
            throw new UnprocessableEntityException("this group is not empty");
        }
    }
}
