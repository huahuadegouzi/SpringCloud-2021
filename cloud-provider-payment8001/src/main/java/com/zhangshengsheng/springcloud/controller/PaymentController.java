package com.zhangshengsheng.springcloud.controller;
import com.zhangshengsheng.springcloud.entity.CommonResult;
import com.zhangshengsheng.springcloud.entity.Payment;
import com.zhangshengsheng.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @program: springcloud2021
 * @description: 服务提供者控制类
 * @author: 张胜胜
 * @create: 2021-02-08 11:22
 **/
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if(result > 0){
            return new CommonResult(200, "插入数据库记录成功, server port: " + serverPort, result);
        }
        return new CommonResult(400, "插入数据库记录失败, server port: " + serverPort, result);
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment paymentById = paymentService.getPaymentById(id);
        if(Objects.nonNull(paymentById)){
            return new CommonResult<Payment>(200, "查询数据库记录成功, server port: " + serverPort, paymentById);
        }
        return new CommonResult<>(400, "查询数据库记录失败, 没有对应的记录, server port: " + serverPort, paymentById);
    }

    @GetMapping("/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping("/discovery")
    public DiscoveryClient discovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach(element -> {
            log.info("******* element ******: {}", element);
            List<ServiceInstance> instances = discoveryClient.getInstances(element);
            instances.forEach(instance -> log.info("***嵌套***" + instance.getInstanceId() + "\t" + instance.getHost() + "\t" + instance.getUri() + "\t" + instance.getPort()));
        });

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-PAYMENT-SERVICE");
        instances.forEach(instance -> log.info(instance.getInstanceId() + "\t" + instance.getHost() + "\t" + instance.getUri() + "\t" + instance.getPort()));

        return discoveryClient;
    }

    @GetMapping("/zipkin")
    public String paymentZipkin(){
        return "***** 我是 zipkin *****";
    }
}