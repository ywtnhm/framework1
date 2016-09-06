/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.common.util.proxy;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/8/2
 */
public class ProxyInterfaceImpl implements ProxyInterface {

    public void before() {
        System.out.println("测试前");
    }

    public void after() {
        System.out.println("测试后");
    }
}
