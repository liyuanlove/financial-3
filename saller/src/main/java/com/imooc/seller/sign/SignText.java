package com.imooc.seller.sign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.imooc.util.JsonUtil;

/**
 * 签名明文
 * Created by songyouyu on 2018/6/16
 */
//这两个接口会自动被实现该接口的类继承
//进行json序列化时包含非空字段
@JsonInclude(JsonInclude.Include.NON_NULL)
//字典排序
@JsonPropertyOrder(alphabetic = true)
public interface SignText {

    // java8
    default String toText(){
        return JsonUtil.toJson(this);
    }

}
