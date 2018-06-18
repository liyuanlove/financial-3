package com.imooc.seller.service;

import com.imooc.api.events.ProductStatusEvent;
import com.imooc.entity.Product;
import com.imooc.entity.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品相关服务
 * Created by songyouyu on 2018/6/14
 */
@Service //容器初始化完成后会触发该事件
public class ProductRpcService implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger Log = LoggerFactory.getLogger(ProductRpcService.class);

    static final String MQ_DESTINATION = "Consumer.cache.VirtualTopic.PRODUCT_STATUS";


    @Autowired
    private ProductCache productCache;

    /**
     * 查询所有产品
     * @return
     */
    public List<Product> findAll() {
        return productCache.readAllCache();
    }

    /**
     * 查询单个产品
     * @param id
     * @return
     */
    public Product findOne(String id) {
        Product product = productCache.readCache(id);
        if (product == null) {
            productCache.removeCache(id);
        }
        return product;
    }

    /**
     * 系统启动时读取所有数据并存入缓存
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<Product> products = findAll();
        products.forEach(product -> {
            productCache.putCache(product);
        });
    }

    @JmsListener(destination = MQ_DESTINATION)
    void updateCache(ProductStatusEvent event) {
        Log.info("receive event:{}", event);
        productCache.removeCache(event.getId());
        if (ProductStatus.IN_SELL.equals(event.getStatus())) {
            productCache.readCache(event.getId());
        }
    }


}
