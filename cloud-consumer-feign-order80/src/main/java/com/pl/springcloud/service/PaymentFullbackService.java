package com.pl.springcloud.service;

import com.pl.springcloud.entity.CommonResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author plei
 * @date 2020/4/3
 */
@Component
public class PaymentFullbackService implements PaymentFeignService{
    @Override
    public CommonResult getPaymentById(Long id) {
        return new CommonResult(404,"系统繁忙");
    }

    @Override
    public String paymentFeignTimeOut() {
        return "========== paymentFeignTimeOut =========";
    }

    @Override
    public String paymentInfo_ok(@PathVariable("id") Integer id) {
        return "========== paymentInfo_ok =========";
    }

    @Override
    public String paymentInfo_timeOut(@PathVariable("id") Integer id) {
        return "========== paymentInfo_timeOut =========";
    }
}
