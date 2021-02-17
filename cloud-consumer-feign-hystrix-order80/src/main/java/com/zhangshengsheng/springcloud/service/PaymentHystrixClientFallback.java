package com.zhangshengsheng.springcloud.service;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: springcloud2021
 * @description: ...
 * @author: 张胜胜
 * @create: 2021-02-16 18:10
 **/
@Component
public class PaymentHystrixClientFallback implements PaymentHystrixClient{
    @Override
    public String paymentInfoOK(@PathVariable("id") Integer id){
        return "-------PaymentFallbackService fall back-paymentInfoOK, 😭哭哭------";
    }

    @Override
    public String paymentInfoTimeOut(@PathVariable("id") Integer id){
        return "-------PaymentFallbackService fall back-paymentInfoTimeOut, 😭哭哭------";
    }
}