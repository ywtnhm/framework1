/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.common.util.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/6/30.
 */
public class CommonProxy<T> implements InvocationHandler {

    private ProxyInterface proxyInterface;

    private T target;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        proxyInterface.before();
        result = method.invoke(this.target, args);
        proxyInterface.after();
        return result;
    }

    @SuppressWarnings("unchecked")
    public T bind(T target, ProxyInterface proxyInterface) {
        this.proxyInterface = proxyInterface;
        this.target = target;
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
