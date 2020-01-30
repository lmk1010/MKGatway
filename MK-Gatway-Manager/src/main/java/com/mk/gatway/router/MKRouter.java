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
    public RouteLocator customRouteLocator(RouteLocatorBuilder locatorBuilder)
    {
        /** spring启动一开始routes就会加载到内存里 */
        LOGGER.info("走到了router！");
        return locatorBuilder.routes()
                .route("test_route", p -> p.path("/get").uri("http://127.0.0.1:18007/auth"))
                .build();
    }

}
