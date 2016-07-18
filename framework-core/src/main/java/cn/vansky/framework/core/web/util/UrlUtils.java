package cn.vansky.framework.core.web.util;

import cn.vansky.framework.common.util.PathPatternMatcher;

import java.util.SortedSet;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/8/12
 */
public class UrlUtils {

    /**
     * 匹配路径是否在控制域的范围内
     *
     * @param urls URL列表
     * @param path 路径
     * @return
     */
    public static String urlMatch(SortedSet<String> urls, String path) {
        if(urls == null || urls.size() == 0) {
            return null;
        }

        SortedSet<String> hurl = urls.headSet(path);
        SortedSet<String> turl = urls.tailSet(path);

        if (hurl.size() > 0) {
            String before = hurl.last();
            if (pathMatch(path, before)) {
                return before;
            }
        }

        if (turl.size() > 0) {
            String after = turl.first();
            if (pathMatch(path, after)) {
                return after;
            }
        }

        return null;
    }

    /**
     * 匹配路径是否在控制域的范围内
     *
     * @param path
     * @param domain
     * @return
     */
    private static boolean pathMatch(String path, String domain) {
        if (PathPatternMatcher.isPattern(domain)) {
            return PathPatternMatcher.match(domain, path);
        } else {
            return domain.equals(path);
        }
    }
}
