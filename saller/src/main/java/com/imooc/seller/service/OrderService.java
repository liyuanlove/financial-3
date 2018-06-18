package com.imooc.seller.service;

import com.imooc.entity.Order;
import com.imooc.entity.Product;
import com.imooc.entity.enums.OrderStatus;
import com.imooc.entity.enums.OrderType;
import com.imooc.seller.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.UUID;

/**
 * 订单服务
 * Created by songyouyu on 2018/6/16
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRpcService productRpcService;

    /**
     * 创建订单
     * @param order
     * @return
     */
    public Order apply(Order order) {
        // 校检数据
        checkOrder(order);
        // 补充订单其他信息
        completeOrder(order);
        order = orderRepository.saveAndFlush(order);
        return order;
    }

    /**
     * 完善订单数据
     * @param order
     */
    private void completeOrder(Order order) {
        order.setOrderId(UUID.randomUUID().toString().replaceAll("-",""));
        order.setOrderType(OrderType.APPLY.name());
        order.setOrderStatus(OrderStatus.SUCCESS.name());
        order.setUpdateAt(new Date());
    }

    /**
     * 校检订单数据
     * @param order
     */
    private void checkOrder(Order order) {
        // 必填字段
        Assert.notNull(order.getOuterOrderId(),"需要外部订单编号");
        Assert.notNull(order.getChanId(),"需要渠道编号");
        Assert.notNull(order.getChanUserId(),"需要用户编号");
        Assert.notNull(order.getProductId(),"需要产品编号");
        Assert.notNull(order.getAmount(),"需要购买金额");
        Assert.notNull(order.getCreateAt(),"需要订单时间");

        // 判断产品是否存在以及产品金额是否符合要求
        Product product = productRpcService.findOne(order.getProductId());
        Assert.notNull(product, "购买的产品不存在");
        // 金额要满足如果有起投金额时，要大于等于起投金额，
        // 如果有投资步长时，超过起投金额的部分要是投资步长的整数倍
//        if (product.getThresholdAmount() != null) {
//            Assert.isTrue(order.getAmount().compareTo(product.getThresholdAmount()) > 0);
//            if (product.getStepAmount() != null) {
//                Assert.isTrue((order.getAmount().subtract(
//                        product.getThresholdAmount())).divide(product.getStepAmount()).doubleValue() == 0);
//            }
//        }
    }
}
