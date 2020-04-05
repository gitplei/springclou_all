package com.pl.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author plei
 * @date 2020/4/4
 */
@Component
@Slf4j
public class MylogFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
        log.info("MylogFilter =======================");
        List<String> name = serverWebExchange.getRequest().getHeaders().get("name");
        if(null == name){
            log.info("headers is null");
            serverWebExchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return serverWebExchange.getResponse().setComplete();
        }
        return gatewayFilterChain.filter(serverWebExchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
