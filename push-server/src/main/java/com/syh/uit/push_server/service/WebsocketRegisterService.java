package com.syh.uit.push_server.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.syh.uit.exception.exception.*;
import com.syh.uit.push_server.controller.PushConnectionController;
import com.syh.uit.push_server.model.ConnectionInfo;
import com.syh.uit.push_server.model.Endpoint;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class WebsocketRegisterService {
    private EndpointConvertService endpointConvertService;
    private OnlineStateManageService onlineStateManageService;
    @Autowired
    private void setEndpointConvertService(EndpointConvertService endpointConvertService) {
        this.endpointConvertService = endpointConvertService;
    }
    @Autowired
    private void setOnlineStateManageService(OnlineStateManageService onlineStateManageService) {
        this.onlineStateManageService = onlineStateManageService;
    }

    private static final ConcurrentHashMap<String, PushConnectionController> webSocketSet = new ConcurrentHashMap<>();

    /**
     * 向服务注册控制器,仅应由新建的控制器调用
     * @param controller PushController
     * @param endpointName name of endpoint
     * @param bearer connection initial auth
     */
    public void register(PushConnectionController controller, String endpointName, String bearer){
        try{
            //解析jwt
            DecodedJWT jwt = verify(bearer);
            int uid = jwt.getClaim("user_name").asInt();
            long exp = jwt.getClaim("exp").asLong();
            Endpoint endpoint = getEndpoint(endpointName);
            //为连接设置信息
            ConnectionInfo connectionInfo = new ConnectionInfo(uid,endpoint);
            controller.setInfo(connectionInfo);
            controller.setExp(exp);
            //连接加入至管理
            PushConnectionController former = webSocketSet.put(connectionInfo.toString(),controller);
            if (former!=null){
                former.handleError(new ResourceNoAuthException("login somewhere else"));
                former.closeConnect();
            }
            //数据库中标为在线
            onlineStateManageService.onLine(uid,endpoint);
        }catch (APIGeneralException e){
            controller.handleError(e);
            controller.closeConnect();
        }catch (Exception e){
            LoggerFactory.getLogger(WebsocketRegisterService.class).error(e.getMessage());
            controller.handleError(new InternalServerException("unknown error"));
        }
    }


    /**
     * 在管理器注销连接,只应由控制器调用
     * @param key destInfo
     */
    public void cancel(ConnectionInfo key){
        webSocketSet.remove(key.toString());
        onlineStateManageService.offLine(key.getUid(),key.getEndpoint());
    }

    /**
     * 供其他服务调用,向客户端发送消息
     * @param key 客户端key
     * @param message 消息
     * @throws APIGeneralException exception
     */
    void sendMessage(String key, String message) throws APIGeneralException {
        PushConnectionController target = webSocketSet.get(key);
        if (target==null){
            throw new UnprocessableEntityException("user: "+key+" is not online");
        }
        target.sendMessage(message);
    }

    /**
     * 供其他服务调用,更新控制器的授权信息
     * @param connectionInfo ConnectionInfo of PushController
     * @param bearer auth token
     * @throws APIGeneralException exception
     */
    void updateConnectAuth(ConnectionInfo connectionInfo, String bearer) throws APIGeneralException {
        PushConnectionController controller = getControllerByID(connectionInfo);
        DecodedJWT jwt = verify(bearer);
        int uid = jwt.getClaim("user_name").asInt();
        long exp = jwt.getClaim("exp").asLong();
        if (uid!=controller.getInfo().getUid()){
            throw new ResourceNoAuthException("auth and conn not match");
        }
        controller.setExp(exp);
    }

    private Endpoint getEndpoint(String name) throws APIGeneralException {
        try{
            return endpointConvertService.convertByName(name);
        }catch (IllegalArgumentException e){
            throw new BadRequestException(e.getMessage());
        }

    }
    private PushConnectionController getControllerByID(ConnectionInfo info) throws APIGeneralException{
        for (PushConnectionController controller: webSocketSet.values()){
            if (controller.getInfo().equals(info))return controller;
        }
        throw new ResourceNotFoundException("not online");
    }
    private DecodedJWT verify(String token) throws APIGeneralException {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    //.withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            jwt.getClaim("exp").asLong();
            return jwt;
        } catch (JWTVerificationException exception){
            //todo:应该为401 NoAUTH ？
            throw new ResourceNoAuthException(exception.getMessage());
            //Invalid signature/claims
        }
    }
}
