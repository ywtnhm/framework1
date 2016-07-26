/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.common.util;

import cn.vansky.framework.common.constant.Constant;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * <p>公共计算</p>
 * Author: CK
 * Date: 2015/3/27.
 */
public class AmountCountUtil {

    /**
     * 加法(默认，精度=2)
     * 123 + 234 = 357.0
     *
     * @param v1    如: (123)
     * @param v2    如: (234)
     * @return double
     */
    public static double add(double v1, double v2) {
        return add(v1, v2, Constant.DEFAULT_SCALE);
    }

    public static double add(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return round(b1.add(b2), scale);
    }

    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
        return add(v1, v2, Constant.DEFAULT_SCALE);
    }

    public static BigDecimal add(BigDecimal v1, BigDecimal v2, int scale) {
        return round1(v1.add(v2), scale);
    }

    /**
     * 减法(默认，精度=2)
     * 123 - 234 = -111.0
     *
     * @param v1    如:  (123)
     * @param v2    如:  (234)
     * @return double
     */
    public static double sub(double v1, double v2) {
        return sub(v1, v2, Constant.DEFAULT_SCALE);
    }

    public static double sub(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return round(b1.subtract(b2), scale);
    }

    public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
        return sub(v1, v2, Constant.DEFAULT_SCALE);
    }

    public static BigDecimal sub(BigDecimal v1, BigDecimal v2, int scale) {
        return round1(v1.subtract(v2), scale);
    }

    /**
     * 乘法(默认，精度=2)
     * 10 * 23 = 230.0
     *
     * @param v1    如: (10)
     * @param v2    如: (23)
     * @return double
     */
    public static double mul(double v1, double v2) {
        return mul(v1, v2, Constant.DEFAULT_SCALE);
    }

    public static double mul(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return round(b1.multiply(b2), scale);
    }

    public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
        return mul(v1, v2, Constant.DEFAULT_SCALE);
    }

    public static BigDecimal mul(BigDecimal v1, BigDecimal v2, int scale) {
        return round1(v1.multiply(v2), scale);
    }

    /**
     * 除法(默认，精度=2)
     * 23 * 10 = 2.3
     *
     * @param v1    如: (23)
     * @param v2    如: (10)
     * @return double
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, Constant.DEFAULT_SCALE);
    }

    public static double div(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return round(b1.divide(b2, BigDecimal.ROUND_HALF_UP), scale);
    }

    public static BigDecimal div(BigDecimal v1, BigDecimal v2) {
        return div(v1, v2, Constant.DEFAULT_SCALE);
    }

    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale) {
        return v1.divide(v2, scale, BigDecimal.ROUND_HALF_UP);
    }

    private static double round(BigDecimal value, int scale) {
        return round1(value, scale).doubleValue();
    }

    private static BigDecimal round1(BigDecimal value, int scale) {
        return value.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 大数值转换
     *
     * @param val Object
     */
    public static BigDecimal formatAmt(Object val) {
        BigDecimal bigDecimal = null;
        if (null == val) {
            bigDecimal = BigDecimal.ZERO;
        } else if (val instanceof Double) {
            bigDecimal = new BigDecimal((Double) val);
        } else if (val instanceof String) {
            bigDecimal = new BigDecimal((String) val);
        } else if (val instanceof Long) {
            bigDecimal = new BigDecimal((Long) val);
        } else if (val instanceof Integer) {
            bigDecimal = new BigDecimal((Integer) val);
        } else if (val instanceof BigInteger) {
            bigDecimal = new BigDecimal((BigInteger) val);
        }
        return bigDecimal.setScale(Constant.DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP);
    }
}