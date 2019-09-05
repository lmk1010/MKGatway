package com.mk.gatwaydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatwaydemoApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(GatwaydemoApplication.class, args);
	}


	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/get")
				.filters(f -> f.addRequestHeader("hello","world"))
				.uri("http://httpbin.org:80"))
				.route(p -> p
				         .path("/fpp")
				.filters(f -> f.hystrix(config -> config
						.setName("mycmd")
						.setFallbackUri("forward:/forwardCallback")))
				.uri("http://www.google.com"))
				.build();
	}



}
