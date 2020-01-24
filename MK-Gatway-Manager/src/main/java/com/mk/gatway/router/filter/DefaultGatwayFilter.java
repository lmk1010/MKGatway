package com.mk.gatway.router.filter;

import com.mk.gatway.constant.FilterOrderConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author liumingkang
 * @Date 2020-01-24 19:28
 * @Destcription 默认的网关过滤器
 * @Version 1.0
 **/
public class DefaultGatwayFilter implements GatewayFilter, Ordered
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGatwayFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        LOGGER.debug("this is info default gatway filter{}",exchange.getRequest().getId());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder()
    {
        return FilterOrderConstant.COMMON_ORDER;
    }
}
