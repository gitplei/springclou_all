package com.pl.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.pl.springcloud.entity.CommonResult;
import com.pl.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author plei
 * @date 2020/4/2
 */
@RestController
@DefaultProperties(defaultFallback = "paymentInfo_timeOutFullback")
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @RequestMapping(value = "/consumer/payment/getPaymentById", method = RequestMethod.POST)
    public CommonResult getPaymentById(Long id) {
        CommonResult commonResult = paymentFeignService.getPaymentById(id);
        return commonResult;
    }

    /**
     * 测试feign 接口默认超时
     *
     * @return
     */
    @GetMapping(value = "/consumer/payment/paymentFeignTimeOut")
    public String paymentFeignTimeOut() {
        //默认1s 报错
        return paymentFeignService.paymentFeignTimeOut();
    }


    /**
     * 测试断路器 正常访问方法
     *
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    String paymentInfo_ok(@PathVariable("id") Integer id) {
        String result = paymentFeignService.paymentInfo_ok(id);
        return result;
    }

    /**
     * 测试断路器 超时访问方法 修改前
     *
     * @param id
     * @return
     */
//    @GetMapping("/consumer/payment/hystrix/timeOut/{id}")
//    String paymentInfo_timeOut(@PathVariable("id") Integer id) {
//        String result = paymentFeignService.paymentInfo_timeOut(id);
//        return result;
//    }

    /**
     * 测试断路器 超时访问方法 修改后
     *
     * @param id
     * @return
     */
    //指定一个 单独方法
//    @HystrixCommand(fallbackMethod = "paymentInfo_timeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeOut/{id}")
    String paymentInfo_timeOut(@PathVariable("id") Integer id) {
//        int a = 10%0;
        String result = paymentFeignService.paymentInfo_timeOut(id);
        return result;
    }

    public String paymentInfo_timeOutHandler(Integer id) {
        String info = "线程池：order feign80 调用超时";
        return info;
    }

    //全局fullback
    public String paymentInfo_timeOutFullback() {
        String info = "order feign80 调用超时或者系统出错！！！";
        return info;
    }


}
