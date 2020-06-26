package com.syh.uit.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class IgnoreTokenFilterFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(TokenFilter.ATTRIBUTE_IGNORE_TokenFilter_FILTER,true);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
