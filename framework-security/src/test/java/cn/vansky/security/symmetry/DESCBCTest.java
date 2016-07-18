/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.security.symmetry;

import cn.vansky.security.ISecurity;
import cn.vansky.security.symmetry.DESCBC;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/4/13
 */
public class DESCBCTest {
    public static void main(String[] args) {
        ISecurity desSecurity = new DESCBC("12345678".getBytes(),"admin@va".getBytes());
        byte[] encryption = desSecurity.encrypt("asdf".getBytes());
        System.out.println(new String(encryption));
        System.out.println(new String(desSecurity.decrypt(encryption)));
    }
}
