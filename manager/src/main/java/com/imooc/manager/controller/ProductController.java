package com.imooc.manager.controller;

import com.imooc.entity.Product;
import com.imooc.manager.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 产品服务controller
 * Created by songyouyu on 2018/6/13
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private static Logger Log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    /**
     * 新增产品
     * @param product
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product) {
        Log.info("创建产品，参数 : {}", product);
        Product result = productService.addProduct(product);
        Log.info("创建产品，结果 : {}", product);
        return result;
    }

    /**
     * 查询单个产品
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product selectProduct(@PathVariable String id) {
        Log.info("查询单个产品, id : {}", id);

        Product product = productService.selectProduct(id);

        Log.info("查询单个产品, 结果 : {}", product);

        return product;
    }

    /**
     * 分页查询产品
     * @param ids
     * @param minRewardRate
     * @param maxRewardRate
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<Product> selectProductList(String ids,
                                           BigDecimal minRewardRate,
                                           BigDecimal maxRewardRate,
                                           String status,
                                           @RequestParam(defaultValue = "0") int pageNum,
                                           @RequestParam(defaultValue = "10") int pageSize) {
        Log.info("查询产品, ids : {}, minRewardRate : {}, maxRewardRate : {}, status : {}",
                ids, minRewardRate, maxRewardRate, status);
        List<String> idList = null, statusList = null;
        if (!StringUtils.isEmpty(ids)) {
            idList = Arrays.asList(ids.split(","));
        }
        if (!StringUtils.isEmpty(status)) {
            statusList = Arrays.asList(status.split(","));
        }
        Pageable pageable = new PageRequest(pageNum, pageSize);
        Page<Product> page = productService.selectProductList(idList,
                minRewardRate, maxRewardRate, statusList, pageable);

        Log.info("查询产品, 结果 : {}", page);
        return page;
    }

}
