package com.imooc.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 销售端启动类
 * Created by songyouyu on 2018/6/14
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.imooc.api", "com.imooc.seller"})
public class SellerApp {

    public static void main(String[] args) {
        SpringApplication.run(SellerApp.class);
    }

}
