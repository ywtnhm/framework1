/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.compress.zip;

import cn.vansky.compress.CommonCompress;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/4/14
 */
public class ZIP extends CommonCompress {
    public ZIP() {
        setType(ZIP);
        setSuffix(SUFFIX_ZIP);
        commonCompress = new ZipCompress(this);
    }
}
