package com.imooc.manager.repository;

import com.imooc.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 产品管理
 * Created by songyouyu on 2018/6/13
 */
public interface ProductRepository extends JpaRepository<Product, String>,
        JpaSpecificationExecutor<Product> {


}
