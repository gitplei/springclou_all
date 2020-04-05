package com.pl.springcloud.controller;

import com.pl.springcloud.entity.CommonResult;
import com.pl.springcloud.entity.Payment;
import com.pl.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author plei
 * @date 2020/3/31
 */
@RestController
@Slf4j
public class PaymentController {


    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;


    @ResponseBody
//    @RequestMapping(value = "/api/getPaymentById/{id}",method = RequestMethod.GET)
    @RequestMapping(value = "/api/getPaymentById",method = RequestMethod.POST)
    public CommonResult getPaymentById(Long id) {
//    public CommonResult getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        return new CommonResult(200,"查询成功 serverPort" + serverPort,payment);
    }

    @ResponseBody
    @RequestMapping(value = "/api/addPayment",method = RequestMethod.POST)
    public CommonResult<Payment> addPayment(@RequestBody Payment payment){
        log.info("======" + payment.toString());
        int add = paymentService.add(payment);
        return new CommonResult(200,"新增成功 serverPort" + serverPort,null);
    }


    @ResponseBody
    @GetMapping(value = "/api/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info(" element : " + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getUri() + "\t" + instance.getHost());
        }
        return this.discoveryClient;
    }


    /**
     * 测试 自写轮询算法
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @ResponseBody
    @GetMapping(value = "/payment/paymentFeignTimeOut")
    public String paymentFeignTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }


    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_ok(id);
        log.info("result ****  == " + result);
        return result;
    }


    @GetMapping("/payment/hystrix/timeOut/{id}")
    public String paymentInfo_timeOut(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_timeOut(id);
        log.info("result ****  == " + result);
        return result;
    }


    //======服务熔断
    @GetMapping("/payment/circuit/timeOut/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("result ****  == " + result);
        return result;
    }

}
