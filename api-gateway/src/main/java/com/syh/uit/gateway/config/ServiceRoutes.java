package com.syh.uit.gateway.config;

import com.syh.uit.gateway.filter.IgnoreTokenFilterFilter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.factory.HystrixGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.DispatcherHandler;

@Component
public class ServiceRoutes {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/api/auth/**")
                        .filters(f -> f.stripPrefix(1)
                                .filter(new IgnoreTokenFilterFilter())
                                )
                        .uri("lb://auth-app"))
                .route(p -> p
                        .path("/api/basic/**")
                        .filters(f -> f.stripPrefix(1)
                                )
                        .uri("lb://basic-info-app"))
                .route(p -> p
                        .path("/api/relation/**")
                        .filters(f -> f.stripPrefix(1)
                                )
                        .uri("lb://relation-info-app"))
                .route(p -> p
                        .path("/api/group/**")
                        .filters(f -> f.stripPrefix(1)
                                )
                                .uri("lb://relation-group-app"))
                .route(p -> p
                        .path("/api/friend-apply/**")
                        .filters(f -> f.stripPrefix(1)
                                )
                                .uri("lb://friend-apply-app"))
                .route(p ->
                        p.path("/api/chat/**")
                        .filters(f -> f.stripPrefix(1)
                                )
                                .uri("lb://chat-app"))
                .route(p ->
                        p.path("/api/push/**")
                        .filters(f -> f.stripPrefix(1)
                                )
                                .uri("lb:ws://push-center-app"))
                .build();
    }

    @Bean
    HystrixGatewayFilterFactory hystrixGatewayFilterFactory(ObjectProvider<DispatcherHandler> dispatcherHandlerProvider){
        return new HystrixGatewayFilterFactory(dispatcherHandlerProvider);
    }

    @RequestMapping("fallback")
    public String fallback() {
        return "Hello,Hystrix fallback";
    }
}
