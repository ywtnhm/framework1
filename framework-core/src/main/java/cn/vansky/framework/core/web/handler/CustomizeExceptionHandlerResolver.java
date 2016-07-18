package cn.vansky.framework.core.web.handler;

import cn.vansky.exception.BaseException;
import cn.vansky.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2016/2/5
 */
public abstract class CustomizeExceptionHandlerResolver implements HandlerExceptionResolver {

    protected String getMessage(Exception ex) {
        BaseException exception = new SystemException(ex);
        String message = null;
        if (ex instanceof DataIntegrityViolationException) {
            message = exception.getMessage(ex.getCause());
        } else {
            message = exception.getMessage();
        }
        if (null == message) {
            return "空指针异常";
        }
        return message;
    }
}
