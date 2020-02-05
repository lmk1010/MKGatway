package com.mk.gatway.router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

/**
 * @Author liumingkang
 * @Date 2020-01-24 17:41
 * @Destcription 路由配置 暂时废弃 改用配置文件进行获取ruoter
 * @Version 1.0
 **/
public class MKRouter
{

    private static final Logger LOGGER = LoggerFactory.getLogger(MKRouter.class);

    public RouteLocator customRouteLocator(RouteLocatorBuilder locatorBuilder)
    {
        /** spring启动一开始routes就会加载到内存里 */
        LOGGER.info("走到了router！");
        return locatorBuilder.routes()
                .route("test_route", p -> p.path("/get").uri("http://127.0.0.1:18007/auth"))
                .build();
    }

}
