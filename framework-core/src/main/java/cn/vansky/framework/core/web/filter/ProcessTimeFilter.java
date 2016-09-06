/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.web.filter;

import cn.vansky.framework.core.web.util.RequestUtils;
import cn.vansky.framework.core.web.util.UrlUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * record request time
 * Author: CK
 * Date:2014/10/6
 */
public class ProcessTimeFilter implements Filter {
    protected final Logger logger = LoggerFactory.getLogger(ProcessTimeFilter.class);

    public static final String START_TIME = ProcessTimeFilter.class.getName() + ".START_TIME";

    private String excludePath;

    private SortedSet<String> excludePathSet;

    public void init(FilterConfig filterConfig) throws ServletException {
        excludePath = filterConfig.getInitParameter("excludePath");
        excludePathSet = new TreeSet<String>();
        if (excludePath != null) {
            String[] paths = excludePath.split(";");
            for (String p : paths) {
                if (!StringUtils.isBlank(p)) {
                    excludePathSet.add(p);
                }
            }
        }
        logger.info("ProcessTimeFilter init");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // 如果在excludePath中，则跳过
        if (StringUtils.isNotBlank(UrlUtils.urlMatch(excludePathSet, RequestUtils.getReqPath(httpRequest)))) {
            chain.doFilter(request, response);
            return;
        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        request.setAttribute(START_TIME, stopWatch);
        chain.doFilter(request, response);
        stopWatch.stop();
        if (null != request.getAttribute(START_TIME)) {
            logger.info("URL[" + httpRequest.getRequestURI() + "]executeTime[" + stopWatch.toString() + "]");
        }
    }

    public void destroy() {
        logger.info("ProcessTimeFilter destroy");
    }
}
