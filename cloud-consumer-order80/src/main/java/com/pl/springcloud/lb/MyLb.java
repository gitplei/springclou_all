package com.pl.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author plei
 * @date 2020/4/2
 */
@Component
public class MyLb implements LoadBabancer{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 自旋锁
     * @return
     */
    private final int getAndIncremnt() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        } while(!atomicInteger.compareAndSet(current,next));
        System.out.println("********************" + next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstanceList) {
        int index = getAndIncremnt() % serviceInstanceList.size();
        return serviceInstanceList.get(index);
    }


}
