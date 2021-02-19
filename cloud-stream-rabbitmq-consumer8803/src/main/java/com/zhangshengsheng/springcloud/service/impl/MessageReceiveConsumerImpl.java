package com.zhangshengsheng.springcloud.service.impl;
import com.zhangshengsheng.springcloud.service.IMessageReceiveConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * @program: springcloud2021
 * @description: 实现类
 * @author: 张胜胜
 * @create: 2021-02-18 23:55
 **/
@EnableBinding(Sink.class)
@Slf4j
public class MessageReceiveConsumerImpl implements IMessageReceiveConsumer {
    @Value("${server.port}")
    private String serverPort;

    @Override
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        log.info("--- 消费者2号，---> 接收到的消息: {} \t port: {}", message.getPayload(),serverPort);
    }
}