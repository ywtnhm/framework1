/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.util;


import org.testng.annotations.Test;

public class JEmptyTest {

    @Test
    public void empty() {
        String json = JsonResp.asEmpty().error("我是错误信息").toJson();
        System.out.println(json);
    }
}