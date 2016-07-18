/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.security.single;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/4/13
 */
public class SHATest {
    public static void main(String[] args) {
        String s = SHA.digest("aaa".getBytes());
        System.out.println(s);
    }
}
