package com.pl.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author plei
 * @date 2020/4/5
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/get/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return "nacos register, payment serverPort" + serverPort + "\t id = " + id;
    }


}
