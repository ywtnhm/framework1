/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.compress.bzip2;

import cn.vansky.compress.CommonCompress;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/4/15
 */
public class BZip2 extends CommonCompress {
    public BZip2() {
        setType(BZIP2);
        setSuffix(SUFFIX_BZIP2);
        commonCompress = new BZip2Compress(this);
    }
}
