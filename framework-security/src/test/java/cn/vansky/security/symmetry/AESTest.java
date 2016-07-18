/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.security.symmetry;

import cn.vansky.framework.common.util.ISOUtil;
import cn.vansky.security.ISecurity;
import cn.vansky.security.symmetry.AES;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/4/13
 */
public class AESTest {
    @Test
    public void encrypt() {
        ISecurity aes = new AES("1111111111111111".getBytes());
        String data = "root1";
        byte[] encryption = aes.encrypt(data.getBytes());
        System.out.println(new String(encryption));
        System.out.println(new String(aes.decrypt(encryption)));
    }

    @Test
    public void encrypt1() {
        ISecurity aes = new AES("2016080901161445".getBytes());
        byte [] data = "000000000000A43428481AE64383964A1".getBytes();
        byte [] encrypt = aes.encrypt(data);
        System.out.println(ISOUtil.hexString(encrypt));
        byte [] decrypt = aes.decrypt(encrypt);
        System.out.println(new String(decrypt));
    }
}
