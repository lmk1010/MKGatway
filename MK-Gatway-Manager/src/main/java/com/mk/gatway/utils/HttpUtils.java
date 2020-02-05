package com.mk.gatway.utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

import java.nio.charset.Charset;

/**
 * @Author liumingkang
 * @Date 2020-02-05 12:40
 * @Destcription TODO
 * @Version 1.0
 **/
public class HttpUtils
{
    /**
     *
     * @Author liumingkang
     * @Description 手动拼接Basic认证的请求头
     * @Date 12:40 2020-02-05
     * @Param [username, password]
     * @return org.springframework.http.HttpHeaders
     **/
    HttpHeaders createHeaders(String username, String password)
    {
        return new HttpHeaders() {
            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
    }
}
