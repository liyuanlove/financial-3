package com.imooc.seller.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 签名服务
 * Created by songyouyu on 2018/6/16
 */
@Service
public class SignService {

    static Map<String,String> PUBLIC_KEYS = new HashMap<>();

    static {
        PUBLIC_KEYS.put("1000","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDjXs83wZtxArOtWYbg/4yowTOj\n" +
                "t1rQwhj8MtF4d5dzsbfhP5MTYg6Ljt/68/prPfaJCs6fchbxzmOjxg+77FutSM7j\n" +
                "QR38ED+CbFTIxm4Ii9hXZJiQDijsuIy0eaFsSS/X2AVm8ZJaiwZR8sYptKvJGXnx\n" +
                "JNz/oTTDgLwOSG5lvwIDAQAB");
    }

    /**
     * 根据授权编号获取公钥
     * @param authId
     * @return
     */
    public String getPublicKey(String authId){
        return PUBLIC_KEYS.get(authId);
    }


}
