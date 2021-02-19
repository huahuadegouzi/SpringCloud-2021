package com.zhangshengsheng.springcloud.controller;
import com.zhangshengsheng.springcloud.service.IMessageReceiveConsumer;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: springcloud2021
 * @description: 控制器
 * @author: 张胜胜
 * @create: 2021-02-18 23:51
 **/
@RestController
public class ReceiveMessageController {
    @Resource
    private IMessageReceiveConsumer iMessageReceiveConsumer;

    @GetMapping("/receiveMessage")
    public void input(Message<String> message){
        iMessageReceiveConsumer.input(message);
    }
}