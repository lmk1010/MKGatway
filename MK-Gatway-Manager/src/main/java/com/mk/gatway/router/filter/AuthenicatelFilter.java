package com.mk.gatway.router.filter;

import com.mk.gatway.constant.FilterOrderConstant;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Map;

/**
 * @Author liumingkang
 * @Date 2020-01-25 01:00
 * @Destcription 默认的全局过滤器 按照order排序过滤 主要用于认证 和 权限的分配
 * @Version 1.0
 **/
@Component
public class AuthenicatelFilter implements GlobalFilter, Ordered
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenicatelFilter.class);

    @Resource(name = "restTemplate")
    private RestTemplate restTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        LOGGER.info("全局filter...");
        LOGGER.info("请求的path:{}", exchange.getRequest().getPath());

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        Map<String, String> requestParam = new HashedMap();
        switch (request.getMethodValue())
        {
            case "GET":
                MultiValueMap<String, String> queryParams = request.getQueryParams();
                for (String key : queryParams.keySet())
                {

                }
                break;
            case "POST":
                break;
        }
        if (requestParam == null)
        {
            LOGGER.error("Request param is empty! authenicate failed!");
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }

        if (!validateAuth(exchange.getRequest()))
        {
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }
        return chain.filter(exchange);
    }

    private boolean validateAuth(ServerHttpRequest request)
    {
        Flux<DataBuffer> body = request.getBody();
        LOGGER.warn("参数： "+ body.toString());
        Map<String,String> requestParam = new HashedMap();

//        restTemplate.postForObject("", requestParam, Object.class);
        return true;
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
