/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.common.util;

import org.testng.annotations.Test;

import java.math.BigDecimal;

public class AmountCountUtilTest {

    @Test
    public void testAdd() throws Exception {
        System.out.println(AmountCountUtil.add(123, 234));
    }

    @Test
    public void testAdd1() throws Exception {
        BigDecimal v1 = new BigDecimal(123);
        BigDecimal v2 = new BigDecimal(234);
        System.out.println(AmountCountUtil.add(v1, v2).toString());
    }

    @Test
    public void testSub() throws Exception {
        System.out.println(AmountCountUtil.sub(123, 234));
    }

    @Test
    public void testSub1() throws Exception {
        BigDecimal v1 = new BigDecimal(123);
        BigDecimal v2 = new BigDecimal(234);
        System.out.println(AmountCountUtil.sub(v1, v2).toString());
    }

    @Test
    public void testMul() throws Exception {
        System.out.println(AmountCountUtil.mul(10, 23));
    }

    @Test
    public void testMul1() throws Exception {
        BigDecimal v1 = new BigDecimal(10);
        BigDecimal v2 = new BigDecimal(23);
        System.out.println(AmountCountUtil.mul(v1, v2).toString());
    }

    @Test
    public void testDiv() throws Exception {
        System.out.println(AmountCountUtil.div(23, 10));
    }

    @Test
    public void testDiv1() throws Exception {
        BigDecimal v1 = new BigDecimal(23);
        BigDecimal v2 = new BigDecimal(10);
        System.out.println(AmountCountUtil.div(v1, v2).toString());
    }

    @Test
    public void testFormatAmt() throws Exception {
        System.out.println(AmountCountUtil.formatAmt(Double.valueOf(234)).toString());
    }
}