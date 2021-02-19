package com.zhangshengsheng.springcloud.service.impl;
import cn.hutool.core.lang.UUID;
import com.zhangshengsheng.springcloud.service.IMessagerProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

/**
 * @program: springcloud2021
 * @description: 实现类
 * @author: 张胜胜
 * @create: 2021-02-18 23:08
 **/
@EnableBinding(Source.class) // 定义消息推送的管道-源
@Slf4j
public class MessagerProviderImpl implements IMessagerProvider {
    @Resource
    private MessageChannel output; // 消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("***** serial: {} ****", serial);
        return null;
    }
}