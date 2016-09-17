/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * Author: CK.
 * Date: 2015/3/28.
 */
public class Tools {

    /**
     * Trans data column to java column.
     *
     * @param name tableName or columnName
     * @param flag 1:tableName,2:columnName
     * @return java column
     */
    public static String dataBaseToJava(String name, int flag) {
        String[] array = name.replaceFirst("tb_", "").replaceFirst("td_", "").split("_");
        StringBuilder s = new StringBuilder();
        if (flag == 1) {
            for (String a : array) {
                s.append(a.substring(0, 1).toUpperCase() + a.substring(1, a.length()));
            }
        } else if (2 == flag) {
            s.append(array[0]);
            for (int i = 1; i < array.length; i++) {
                s.append(array[i].substring(0, 1).toUpperCase() + array[i].substring(1, array[i].length()));
            }
        }

        return s.toString();
    }

    /**
     * 确保JAVA属性有效
     * 【eMail-eMail】 【firstName-firstName】 【URL-URL】 【XAxis-XAxis】 【a-a】 【B-b】 【Yaxis-yaxis】
     *
     * @param inputString
     *            the input string
     * @return the valid property name
     */
    public static String getValidPropertyName(String inputString) {
        String answer;

        if (inputString == null) {
            answer = null;
        } else if (inputString.length() < 2) {
            // 一个字符变小写
            answer = inputString.toLowerCase(Locale.US);
        } else {
            if (Character.isUpperCase(inputString.charAt(0))
                    && !Character.isUpperCase(inputString.charAt(1))) {
                // 第一个字符大写，第二个字母小写，需要改变第一个字母小写
                answer = inputString.substring(0, 1).toLowerCase(Locale.US)
                        + inputString.substring(1);
            } else {
                answer = inputString;
            }
        }

        return answer;
    }

    /**
     * Execute shell.
     * @param shellCommand the shell command
     */
    public synchronized static void executeShell(String shellCommand) {
        int success = -1;
        BufferedReader bufferedReader = null;
        try {
            Process pid = Runtime.getRuntime().exec(shellCommand);
            bufferedReader = new BufferedReader(new InputStreamReader(pid.getErrorStream()), 1024);
            pid.waitFor();
            success = pid.exitValue();
            if (0 != success) {
                String line = null;
                StringBuilder errorMsg = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    errorMsg.append(line);
                }
                throw new Exception("执行" + shellCommand + "命令异常，异常信息" + errorMsg.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(bufferedReader);
        }
    }

    /**
     * Execute shell.
     *
     * @param shellCommand the shell command
     */
    public synchronized static void executeShell(String ... shellCommand) {
        int success = -1;
        BufferedReader bufferedReader = null;
        try {
            Process pid = Runtime.getRuntime().exec(shellCommand);
            bufferedReader = new BufferedReader(new InputStreamReader(pid.getErrorStream()), 1024);
            pid.waitFor();
            success = pid.exitValue();
            if (0 != success) {
                StringBuilder errorMsg = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    errorMsg.append(line);
                }
                throw new Exception("执行" + shellCommand + "命令异常，异常信息" + errorMsg.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(bufferedReader);
        }
    }

    /**
     * Close bufferReader.
     * @param bufferedReader the bufferedReader
     */
    private static void close(BufferedReader bufferedReader) {
        try {
            if (null != bufferedReader) {
                bufferedReader.close();
            }
        } catch (IOException e) {
            // ignore
            bufferedReader = null;
        }
    }
}
