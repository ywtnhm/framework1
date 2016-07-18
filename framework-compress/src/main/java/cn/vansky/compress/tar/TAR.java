/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.compress.tar;

import cn.vansky.compress.CommonCompress;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/4/15
 */
public class TAR extends CommonCompress {
    public TAR() {
        setType(TAR);
        setSuffix(SUFFIX_TAR);
        commonCompress = new TarCompress(this);
    }
}
