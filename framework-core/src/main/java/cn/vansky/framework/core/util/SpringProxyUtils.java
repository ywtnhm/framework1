/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.util;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * spring代理操作类
 * Auth: CK
 * Date: 2016/9/11
 */
public class SpringProxyUtils {

    /**
     * 通过代理对象获取被代理对象
     * @param proxy 代理对象
     * @param <T> 强转
     * @return 被代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getProxy(Object proxy) {
        ConfigurablePropertyAccessor accessor;
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            // jdk proxy
            InvocationHandler handler = Proxy.getInvocationHandler(proxy);
            accessor = PropertyAccessorFactory.forDirectFieldAccess(handler);
        } else {
            // cglib
            accessor = PropertyAccessorFactory.forDirectFieldAccess(proxy);
            Object cglib$CALLBACK_0 = accessor.getPropertyValue("CGLIB$CALLBACK_0");
            accessor = PropertyAccessorFactory.forDirectFieldAccess(cglib$CALLBACK_0);
        }
        AdvisedSupport advised = (AdvisedSupport) accessor.getPropertyValue("advised");
        TargetSource targetSource = advised.getTargetSource();
        T obj = null;
        try {
            obj = (T) targetSource.getTarget();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
