package com.mk.gatway.router.filter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import java.security.Principal;

/**
 * @Author liumingkang
 * @Date 2020-01-24 22:59
 * @Destcription 自定义的全局过滤 适用所有路由 // FIXME: 2020-01-25 暂时不启用
 * @Version 1.0
 **/
//@Configuration
public class CustomGlobalFilterConfig
{

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomGlobalFilterConfig.class);

    /**
     * @Author liumingkang
     * @Description 全局的过滤器的请求之前
     * @Date 00:21 2020-01-25
     * @Param []
     * @return org.springframework.cloud.gateway.filter.GlobalFilter
     **/
    @Bean
    public GlobalFilter customGlobalFilter() {
        LOGGER.info("进来 pre filter");
        return (exchange, chain) -> {
            // todo 全局的过滤器 适用于做用户校验
            // TODO: 2020-01-25 先访问auth服务进行
            LOGGER.info("请求进来:{}", exchange.getRequest().getId());

            return exchange.getPrincipal()
                    .map(Principal::getName)
                    .defaultIfEmpty("Default User")
                    .map(userName -> {
                        //adds header to proxied request
                        exchange.getRequest().mutate().header("CUSTOM-REQUEST-HEADER", userName).build();
                        return exchange;
                    })
                    .flatMap(chain::filter);
        };
    }

    /**
     *
     * @Author liumingkang
     * @Description 网关过滤之后的返回处理
     * @Date 00:28 2020-01-25
     * @Param []
     * @return org.springframework.cloud.gateway.filter.GlobalFilter
     **/
    @Bean
    public GlobalFilter customGlobalPostFilter() {
        LOGGER.info("进来 post filter");
        return (exchange, chain) -> {
            LOGGER.info("请求出去");
            return chain.filter(exchange)
                    .then(Mono.just(exchange))
                    .map(serverWebExchange -> {
                        //adds header to response
                        serverWebExchange.getResponse().getHeaders().set("CUSTOM-RESPONSE-HEADER",
                                HttpStatus.OK.equals(serverWebExchange.getResponse().getStatusCode()) ? "It worked" : "It did not work");
                        return serverWebExchange;
                    })
                    .then();
        };
    }

}
