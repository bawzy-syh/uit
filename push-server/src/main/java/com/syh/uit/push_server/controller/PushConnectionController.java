package com.syh.uit.push_server.controller;

import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.push_server.model.ConnectionInfo;
import com.syh.uit.push_server.service.WebsocketRegisterService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ServerEndpoint("/ws/{endpoint}")
@Component
public class PushConnectionController {
    private WebsocketRegisterService registerService;

    @Autowired
    private void setRegisterService(WebsocketRegisterService registerService) {
        this.registerService = registerService;
    }

    private Session session;
    private ConnectionInfo info;
    private ScheduledExecutorService expService;

    /**
     * 连接建立成功调用的方法
     * @param endpoint 终端类型
     * @param session session
     */
    @OnOpen
    private void onOpen(@PathParam(value = "endpoint") String endpoint, Session session) {
        this.session = session;
        Map<String,String> parameters =session.getPathParameters();
        String bearer = parameters.get("Authorization");
        registerService.register(this,endpoint,bearer);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    private void onClose() {
        registerService.cancel(info);
    }

    /**
     * websocket发生错误时调用
     * @param error websocket error
     */
    @OnError
    private void onError(Throwable error) {
        LoggerFactory.getLogger(PushConnectionController.class).info("error on websocket",error);
        error.printStackTrace();
    }

    /**
     * 发送消息
     * @param message 要发送的消息
     */
    public void sendMessage(String message) {
        //Future<Void> async =
        //todo:异步消息发送的结果
                session.getAsyncRemote().sendText(message);
        //async.
    }

    /**
     * 控制器使本连接关闭
     */
    public void closeConnect() {
        try{
            session.close();
        }catch (IOException e){
            LoggerFactory.getLogger(PushConnectionController.class).info("error on websocket",e);
        }
    }
    /**
     * 客户端发送请求的异常处理
     * @param e 抛出的异常
     */
    public void handleError(APIGeneralException e){
        sendMessage(e.toString());
    }

    public void setInfo(ConnectionInfo info) {
        this.info = info;
    }

    public void setExp(long exp) {
        if (expService!=null) expService.shutdown();
        expService = Executors.newSingleThreadScheduledExecutor();
        // 参数：1、任务体 2、首次执行的延时时间
        //      3、任务执行间隔 4、间隔时间单位
        long period = exp - System.currentTimeMillis()/1000;
        expService.scheduleAtFixedRate(()->{
            expService.shutdown();
            registerService.cancel(this.info);
            },
                0,
                period,
                TimeUnit.MICROSECONDS);
    }

    public ConnectionInfo getInfo() {
        return info;
    }
}
