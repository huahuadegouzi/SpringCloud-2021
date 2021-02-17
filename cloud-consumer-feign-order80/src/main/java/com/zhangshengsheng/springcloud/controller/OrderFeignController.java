package com.zhangshengsheng.springcloud.controller;
import com.zhangshengsheng.springcloud.entity.CommonResult;
import com.zhangshengsheng.springcloud.entity.Payment;
import com.zhangshengsheng.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: springcloud2021
 * @description: 控制器
 * @author: 张胜胜
 * @create: 2021-02-12 15:21
 **/
@RestController
@RequestMapping("/consumer/payment")
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        CommonResult<Payment> paymentById = null;
        try{
            paymentById = paymentFeignService.getPaymentById(id);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return paymentById;
    }
}