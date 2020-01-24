package com.mk.gatway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author liumingkang
 * @Date 2020-01-24 18:41
 * @Destcription
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test/")
public class TestController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String toHello()
    {
        LOGGER.info("来到了controller了");
        return "hello";
    }
}
