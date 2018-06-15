package com.imooc.manager;

import com.imooc.swagger.EnableMySwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Created by songyouyu on 2018/6/13
 */
@SpringBootApplication
@EntityScan(basePackages = {"com.imooc.entity"})
// 第一种添加swagger方式
//@Import(SwaggerConfiguration.class)
//第二种方式(定义注解)
//@EnableMySwagger
public class ManagerApp {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApp.class);
    }

}
