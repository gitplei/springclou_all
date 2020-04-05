package com.pl.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.pl.springcloud.dao.PaymentDao;
import com.pl.springcloud.entity.Payment;
import com.pl.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * @author plei
 * @date 2020/3/31
 */
@Service
public class PaymentServiceImpl implements PaymentService {


    @Override
    public String test() {
        return "ddd";
    }

    @Resource
    private PaymentDao paymentDao;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int add(Payment payment) {
        return paymentDao.add(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        Payment payment = paymentDao.getPaymentById(id);
        return payment;
    }


    @Override
    public String paymentInfo_ok(Integer id) {
        String info = "线程池：" + Thread.currentThread().getName() + " paymentInfo_ok id = " + id;
        return info;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_timeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @Override
    public String paymentInfo_timeOut(Integer id) {
        String info = "线程池：" + Thread.currentThread().getName() + " paymentInfo_timeOut id = " + id + " 耗时2秒钟";
        //测试报错
        int a = 10/0;
//        try {
//            TimeUnit.SECONDS.sleep(4);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return info;
    }

    public String paymentInfo_timeOutHandler(Integer id) {
        String info = "线程池：" + Thread.currentThread().getName() + " paymentInfo_timeOutHandler id = " + id + " 调用超时";
        return info;
    }



    //==========================服务熔断===========================================
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fullBack",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value= "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value= "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value= "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value= "60"),//失败率达到多少后跳闸
    })
    @Override
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        String result = Thread.currentThread().getName() + "调用成功，流水号 " + serialNumber;
        return result;
    }

    public String paymentCircuitBreaker_fullBack(Integer id){
        String result = "id 不能为负数，请稍后再试！！ ";
        return  result;
    }


}
