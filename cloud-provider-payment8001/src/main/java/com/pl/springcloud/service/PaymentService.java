package com.pl.springcloud.service;

import com.pl.springcloud.entity.Payment;

/**
 * @author plei
 * @date 2020/3/31
 */
public interface PaymentService {

    int add(Payment payment);

    Payment getPaymentById(Long id);

    String test();

    /**
     * 测试断路器 正常访问方法
     * @param id
     * @return
     */
    String paymentInfo_ok(Integer id);

    /**
     * 测试断路器 延时方法及错误方法
     * @param id
     * @return
     */
    String paymentInfo_timeOut(Integer id);

    /**
     * 测试服务熔断
     * @param id
     * @return
     */
    String paymentCircuitBreaker(Integer id);
}
