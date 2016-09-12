/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.util;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.interceptor.AsyncExecutionInterceptor;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
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
     *
     * @param proxy 代理对象
     * @param <T>   强转
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

    @SuppressWarnings("all")
    public static <T> T getRealTarget(Object proxy) {
        ConfigurablePropertyAccessor accessor;
        if (isMultipleProxy(proxy)) {
            try {
                InvocationHandler handler = Proxy.getInvocationHandler(proxy);
                accessor = PropertyAccessorFactory.forDirectFieldAccess(handler);
                AdvisedSupport advised = (AdvisedSupport) accessor.getPropertyValue("advised");
                TargetSource targetSource = advised.getTargetSource();

                accessor = PropertyAccessorFactory.forDirectFieldAccess(targetSource.getTarget());
                Object cglib$CALLBACK_0 = accessor.getPropertyValue("CGLIB$CALLBACK_0");
                accessor = PropertyAccessorFactory.forDirectFieldAccess(cglib$CALLBACK_0);
                advised = (AdvisedSupport) accessor.getPropertyValue("advised");
                targetSource = advised.getTargetSource();

                T obj = null;
                obj = (T) targetSource.getTarget();
                return obj;
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            return getProxy(proxy);
        }
        return null;
    }

    @SuppressWarnings("all")
    public static boolean isMultipleProxy(Object proxy) {
        try {
            ProxyFactory proxyFactory = null;
            if (AopUtils.isJdkDynamicProxy(proxy)) {
                proxyFactory = findJdkDynamicProxyFactory(proxy);
            }
            if (AopUtils.isCglibProxy(proxy)) {
                proxyFactory = findCglibProxyFactory(proxy);
            }
            TargetSource targetSource = (TargetSource) ReflectionUtils.getField(ProxyFactory_targetSource_FIELD, proxyFactory);
            return AopUtils.isAopProxy(targetSource.getTarget());
        } catch (Exception e) {
            throw new IllegalArgumentException("proxy args maybe not proxy with cglib or jdk dynamic proxy. this method not support", e);
        }
    }

    public static ProxyFactory findJdkDynamicProxyFactory(final Object proxy) {
        InvocationHandler h = Proxy.getInvocationHandler(proxy);
        return (ProxyFactory) ReflectionUtils.getField(JdkDynamicAopProxy_advised_FIELD, h);
    }

    public static ProxyFactory findCglibProxyFactory(final Object proxy) {
        Field field = ReflectionUtils.findField(proxy.getClass(), "CGLIB$CALLBACK_0");
        ReflectionUtils.makeAccessible(field);
        Object CGLIB$CALLBACK_0 = ReflectionUtils.getField(field, proxy);
        return (ProxyFactory) ReflectionUtils.getField(CglibAopProxy$DynamicAdvisedInterceptor_advised_FIELD, CGLIB$CALLBACK_0);

    }

    /**
     * 查看指定的代理对象是否 添加事务切面
     * see http://jinnianshilongnian.iteye.com/blog/1850432
     *
     * @param proxy
     * @return
     */
    public static boolean isTransactional(Object proxy) {
        return hasAdvice(proxy, TransactionInterceptor.class);
    }

    /**
     * 移除代理对象的异步调用支持
     *
     * @return
     */
    public static void removeTransactional(Object proxy) {
        removeAdvisor(proxy, TransactionInterceptor.class);
    }

    /**
     * 是否是异步的代理
     *
     * @param proxy
     * @return
     */
    public static boolean isAsync(Object proxy) {
        return hasAdvice(proxy, AsyncExecutionInterceptor.class);
    }

    /**
     * 移除代理对象的异步调用支持
     *
     * @return
     */
    public static void removeAsync(Object proxy) {
        removeAdvisor(proxy, AsyncExecutionInterceptor.class);
    }

    private static void removeAdvisor(Object proxy, Class<? extends Advice> adviceClass) {
        if (!AopUtils.isAopProxy(proxy)) {
            return;
        }
        ProxyFactory proxyFactory = null;
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            proxyFactory = findJdkDynamicProxyFactory(proxy);
        }
        if (AopUtils.isCglibProxy(proxy)) {
            proxyFactory = findCglibProxyFactory(proxy);
        }

        Advisor[] advisors = proxyFactory.getAdvisors();

        if (advisors == null || advisors.length == 0) {
            return;
        }

        for (Advisor advisor : advisors) {
            if (adviceClass.isAssignableFrom(advisor.getAdvice().getClass())) {
                proxyFactory.removeAdvisor(advisor);
                break;
            }
        }
    }

    private static boolean hasAdvice(Object proxy, Class<? extends Advice> adviceClass) {
        if (!AopUtils.isAopProxy(proxy)) {
            return false;
        }
        ProxyFactory proxyFactory = null;
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            proxyFactory = findJdkDynamicProxyFactory(proxy);
        }
        if (AopUtils.isCglibProxy(proxy)) {
            proxyFactory = findCglibProxyFactory(proxy);
        }
        Advisor[] advisors = proxyFactory.getAdvisors();

        if (advisors == null || advisors.length == 0) {
            return false;
        }

        for (Advisor advisor : advisors) {
            if (adviceClass.isAssignableFrom(advisor.getAdvice().getClass())) {
                return true;
            }
        }
        return false;
    }

    ///////////////////////////////////内部使用的反射 静态字段///////////////////////////////////
    //JDK动态代理 字段相关
    private static Field JdkDynamicProxy_h_FIELD;
    private static Class JdkDynamicAopProxy_CLASS;
    private static Field JdkDynamicAopProxy_advised_FIELD;

    //CGLIB代理 相关字段
    private static Class CglibAopProxy_CLASS;
    private static Class CglibAopProxy$DynamicAdvisedInterceptor_CLASS;
    private static Field CglibAopProxy$DynamicAdvisedInterceptor_advised_FIELD;

    //ProxyFactory 相关字段
    private static Class ProxyFactory_CLASS;
    private static Field ProxyFactory_targetSource_FIELD;

    static {
        JdkDynamicProxy_h_FIELD = ReflectionUtils.findField(Proxy.class, "h");
        ReflectionUtils.makeAccessible(JdkDynamicProxy_h_FIELD);

        try {
            JdkDynamicAopProxy_CLASS = Class.forName("org.springframework.aop.framework.JdkDynamicAopProxy");
            JdkDynamicAopProxy_advised_FIELD = ReflectionUtils.findField(JdkDynamicAopProxy_CLASS, "advised");
            ReflectionUtils.makeAccessible(JdkDynamicAopProxy_advised_FIELD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            /*ignore*/
        }

        try {
            CglibAopProxy_CLASS = Class.forName("org.springframework.aop.framework.CglibAopProxy");
            CglibAopProxy$DynamicAdvisedInterceptor_CLASS = Class.forName("org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor");
            CglibAopProxy$DynamicAdvisedInterceptor_advised_FIELD = ReflectionUtils.findField(CglibAopProxy$DynamicAdvisedInterceptor_CLASS, "advised");
            ReflectionUtils.makeAccessible(CglibAopProxy$DynamicAdvisedInterceptor_advised_FIELD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            /*ignore*/
        }

        ProxyFactory_CLASS = ProxyFactory.class;
        ProxyFactory_targetSource_FIELD = ReflectionUtils.findField(ProxyFactory_CLASS, "targetSource");
        ReflectionUtils.makeAccessible(ProxyFactory_targetSource_FIELD);

    }
}
