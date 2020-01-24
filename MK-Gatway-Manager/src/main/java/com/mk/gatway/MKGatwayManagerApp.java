package com.mk.gatway;

import com.mk.gatway.router.filter.config.CustomGlobalFilterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;

/**
 * @Author liumingkang
 * @Date 2020-01-24 17:38
 * @Destcription MK网关启动类
 * @Version 1.0
 **/

@SpringBootApplication
public class MKGatwayManagerApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(MKGatwayManagerApp.class, args);
    }
}
