/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.compress.gzip;

import cn.vansky.compress.CommonCompress;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/4/15
 */
public class GZIP extends CommonCompress {
    public GZIP() {
        setType(GZIP);
        setSuffix(SUFFIX_GZIP);
        commonCompress = new GzipCompress(this);
    }
}
