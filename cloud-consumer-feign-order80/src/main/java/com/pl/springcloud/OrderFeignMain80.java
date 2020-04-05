package com.pl.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author plei
 * @date 2020/4/2
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class OrderFeignMain80 {


    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }


}
