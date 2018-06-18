package com.imooc.seller.controller;

import com.imooc.entity.Order;
import com.imooc.seller.params.OrderParam;
import com.imooc.seller.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单相关
 * Created by songyouyu on 2018/6/16
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * @param param
     * @return
     */
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public Order apply(@RequestHeader String authId, @RequestHeader String sign, @RequestBody OrderParam param) {
        log.info("创建订单请求 : []", param);
        Order order = new Order();
        BeanUtils.copyProperties(param, order);
        order = orderService.apply(order);
        log.info("创建订单结果 : {}", order);
        return order;
    }
}
