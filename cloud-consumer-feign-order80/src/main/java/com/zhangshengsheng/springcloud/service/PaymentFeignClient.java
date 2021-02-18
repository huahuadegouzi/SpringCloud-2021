package com.zhangshengsheng.springcloud.service;

import com.zhangshengsheng.springcloud.entity.CommonResult;
import com.zhangshengsheng.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "cloud-provider-payment-service")
@RequestMapping("/payment")
public interface PaymentFeignClient {
    @PostMapping("/create")
    CommonResult create(@RequestBody Payment payment);

    @GetMapping("/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
