package com.zhangshengsheng.myrule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springcloud2021
 * @description: 负载方式类
 * @author: 张胜胜
 * @create: 2021-02-10 21:31
 **/
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        // 定义为随机负载
        return new RandomRule();
    }
}