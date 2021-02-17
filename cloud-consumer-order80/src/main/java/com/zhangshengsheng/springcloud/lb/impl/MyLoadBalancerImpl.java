package com.zhangshengsheng.springcloud.lb.impl;
import com.zhangshengsheng.springcloud.lb.MyLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: springcloud2021
 * @description: 负载均衡实现
 * @author: 张胜胜
 * @create: 2021-02-10 22:30
 **/
@Component
@Slf4j
public class MyLoadBalancerImpl implements MyLoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private AtomicLong atomicLong = new AtomicLong(1);

    public int getAndIncrement(int modulo){
        for(;;){
            int current = atomicInteger.get();
            int next = (current + 1) % modulo;
            if(atomicInteger.compareAndSet(current,next)){
                log.info("******第{}次访问******", atomicLong.getAndIncrement());
                return next;
            }
        }
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        return serviceInstances.get(getAndIncrement(serviceInstances.size()));
    }
}