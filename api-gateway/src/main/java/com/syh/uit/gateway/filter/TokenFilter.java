package com.syh.uit.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class TokenFilter implements GlobalFilter, Ordered {
    public final static String ATTRIBUTE_IGNORE_TokenFilter_FILTER = "@ignoreTokenFilter";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (exchange.getAttribute(ATTRIBUTE_IGNORE_TokenFilter_FILTER) != null){
            return chain.filter(exchange);
        }
        List<String> token = exchange.getRequest().getHeaders().get("Authorization");
        if (token==null || token.isEmpty()){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
