package com.pl.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author plei
 * @date 2020/4/5
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain9002 {


    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9002.class, args);
    }

}
