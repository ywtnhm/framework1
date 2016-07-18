/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.compress.bzip2;

import cn.vansky.compress.Compress;
import org.testng.annotations.Test;

public class BZip2CompressTest {

    public static Compress compress = new BZip2();

    @Test
    public void testCompress() {
        compress.source("D:\\demo\\test.js");
        compress.target("D:\\2\\1.bz2");
        compress.compress();
    }

    public void testUnCompress() throws Exception {
        compress.source("D:\\2\\1.bz2");
        compress.target("D:\\3");
        compress.unCompress();
    }
}