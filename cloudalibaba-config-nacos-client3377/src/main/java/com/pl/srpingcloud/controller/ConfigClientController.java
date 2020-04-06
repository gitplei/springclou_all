package com.pl.srpingcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author plei
 * @date 2020/4/6
 */
@RestController
@RefreshScope //nacos动态刷新
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/config/getConfigInfo")
    public String getConfigInfo(){
        return configInfo;
    }

}
