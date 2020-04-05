package com.pl.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author plei
 * @date 2020/4/2
 */
public interface LoadBabancer {

    ServiceInstance instance(List<ServiceInstance> serviceInstanceList);

}
