package com.imooc.seller.service;

import com.imooc.api.ProductRpc;
import com.imooc.api.domain.ProductRpcReq;
import com.imooc.entity.Product;
import com.imooc.entity.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品相关服务
 * Created by songyouyu on 2018/6/14
 */
@Service
public class ProductRpcService {

    private static Logger log = LoggerFactory.getLogger(ProductRpcService.class);

    @Autowired
    private ProductRpc productRpc;

    /**
     * 查询所有产品
     * @return
     */
    public List<Product> findAll() {
        ProductRpcReq req = new ProductRpcReq();
        List<String> status = new ArrayList<>();
        status.add(ProductStatus.IN_SELL.name());
        req.setStatusList(status);
        log.info("rpc查询全部产品, 请求 : {}", req);
        List<Product> productList = productRpc.query(req);
        log.info("rpc查询全部产品, 结果 : {}", req);
        return productList;
    }

    /**
     * 查询单个产品
     * @param id
     * @return
     */
    public Product findOne(String id) {
        log.info("rpc查询单个产品, 请求 : {}", id);
        Product product = productRpc.findOne(id);
        log.info("rpc查询单个产品, 结果 : {}", product);
        return product;
    }

    @PostConstruct
    public void test() {
        findAll();
    }
}
