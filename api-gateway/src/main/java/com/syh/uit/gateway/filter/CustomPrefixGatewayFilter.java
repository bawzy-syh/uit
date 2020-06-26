package com.syh.uit.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomPrefixGatewayFilter implements GlobalFilter, Ordered {
    Logger logger = LoggerFactory.getLogger(CustomPrefixGatewayFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /*String prefix = exchange.getRequest().getPath().pathWithinApplication().subPath(1,2).value();
        logger.info(prefix);
        logger.info(exchange.getRequest().getPath().pathWithinApplication().subPath(2).value());
        if (prefix.equals("api")){
            exchange.getRequest().getPath().modifyContextPath(exchange.getRequest().getPath().pathWithinApplication().subPath(2).value());
        }else{
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            return exchange.getResponse().setComplete();
        }*/
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
