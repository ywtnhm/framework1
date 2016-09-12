/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.util;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/9/12
 */
public class Bmw implements Car {
    public Bmw() {
        this.type = "宝马";
    }

    protected String type;

    public void get() {
        System.out.println(type);
    }
}
