/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.util;

import org.springframework.aop.Advisor;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adapter.AdvisorAdapterRegistry;
import org.springframework.aop.framework.adapter.GlobalAdvisorAdapterRegistry;
import org.springframework.aop.target.SingletonTargetSource;
import org.springframework.util.ClassUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SpringProxyUtilsTest {

    AdvisorAdapterRegistry advisorAdapterRegistry;

    @BeforeMethod
    public void setUp() throws Exception {
        advisorAdapterRegistry = GlobalAdvisorAdapterRegistry.getInstance();

    }

    @Test
    public void testGetProxy() throws Exception {
        jdk();
        cglib();
    }

    public void jdk() {
        ProxyFactory proxyFactory = new ProxyFactory();

        Bmw bmw = new Bmw();
        TargetSource targetSource = new SingletonTargetSource(bmw);
        proxyFactory.setTargetSource(targetSource);

        Class<?>[] targetInterfaces = ClassUtils.getAllInterfacesForClass(bmw.getClass());
        for (Class<?> targetInterface : targetInterfaces) {
            proxyFactory.addInterface(targetInterface);
        }

        Advisor[] advisors = new Advisor[1];
        advisors[0] = advisorAdapterRegistry.wrap(new TestMethodInterceptor());
        proxyFactory.addAdvisors(advisors);

        Car proxy = (Car) proxyFactory.getProxy();
        Bmw obj = SpringProxyUtils.getProxy(proxy);
        Assert.assertEquals(bmw, obj);
    }

    public void cglib() {
        ProxyFactory proxyFactory = new ProxyFactory();

        Audi audi = new Audi();
        TargetSource targetSource = new SingletonTargetSource(audi);
        proxyFactory.setTargetSource(targetSource);

        Advisor[] advisors = new Advisor[1];
        advisors[0] = advisorAdapterRegistry.wrap(new TestMethodInterceptor());
        proxyFactory.addAdvisors(advisors);

        Audi proxy = (Audi) proxyFactory.getProxy();
        Audi obj = SpringProxyUtils.getProxy(proxy);
        Assert.assertEquals(audi, obj);
    }
}