package com.imooc.entity.enums;

/**
 * Created by songyouyu on 2018/6/13
 */
public enum OrderStatus {

    INIT("初始化"),
    PROCESS("处理中"),
    SUCCESS("成功"),
    FAIL("失败");

    private String desc;

    OrderStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
