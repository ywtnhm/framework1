package cn.vansky.framework.core.web.filter;

import cn.vansky.framework.core.web.ThreadLocalInfo;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/8/17
 */
public class FrameWorkFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ThreadLocalInfo.set(request, response);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
