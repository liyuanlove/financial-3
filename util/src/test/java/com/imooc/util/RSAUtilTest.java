package com.imooc.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by songyouyu on 2018/6/16
 */
public class RSAUtilTest {

    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDjXs83wZtxArOtWYbg/4yowTOj\n" +
            "t1rQwhj8MtF4d5dzsbfhP5MTYg6Ljt/68/prPfaJCs6fchbxzmOjxg+77FutSM7j\n" +
            "QR38ED+CbFTIxm4Ii9hXZJiQDijsuIy0eaFsSS/X2AVm8ZJaiwZR8sYptKvJGXnx\n" +
            "JNz/oTTDgLwOSG5lvwIDAQAB";
    private static final String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAONezzfBm3ECs61Z\n" +
            "huD/jKjBM6O3WtDCGPwy0Xh3l3Oxt+E/kxNiDouO3/rz+ms99okKzp9yFvHOY6PG\n" +
            "D7vsW61IzuNBHfwQP4JsVMjGbgiL2FdkmJAOKOy4jLR5oWxJL9fYBWbxklqLBlHy\n" +
            "xim0q8kZefEk3P+hNMOAvA5IbmW/AgMBAAECgYEAq+RfcF2UMGnEgMdwKuXH50S4\n" +
            "z4qsw6oZzYB10EdJXpP62fWAMWUubd+EAWs15KGhopVjBDPymLhZp9TyzDWyT/Al\n" +
            "sxpJ8uGRrhlVSa2GOYqcQhNXjYmLVbngIiwbuu3lY72puHvTTSo3U1TLj9wHQjYf\n" +
            "gtNvEtwbO6WROFd8kGECQQD1SPfaU82DHCmDXRFJ5kks67SN57GqD5uhYCpyTH3S\n" +
            "57T5adpiFMg9d+P5f8JGLIHZG8YCqVpGuMRwk70Ks1HpAkEA7U2ALYEAfvUIZNeq\n" +
            "k0aJGoFOLPWqtLLB23yLH25GZ6W97v/2TxdYGFBd71w5x2SyzyQ4x5sGJ5mf7Pyq\n" +
            "72VJZwJBAISjK5v9OyGN5gjqF8I9bA6FewJNau5KOF9AcFOUd2nH8D369qOZF1rd\n" +
            "7QJrCrQFt0We1il0SKP7oAY8bbpOk5kCQHZtvjF3dwrlOOoy47MaeaQbsisn86i4\n" +
            "qZzjW/80prhldBPJ9F/mc5W2zhp9+Wv0anSMTDX5vSmYNsMsGhbdgrMCQFs1KIS4\n" +
            "iOWInAU/HY59uCULyHaK3ZOq8beaWC0z7HwfQ05+oBavd3LAtSTblg9QzH3dTG0P\n" +
            "B9dqK4LuZ9pE888=";


    @Test
    public void test() {
        String text = "{\"amount\":10,\"chanId\":\"123\",\"chanUserId\":\"123\",\"createAt\":\"2018-12-365 09:41:28\",\"memo\":\"123\",\"outerOrderId\":\"123\",\"productId\":\"001\"}";
        String sign = RSAUtil.sign(text, privateKey);
        System.out.println(sign);
        System.out.println(RSAUtil.verify(text, sign, publicKey));

    }


}