package cn.vansky.framework.core.web.handler;

import cn.vansky.exception.BaseException;
import cn.vansky.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一调到错误页面
 * Author: CK
 * Date: 2016/2/2
 */
public class ForwardExceptionHandlerResolver extends CustomizeExceptionHandlerResolver {
    private static final Logger logger = LoggerFactory.getLogger(ForwardExceptionHandlerResolver.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 把漏网的异常信息记入日志
        logger.error("Catch Exception: ",ex);
        Map<String,Object> map = new HashMap<String,Object>(1);
        // 将错误信息传递给view
        map.put("errorMsg", getMessage(ex));
        return new ModelAndView("error", map);
    }
}
