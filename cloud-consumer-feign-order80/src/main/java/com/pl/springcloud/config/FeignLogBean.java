package com.pl.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author plei
 * @date 2020/4/2
 */
//@Configuration
public class FeignLogBean {

    @Bean
    Logger.Level feignLoggerLive(){
        return Logger.Level.FULL;
    }


}
