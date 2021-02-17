package com.zhangshengsheng.springcloud.service.impl;
import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhangshengsheng.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @program: springcloud2021
 * @description: 服务实现类
 * @author: 张胜胜
 * @create: 2021-02-13 11:07
 **/
@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfoOK(Integer id) {
        return "当前线程：" + Thread.currentThread().getName() + "paymentInfo_OK, 获得的对应id: " + id + "\t 😄 哈哈哈～";
    }

    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000") //3秒钟以内就是正常的业务逻辑
    })
    @Override
    public String paymentInfoTimeOut(Integer id) {
//        int age = 10/0;
        int timeNumber = 2000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "当前线程：" + Thread.currentThread().getName()
                + "paymentInfo_TimeOut, 获得的对应id: " + id
                + "\t 😄 哈哈哈～" + "耗时(毫秒): " + timeNumber;
    }

    public String paymentInfoTimeOutHandler(Integer id){
        return "当前线程：" + Thread.currentThread().getName() + " 系统繁忙/运行报错，请稍后再试, 获得的对应id: " + id + "\t 😭 哭哭～";
    }

    // 服务熔断
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),// 时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")// 失败率达到多少后跳闸
            // 加起来就是在10s内的10次请求中如果失败超过6次（60%）进入服务熔断
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功, 流水号: " + serialNumber;
    }

    @Override
    public String paymentCircuitBreakerFallback(Integer id) {
        return "id不能为负数,请稍后再试😭哭哭～ id："+id;
    }
}