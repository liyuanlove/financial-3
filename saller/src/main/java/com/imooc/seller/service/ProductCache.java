package com.imooc.seller.service;

import com.hazelcast.core.HazelcastInstance;
import com.imooc.api.ProductRpc;
import com.imooc.api.domain.ProductRpcReq;
import com.imooc.entity.Product;
import com.imooc.entity.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 产品缓存
 * Created by songyouyu on 2018/6/15
 */
@Component
public class ProductCache {

    private static final Logger Log = LoggerFactory.getLogger(ProductCache.class);

    static final String CACHE_NAME = "imooc_product";

    @Autowired
    private ProductRpc productRpc;
    @Autowired
    private HazelcastInstance hazelcastInstance;

    /*
     * 在当前类中调用这些带有注解的方法的时候，它的缓存功能是没有效果的
     */

    /**
     * 读取缓存
     * @param id
     * @return
     */
    @Cacheable(cacheNames = CACHE_NAME)
    public Product readCache(String id) {
        Log.info("rpc查询单个产品, 请求 : {}", id);
        Product product = productRpc.findOne(id);
        Log.info("rpc查询单个产品, 结果 : {}", product);
        return product;
    }

    /**
     * 更新缓存(方法每次都会执行，product.id作为key,Product为value放入缓存)
     * @param product
     * @return
     */
    @CachePut(cacheNames = CACHE_NAME, key = "#product.id")
    public Product putCache(Product product) {
        return product;
    }

    /**
     * 清除缓存
     * @param id
     */
    @CacheEvict(cacheNames = CACHE_NAME)
    public void removeCache(String id) {

    }

    /**
     * 读取所有缓存
     * @return
     */
    public List<Product> readAllCache() {
        Map map = hazelcastInstance.getMap(CACHE_NAME);
        List<Product> products = null;
        if (map.size() > 0) {
            products = new ArrayList<>();
            products.addAll(map.values());
        } else {
            products = findAll();
        }
        return products;
    }

    /**
     * 查询全部产品
     * @return
     */
    public List<Product> findAll() {
        ProductRpcReq req = new ProductRpcReq();
        List<String> status = new ArrayList<>();
        status.add(ProductStatus.IN_SELL.name());
        req.setStatusList(status);
        Log.info("rpc查询全部产品, 请求 : {}", req);
        List<Product> productList = productRpc.query(req);
        Log.info("rpc查询全部产品, 结果 : {}", req);
        return productList;
    }

}
