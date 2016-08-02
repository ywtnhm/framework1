/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.common.util.proxy;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommonProxyTest {

    @Test
    public void testBind() throws Exception {
        ProxyInterface proxyInterface = new ProxyInterfaceImpl();
        TestService service = new TestServiceImpl();
        TestService s = new CommonProxy<TestService>().bind(service, proxyInterface);
        s.test();
    }
}