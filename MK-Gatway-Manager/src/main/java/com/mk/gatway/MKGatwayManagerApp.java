package com.mk.gatway;

import groovy.util.logging.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * @Author liumingkang
 * @Date 2020-01-24 17:38
 * @Destcription MK网关启动类
 * @Version 1.0
 **/

@SpringBootApplication
@EnableDiscoveryClient
public class MKGatwayManagerApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(MKGatwayManagerApp.class, args);
    }

    @LoadBalanced
    @Bean("restTemplate")
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
