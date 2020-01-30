package com.mk.gatway.router.filter;

import com.mk.gatway.constant.FilterOrderConstant;
import groovy.util.logging.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.ForwardRoutingFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.DispatcherHandler;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

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
        LOGGER.info("请求的path:{}", exchange.getRequest().getPath());

        Object uriObj = exchange.getAttributes().get(GATEWAY_REQUEST_URL_ATTR);
        if (uriObj != null) {
            URI uri = (URI) uriObj;
            uri = this.upgradeConnection(uri, "http");
            exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, uri);
        }
        return chain.filter(exchange);

    }

    private URI upgradeConnection(URI uri, String scheme) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUri(uri).scheme(scheme);
        if (uri.getRawQuery() != null) {
            // When building the URI, UriComponentsBuilder verify the allowed characters and does not
            // support the '+' so we replace it for its equivalent '%20'.
            // See issue https://jira.spring.io/browse/SPR-10172
            uriComponentsBuilder.replaceQuery(uri.getRawQuery().replace("+", "%20"));
        }
        return uriComponentsBuilder.build(true).toUri();
    }

    @Override
    public int getOrder()
    {
        return FilterOrderConstant.AUTH_ORDER;
    }
}
