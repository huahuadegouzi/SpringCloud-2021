package com.zhangshengsheng.springcloud.controller;
import com.zhangshengsheng.springcloud.service.IMessagerProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: springcloud2021
 * @description: 控制器
 * @author: 张胜胜
 * @create: 2021-02-18 23:32
 **/
@RestController
public class SendMessageController {
    @Resource
    private IMessagerProvider iMessagerProvider;

    @GetMapping("/sendMessage")
    public String sendMessage(){
        return iMessagerProvider.send();
    }
}