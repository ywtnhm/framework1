/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.security.dissymmetry;

import cn.vansky.security.TSecurity;
import cn.vansky.security.dissymmetry.DSA;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/4/13
 */
public class DSATest {
    public static void main(String[] args) {
        TSecurity tSecurity = new DSA();
        String inputStr = "abc";

        // 构建密钥
        Map<String, Object> keyMap = tSecurity.initKey();

        // 获得密钥
        String publicKey = tSecurity.getPublicKey();
        String privateKey = tSecurity.getPrivateKey();

        System.out.println("公钥:" + publicKey);
        System.out.println("私钥:" + privateKey);

        // 产生签名
        String sign = tSecurity.sign(inputStr, privateKey);
        System.out.println("签名:" + sign);

        // 验证签名
        boolean status = tSecurity.verify(inputStr, publicKey, sign);
        System.out.println("状态:" + status);
    }
}
