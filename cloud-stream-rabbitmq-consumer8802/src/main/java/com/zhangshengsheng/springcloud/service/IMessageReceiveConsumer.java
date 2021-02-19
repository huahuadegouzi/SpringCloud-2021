package com.zhangshengsheng.springcloud.service;
import org.springframework.messaging.Message;

public interface IMessageReceiveConsumer {
    void input(Message<String> message);
}
