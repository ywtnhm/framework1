/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.security.symmetry;

import cn.vansky.security.ISecurity;
import cn.vansky.security.symmetry.PBE;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/4/13
 */
public class PBETest {
    public static void main(String[] args) {
        String key = "efg";
        ISecurity pbe = new PBE(key.getBytes());
        String plaintext = "root";
        byte[] encryption = pbe.encrypt(plaintext.getBytes());
        System.out.println(new String(encryption));
        System.out.println(new String(pbe.decrypt(encryption)));
    }
}
