/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.common.util;

import org.testng.annotations.Test;

public class ToolsTest {

    @Test
    public void testExecuteShell() throws Exception {
        Tools.executeShell("notepad");
    }

    @Test
    public void testExecuteShell1() throws Exception {
        Tools.executeShell("notepad", "help");
    }
}