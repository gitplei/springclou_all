package com.pl.springcloud.controller;

import com.pl.springcloud.entity.CommonResult;
import com.pl.springcloud.entity.Payment;
import com.pl.springcloud.lb.LoadBabancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author plei
 * @date 2020/4/1
 */
@RestController
@Slf4j
public class OrderController {


    //    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private LoadBabancer loadBabancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/payment/addPayment", method = RequestMethod.POST)
    public CommonResult<Payment> addPayment(Payment payment) {
        log.info("================" + payment.toString());
        return restTemplate.postForObject(PAYMENT_URL + "/api/addPayment", payment, CommonResult.class);
    }

    @RequestMapping(value = "/consumer/payment/getPaymentById", method = RequestMethod.POST)
    public CommonResult<Payment> getPaymentById(Long id) {
        log.info("id =========================== " + id);
        return restTemplate.postForObject(PAYMENT_URL + "/api/getPaymentById", id, CommonResult.class);
    }

    /**
     * 测试 自定义轮询算法
     * @return
     */
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (null == instances || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBabancer.instance(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }


    //=====================微服务链路跟踪========================
    @GetMapping(value = "/zipkin")
    public String zipkin(){
        System.out.println("==================  链路跟踪");
        return restTemplate.getForObject("http://localhost:8001/zipkin", String.class);
    }

}
