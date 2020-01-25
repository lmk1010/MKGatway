package com.mk.gatway.router;

import com.mk.gatway.router.filter.DefaultGatwayFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.ForwardRoutingFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.DispatcherHandler;

/**
 * @Author liumingkang
 * @Date 2020-01-24 17:41
 * @Destcription 路由配置
 * @Version 1.0
 **/
@Configuration
public class MKRouter
{

    private static final Logger LOGGER = LoggerFactory.getLogger(MKRouter.class);

    @Bean
    public RouteLocator defaultRouterLocater(RouteLocatorBuilder locatorBuilder)
    {

        LOGGER.info("来到了router！");
        return locatorBuilder.routes()
                .route(p -> p.path("/api/car/**").uri("http://localhost:18009/test/hello"))
                .route(p -> p.path("/api/life/**").uri("http://localhost:18009/test"))
                .build();
    }

}
