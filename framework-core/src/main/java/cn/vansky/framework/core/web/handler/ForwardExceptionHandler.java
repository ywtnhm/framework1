/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.web.handler;

import cn.vansky.exception.web.ForwardException;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回错误页面
 * Auth: CK
 * Date: 2016/9/9
 */
public class ForwardExceptionHandler implements CustomizeExceptionHandler {

    public boolean support(Exception ex, Class clazz) {
        return ex instanceof ForwardException
                || ModelAndView.class.isAssignableFrom(clazz)
                || ModelMap.class.isAssignableFrom(clazz);
    }

    public ModelAndView deal(HttpServletRequest request, HttpServletResponse response, Object handler,
                             Exception ex, CustomizeExceptionHandlerResolver resolver) {
        // 把漏网的异常信息记入日志
        Map<String, Object> map = new HashMap<String, Object>(1);
        String message = ex.getMessage();
        if (message == null) {
            Throwable e = ex.getCause();
            message = resolver.getMessage(e);
        }
        // 将错误信息传递给view
        map.put("errorMsg", message);
        return new ModelAndView("error", map);
    }
}
