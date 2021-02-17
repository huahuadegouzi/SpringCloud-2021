package com.zhangshengsheng.springcloud.controller;
import com.zhangshengsheng.springcloud.entity.CommonResult;
import com.zhangshengsheng.springcloud.entity.Payment;
import com.zhangshengsheng.springcloud.lb.MyLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: springcloud2021
 * @description: 订单控制器
 * @author: 张胜胜
 * @create: 2021-02-08 14:11
 **/
@RestController
@RequestMapping("/consumer")
@Slf4j
public class OrderController {
//    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PROVIDER-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private MyLoadBalancer myLoadBalancer;

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @PostMapping("/payment/create2")
    public CommonResult<Payment> create2(@RequestBody Payment payment){
        ResponseEntity<CommonResult> commonResultResponseEntity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        log.info(commonResultResponseEntity.getStatusCode() + "\t" + commonResultResponseEntity.getHeaders());
        return commonResultResponseEntity.getBody();
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-PAYMENT-SERVICE");
        if(null == instances || instances.size() <= 0){
            return null;
        }
        ServiceInstance instance = myLoadBalancer.instances(instances);
        String str = instance.getServiceId() + "\t" + instance.getInstanceId() + "\t"
                + instance.getHost() + "\t" + instance.getUri() + "\t" + instance.getPort();
        return str;
    }
}