package com.syh.uit.relation_group_info.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "ID-WORKER-APP")
public interface IDGeneratorService {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    long getID();
}
