package com.zhangshengsheng.springcloud.config;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springcloud2021
 * @description: feign配置类
 * @author: 张胜胜
 * @create: 2021-02-12 20:40
 **/
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}