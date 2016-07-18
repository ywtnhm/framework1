/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.security.single;

import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/4/13
 */
public class MD5Test {

    @Test
    public void main() {
        String s = MD5.digest("aaa".getBytes());
        System.out.println(s);
    }
}
