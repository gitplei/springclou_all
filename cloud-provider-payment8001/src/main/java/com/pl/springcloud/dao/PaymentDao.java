package com.pl.springcloud.dao;

import com.pl.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author plei
 * @date 2020/3/31
 */

@Mapper
public interface PaymentDao {

    int add(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

}
