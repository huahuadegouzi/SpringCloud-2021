package com.zhangshengsheng.springcloud.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: springcloud2021
 * @description: 支付实体类
 * @author: 张胜胜
 * @create: 2021-02-08 10:34
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}