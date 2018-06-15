package com.imooc.api;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.imooc.api.domain.ParamInf;
import com.imooc.entity.Product;

import java.util.List;

/**
 * 产品相关的rpc
 * Created by songyouyu on 2018/6/14
 */
@JsonRpcService
public interface ProductRpc {

    /**
     * 查询多个产品
     * @param req
     * @return
     */
    List<Product> query(ParamInf req);

    /**
     * 查询单个产品
     * @param id
     * @return
     */
    Product findOne(String id);

}
