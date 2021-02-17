package com.zhangshengsheng.springcloud.lb;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @program: springcloud2021
 * @description: 负载均衡
 * @author: 张胜胜
 * @create: 2021-02-10 22:04
 **/
public interface MyLoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}