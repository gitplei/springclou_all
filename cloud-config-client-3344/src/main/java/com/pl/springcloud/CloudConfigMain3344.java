package com.pl.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author plei
 * @date 2020/4/5
 */
@SpringBootApplication
@EnableConfigServer
public class CloudConfigMain3344 {

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigMain3344.class, args);
    }


}
