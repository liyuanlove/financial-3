package com.imooc.util.configuration;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * jsonrpc自动化配置
 * Created by songyouyu on 2018/6/15
 */
@Configuration
public class JSONRpcConfiguration {

    private static Logger Log = LoggerFactory.getLogger(JSONRpcConfiguration.class);

    @Bean
    public AutoJsonRpcServiceImplExporter autoJsonRpcServiceImplExporter() {
        AutoJsonRpcServiceImplExporter exp =  new AutoJsonRpcServiceImplExporter();
        return exp;
    }

    @Bean
    @ConditionalOnProperty(value = {"rpc.client.url","rpc.client.basePackage"})
    public AutoJsonRpcClientProxyCreator rpcClientProxyCreator(@Value("${rpc.client.url}") String url,
                                                               @Value("${rpc.client.basePackage}") String basePackage) {
        AutoJsonRpcClientProxyCreator creator = new AutoJsonRpcClientProxyCreator();
        try {
            creator.setBaseUrl(new URL(url));
        } catch (MalformedURLException e) {
            Log.error("创建rpc地址服务错误", e);
        }
        creator.setScanPackage(basePackage);
        return creator;
    }

}
