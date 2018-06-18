package com.imooc.seller.repositories;

import com.imooc.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 订单管理
 * Created by songyouyu on 2018/6/13
 */
public interface OrderRepository extends JpaRepository<Order, String>,
        JpaSpecificationExecutor<Order> {


}
