/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.common.util.code;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CoderTest {

    @Test
    public void testASCIIToEBCDIC() throws Exception {
        byte []c  = new byte[]{1,2,3,4};
        byte a[] = Coder.ASCIIToEBCDIC(c);
        for (byte b : a) {
            System.out.println(b);
        }
    }
}