/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.common.util;

import org.testng.annotations.Test;

public class ToolsTest {

    @Test
    public void testDataBaseToJava() throws Exception {
        System.out.println(Tools.dataBaseToJava("tb_role", 1));
        System.out.println(Tools.dataBaseToJava("role_id", 2));
    }

    @Test
    public void testGetValidPropertyName() throws Exception {
        System.out.println(Tools.getValidPropertyName("XAxis"));
        System.out.println(Tools.getValidPropertyName("Yaxis"));
        System.out.println(Tools.getValidPropertyName("Y"));
        System.out.println(Tools.getValidPropertyName(null));
    }

    @Test
    public void testExecuteShell() throws Exception {
        Tools.executeShell("notepad");
    }

    @Test
    public void testExecuteShell1() throws Exception {
        Tools.executeShell("notepad", "help");
    }
}