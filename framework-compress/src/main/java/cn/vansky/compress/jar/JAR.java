/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.compress.jar;

import cn.vansky.compress.CommonCompress;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/6/30.
 */
public class JAR extends CommonCompress {
    public JAR() {
        setType(JAR);
        setSuffix(SUFFIX_JAR);
        commonCompress = new JarCompress(this);
    }
}
