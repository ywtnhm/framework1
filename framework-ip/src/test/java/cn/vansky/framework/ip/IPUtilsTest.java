/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.ip;

import org.testng.annotations.Test;

public class IPUtilsTest {

    @Test
    public void testGetIPLocation() throws Exception {
        IPLocation ipLocation = IPUtils.getIPLocation("123.57.60.214");
        System.out.println(ipLocation.getCountry());
        System.out.println(ipLocation.getArea());
    }
}