package com.syh.uit.friend_apply_service.controller.aop;

import com.syh.uit.friend_apply_service.model.FriendApply;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

public class parseJSONbyAuthAOP {
    @Around("parseJSONbyAuth()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        FriendApply resource = (FriendApply) pjp.proceed(args);
        Object result;
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int requestID = Integer.parseInt(String.valueOf(authentication.getPrincipal()));

        if (requestID == resource.getApplyID()){
            result = new FriendApply_ResponseForApplicant();
        } else if (requestID == resource.getTargetID()) {
            result = new FriendApply_ResponseForTarget();
        }else{
            throw new ResourceNoAuthException("");
        }

        BeanUtils.copyProperties(resource,result);*/
        return resource;
    }
}
