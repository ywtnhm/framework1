/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.compress.gzip;

import cn.vansky.compress.Compress;
import org.testng.annotations.Test;

public class GzipCompressTest {

    public static Compress compress = new GZIP();

    @Test
    public void testCompress() {
        compress.source("D:\\logs");
//        compress.setTarget("D:\\2\\");
        compress.compress();
    }

    public void testUnCompress() throws Exception {
        compress.source("D:\\2\\1.gz");
        compress.target("D:\\3\\");
        compress.unCompress();
    }
}