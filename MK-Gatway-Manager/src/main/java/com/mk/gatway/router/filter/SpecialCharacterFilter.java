package com.mk.gatway.router.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author liumingkang
 * @Date 2020-02-05 13:23
 * @Destcription 特殊字符过滤 包括SQL注入和XSS
 * @Version 1.0
 **/
public class SpecialCharacterFilter implements GlobalFilter, Ordered
{

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        return null;
    }

    @Override
    public int getOrder()
    {
        return 0;
    }
}
