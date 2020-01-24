package com.mk.gatway.router.filter;

import com.mk.gatway.constant.FilterOrderConstant;
import groovy.util.logging.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @Author liumingkang
 * @Date 2020-01-25 01:00
 * @Destcription 默认的全局过滤器 按照order排序过滤
 * @Version 1.0
 **/
@Component
public class DefaultGlobalFilter implements GlobalFilter, Ordered
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        LOGGER.info("全局filter...");
        ServerHttpResponse response = exchange.getResponse();
        JSONObject message = new JSONObject();
        try {
            message.put("result", "success");
            message.put("status", "200");
            byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            return response.writeWith(Mono.just(buffer));
        } catch (JSONException e) {
            e.printStackTrace();
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder()
    {
        return FilterOrderConstant.AUTH_ORDER;
    }
}
