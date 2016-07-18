/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.compress.jar;

import cn.vansky.compress.Compress;

public class JarCompressTest {

    public static Compress compress = new JAR();

    public void testCompress() {
        compress.source(JarCompressTest.class.getResource("/").getPath());
        compress.target("D:\\3\\");
        compress.compress();
    }

    public void testUnCompress() throws Exception {
        compress.source("D:\\3\\test-classes.jar");
        compress.target("D:\\3\\");
        compress.unCompress();
    }
}