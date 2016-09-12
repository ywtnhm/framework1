/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.util;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/9/12
 */
public class Audi extends Bmw {

    public Audi() {
        this.type = "奥迪";
    }

    public void get() {
        System.out.println(type);
    }
}
