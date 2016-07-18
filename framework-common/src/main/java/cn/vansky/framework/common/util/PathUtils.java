package cn.vansky.framework.common.util;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2016/1/17
 */
public class PathUtils {

    /**
     * Join.
     *
     * @param base the base
     * @param path the path
     * @return the string
     */
    public static String join(String base, String path) {
        return new File(base, path).getPath();
    }
}
