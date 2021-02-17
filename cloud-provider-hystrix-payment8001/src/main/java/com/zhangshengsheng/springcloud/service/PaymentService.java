package com.zhangshengsheng.springcloud.service;

public interface PaymentService {
    // ====服务降级
    String paymentInfoOK(Integer id);
    String paymentInfoTimeOut(Integer id);

    // ====服务熔断
    String paymentCircuitBreaker(Integer id);
    String paymentCircuitBreakerFallback(Integer id);
}
