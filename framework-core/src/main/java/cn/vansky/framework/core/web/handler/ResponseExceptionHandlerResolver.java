package cn.vansky.framework.core.web.handler;

import cn.vansky.framework.core.util.JsonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2016/2/2
 */
public class ResponseExceptionHandlerResolver extends CustomizeExceptionHandlerResolver {

    private static final Logger logger = LoggerFactory.getLogger(ForwardExceptionHandlerResolver.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        // 把漏网的异常信息记入日志
        try {
            Method method = ((HandlerMethod) handler).getMethod();
            logger.error("Catch Exception:方法[" + method.getDeclaringClass().getName()
                    + "." + method.getName() + "],异常", ex);
            String message = getMessage(ex);
            HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
            HttpHeaders headers = outputMessage.getHeaders();
            if (headers != null) {
                MediaType contentType = headers.getContentType();
                // 默认UTF-8
                Charset charset = null;
                if (contentType != null && contentType.getCharSet() != null) {
                    charset = contentType.getCharSet();
                } else {
                    charset = Charset.forName("UTF-8");
                }
                // 设置内容类型
                if (contentType == null) {
                    headers.setContentType(MediaType.TEXT_HTML);
                }
                // 设置内容长度
                String json = JsonResp.asData().error(message).toJson();
                if (headers.getContentLength() == -1) {
                    Long contentLength = (long) json.getBytes(charset.name()).length;
                    if (contentLength != null) {
                        headers.setContentLength(contentLength);
                    }
                }
                // 设置接收编码类型
                headers.setAcceptCharset(new ArrayList<Charset>(Charset.availableCharsets().values()));
                // 将错误信息传递给前端
                StreamUtils.copy(json, charset, outputMessage.getBody());
                outputMessage.getBody().flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
