package com.syh.uit.user_connection_center.feign;

import com.syh.uit.user_connection_center.module.PushOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("push-service")
public interface PushServiceClient {
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    void pushNow(@RequestBody PushOrder order);
}
