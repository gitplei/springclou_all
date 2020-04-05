package com.pl.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author plei
 * @date 2020/4/5
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigClientMain3355 {


    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3355.class, args);
    }

}
