/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.common.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class Num62Test {

    @Test
    public void testLongToN62() throws Exception {
        System.out.println(Num62.longToN62(Long.MAX_VALUE));
    }
}