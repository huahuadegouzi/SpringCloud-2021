package com.zhangshengsheng.springcloud.dao;
import com.zhangshengsheng.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @program: springcloud2021
 * @description: 支付dao
 * @author: 张胜胜
 * @create: 2021-02-08 10:46
 **/
@Mapper
public interface PaymentDao {
    /**
     * 插入一条记录
     * @param payment 服务序列号
     * @return 成功个数
     */
    int create(Payment payment);

    /**
     * 查询指定id的记录
     * @param id 指定id
     * @return 一条完整记录
     */
    Payment getPaymentById(@Param("id") Long id);
}