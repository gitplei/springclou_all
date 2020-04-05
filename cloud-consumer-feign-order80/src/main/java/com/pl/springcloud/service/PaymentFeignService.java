package com.pl.springcloud.service;

import com.pl.springcloud.entity.CommonResult;
import com.pl.springcloud.entity.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author plei
 * @date 2020/4/2
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE",fallback = PaymentFullbackService.class)
public interface PaymentFeignService {

    /**
     *ss
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/getPaymentById",method = RequestMethod.POST)
    CommonResult getPaymentById(Long id);

    /**
     * 测试feign 接口默认超时
     * @return
     */
    @GetMapping(value = "/payment/paymentFeignTimeOut")
    String paymentFeignTimeOut();


    /**
     * 测试断路器 正常访问方法
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_ok(@PathVariable("id") Integer id);

    /**
     * 测试断路器 正常访问方法
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/timeOut/{id}")
    String paymentInfo_timeOut(@PathVariable("id") Integer id);

}
