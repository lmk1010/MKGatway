package com.mk.gatway.constant;

import org.springframework.core.Ordered;

/**
 * @Author liumingkang
 * @Date 2020-01-25 01:15
 * @Destcription 过滤器的顺序 常量
 * @Version 1.0
 **/
public interface FilterOrderConstant
{
    // TODO: 2020-01-25 后期调整为数据库读取可配置
    int AUTH_ORDER = -100;

    int COMMON_ORDER = 0;

    // 调整区间为200个filter
    int MIN_ORDER = 100;

    int MAX_ORDER = -100;

}
