package com.syh.uit.user_connection_center.module;

import com.syh.uit.user_connection_center.module.inner.Endpoint;

public class PushOrder {
    private final String target;
    private final PushMessage message;

    public PushOrder(int uid, Endpoint endpoints, String serviceName, String message){
        this.target = uid+"."+endpoints.getName();
        this.message = new PushMessage(serviceName, message);
    }

    public String getTarget() {
        return target;
    }

    public String getMessage() {
        return message.toString();
    }
    private static class PushMessage{
        private final String serviceName;
        private final String message;

        private PushMessage(String serviceName, String message) {
            this.serviceName = serviceName;
            this.message = message;
        }

        public String getServiceName() {
            return serviceName;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return "{" + "serviceName='" + serviceName + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
