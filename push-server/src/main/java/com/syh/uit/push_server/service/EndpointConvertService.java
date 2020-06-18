package com.syh.uit.push_server.service;

import com.syh.uit.push_server.config.user_state.UserOnlineStateProperties;
import com.syh.uit.push_server.model.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EndpointConvertService {
    private UserOnlineStateProperties userOnlineStateProperties;
    @Autowired
    private void setUserOnlineStateProperties(UserOnlineStateProperties userOnlineStateProperties) {
        this.userOnlineStateProperties = userOnlineStateProperties;
    }

    Endpoint convertByName(String name) throws IllegalArgumentException {
        for (Endpoint endpoint: userOnlineStateProperties.getEndpoints()){
            if (endpoint.getName().toLowerCase().equals(name.toLowerCase())){
                return endpoint;
            }
        }
        throw new IllegalArgumentException(name+"can not convert to Endpoint");
        //throw new BadRequestException(name+"can not convert to Endpoint");
    }

    List<Endpoint> convertByNameList(List<String> endpointNames) throws IllegalArgumentException {
        List<Endpoint> result = new ArrayList<>();
        for (String name : endpointNames){
            name = name.toLowerCase();
            if (name.equals("all")){
                return new ArrayList<>(userOnlineStateProperties.getEndpoints());
            }
            result.add(convertByName(name));
        }
        return result;
    }
}
