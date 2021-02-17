package com.zhangshengsheng.springcloud.service.impl;
import com.zhangshengsheng.springcloud.dao.PaymentDao;
import com.zhangshengsheng.springcloud.entity.Payment;
import com.zhangshengsheng.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: springcloud2021
 * @description: 服务提供者服务实现类
 * @author: 张胜胜
 * @create: 2021-02-08 11:20
 **/
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        int i = paymentDao.create(payment);
        log.info("******插入了: {}条记录，记录为：{}", i, payment);
        return i;
    }

    @Override
    public Payment getPaymentById(Long id) {
        Payment paymentById = paymentDao.getPaymentById(id);
        log.info("******查询结果为：{}", paymentById);
        return paymentById;
    }
}