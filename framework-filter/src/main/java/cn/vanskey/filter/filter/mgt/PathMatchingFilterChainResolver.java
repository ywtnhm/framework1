/*
 * Copyright (C) 2016 hyssop, Inc. All Rights Reserved.
 */

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package cn.vanskey.filter.filter.mgt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.vanskey.filter.util.AntPathMatcher;
import cn.vanskey.filter.util.PatternMatcher;
import cn.vanskey.filter.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class PathMatchingFilterChainResolver implements FilterChainResolver {

    private static transient final Logger log = LoggerFactory.getLogger(PathMatchingFilterChainResolver.class);

    private FilterChainManager filterChainManager;

    private PatternMatcher pathMatcher;

    public PathMatchingFilterChainResolver() {
        this.pathMatcher = new AntPathMatcher();
        this.filterChainManager = new DefaultFilterChainManager();
    }

    public PathMatchingFilterChainResolver(FilterConfig filterConfig) {
        this.pathMatcher = new AntPathMatcher();
        this.filterChainManager = new DefaultFilterChainManager(filterConfig);
    }

    /**
     * @return the {@code PatternMatcher} used when determining if an incoming request's path
     * matches a configured filter chain.
     */
    public PatternMatcher getPathMatcher() {
        return pathMatcher;
    }

    /**
     * @param pathMatcher the {@code PatternMatcher} used when determining if an incoming request's path
     *                    matches a configured filter chain.
     */
    public void setPathMatcher(PatternMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    public FilterChainManager getFilterChainManager() {
        return filterChainManager;
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public void setFilterChainManager(FilterChainManager filterChainManager) {
        this.filterChainManager = filterChainManager;
    }

    public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originalChain) {
        FilterChainManager filterChainManager = getFilterChainManager();
        if (!filterChainManager.hasChains()) {
            return null;
        }

        String requestURI = getPathWithinApplication(request);

        //the 'chain names' in this implementation are actually path patterns defined by the user.  We just use them
        //as the chain name for the FilterChainManager's requirements
        for (String pathPattern : filterChainManager.getChainNames()) {

            // If the path does match, then pass on to the subclass implementation for specific checks:
            if (pathMatches(pathPattern, requestURI)) {
                if (log.isTraceEnabled()) {
                    log.trace("Matched path pattern [" + pathPattern + "] for requestURI [" + requestURI + "].  " +
                            "Utilizing corresponding filter chain...");
                }
                return filterChainManager.proxy(originalChain, pathPattern);
            }
        }

        return null;
    }

    /**
     * @param pattern the pattern to match against
     * @param path    the value to match with the specified {@code pattern}
     * @return {@code true} if the request {@code path} matches the specified filter chain url {@code pattern},
     * {@code false} otherwise.
     */
    protected boolean pathMatches(String pattern, String path) {
        PatternMatcher pathMatcher = getPathMatcher();
        return pathMatcher.matches(pattern, path);
    }

    /**
     * @param request the incoming {@code ServletRequest}
     * @return the request's path within the appliation.
     */
    protected String getPathWithinApplication(ServletRequest request) {
        return WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
    }
}
