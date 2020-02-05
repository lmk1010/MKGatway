package com.mk.gatway.config.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.HeaderRoutePredicateFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

/**
 * @Author liumingkang
 * @Date 2020-02-01 12:29
 * @Destcription 自定义的router的谓词工厂
 * @Version 1.0
 **/
@Slf4j
public class MKRoutePredicateFactory extends AbstractRoutePredicateFactory<HeaderRoutePredicateFactory.Config>
{

    public MKRoutePredicateFactory(Class<HeaderRoutePredicateFactory.Config> configClass)
    {
        super(configClass);
    }


    @Override
    public Predicate<ServerWebExchange> apply(HeaderRoutePredicateFactory.Config config)
    {
        // grab configuration from Config object
        return exchange -> {
            //grab the request
            ServerHttpRequest request = exchange.getRequest();
            //take information from the request to see if it
            log.info("配置信息");
            //matches configuration.
            return matches(config, request);
        };
    }

    private boolean matches(HeaderRoutePredicateFactory.Config config, ServerHttpRequest request)
    {
        return true;
    }

}
