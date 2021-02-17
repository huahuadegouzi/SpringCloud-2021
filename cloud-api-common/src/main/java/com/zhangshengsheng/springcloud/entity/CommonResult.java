package com.zhangshengsheng.springcloud.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: springcloud2021
 * @description: 统一规范输出实体
 * @author: 张胜胜
 * @create: 2021-02-08 10:36
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message){
        this(code, message,null);
    }

    public CommonResult<T> success(T result){
        return new CommonResult(0, "成功", result);
    }

    public CommonResult<T> fail(T result){
        return new CommonResult(400, "失败", result);
    }
}