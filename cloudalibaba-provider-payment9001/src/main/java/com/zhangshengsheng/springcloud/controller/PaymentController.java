package com.zhangshengsheng.springcloud.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springcloud2021
 * @description: 控制器
 * @author: 张胜胜
 * @create: 2021-02-19 11:50
 **/
@RestController
@RequestMapping("/payment/nacos")
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/{id}")
    public String getPaymentById(@PathVariable("id") Integer id){
        return Thread.currentThread().getName() + "nacos registry, serverPort: " + serverPort + "\t id: " + id;
    }
}