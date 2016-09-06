/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.bean.web;

import cn.vansky.framework.core.bean.ServiceLocator;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/6/24
 */
public class InitSystemListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        ServiceLocator.getInstance().setFactory(context);
    }

    public void contextDestroyed(ServletContextEvent event) {
        ServiceLocator.getInstance().setFactory(null);
    }
}
