package cn.vansky.framework.core.web.util;

import cn.vansky.framework.core.web.ThreadLocalInfo;
import cn.vansky.framework.core.web.filter.auth.AuthWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.SortedSet;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/12/15
 */
public class SessionHelper {
    /**
     * 获取当前用户
     *
     * @return 当前用户
     */
    public static Object getUser() {
        return ThreadLocalInfo.currentHttpServletSession().getAttribute(AuthWrapper.SESSION_USER_MESSAGE);
    }

    /**
     * 获取角色、权限集合
     *
     * @return 角色、权限集合
     */
    public static AuthWrapper getAuthWrap() {
        return (AuthWrapper) ThreadLocalInfo.currentHttpServletSession().getAttribute(AuthWrapper.SESSION_AUTH_WRAPPER_MESSAGE);
    }

    /**
     * 获取当前用户是否有访问这个url的权限
     * @param path url
     * @return url的权限
     */
    public static boolean hasOpAuth(String path) {
        // 从缓存中获取当前用户所能操作的所有的url
        SortedSet<String> urls = SessionHelper.getAuthWrap().getAuthUrlSet();
        if (CollectionUtils.isNotEmpty(urls)) {
            return StringUtils.isNotBlank(UrlUtils.urlMatch(urls, path));
        }
        return false;
    }

    public static void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
