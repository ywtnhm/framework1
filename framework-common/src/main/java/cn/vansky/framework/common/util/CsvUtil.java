/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.common.util;

import au.com.bytecode.opencsv.CSVWriter;
import cn.vansky.framework.common.constant.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/9/22
 */
public class CsvUtil {
    /**
     * RESPONSE以流的方式向浏览器输出CSV文件。
     * @param response 响应
     * @param request 请求
     * @param filename 文件名
     * @param title 标题
     * @param bodyList 内容
     */
    public static void writeCsvFile(HttpServletResponse response, HttpServletRequest request,
                                    String filename, String[] title, List<String[]> bodyList) {
        CSVWriter writer = null;
        try {
            response.setContentType("application/octet-stream;charset=GBK");
            String fileName = URLEncoder.encode(filename, Constant.UTF_8);
            // 根据浏览器代理获取浏览器内核类型
            UserAgent userAgent = UserAgentUtil.getUserAgent(request.getHeader("USER-AGENT"));
            String browserType = userAgent == null ? "" : userAgent.getBrowserType();
            if ("Firefox".equals(browserType)) {
                // 针对火狐浏览器处理方式不一样了,解决Firefox下载文件名编码问题
                fileName = new String(filename.getBytes(Constant.UTF_8), Constant.ISO_8859_1);
            }
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".csv");
            writer = new CSVWriter(response.getWriter());
            if (title != null && title.length > 0) {
                writer.writeNext(title);
            }
            if (bodyList != null && bodyList.size() > 0) {
                writer.writeAll(bodyList);
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("charset no support UTF-8", e);
        } catch (IOException e) {
            throw new RuntimeException("write csv file of response fail ", e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                // ignore
            }
        }
    }

    /**
     * 以文件方式输出
     * @param filename 文件名
     * @param title 标题
     * @param bodyList 内容
     */
    public static void writeCsvFile(String filename, String[] title, List<String[]> bodyList) {
        CSVWriter writer = null;
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(new File(filename + ".csv")), "GBK");
            writer = new CSVWriter(osw);
            if (title != null && title.length > 0) {
                writer.writeNext(title);
            }
            if (bodyList != null && bodyList.size() > 0) {
                writer.writeAll(bodyList);
            }
        } catch (IOException e) {
            throw new RuntimeException("write csv file fail ", e);
        } finally {
            try {
                if (osw != null) {
                    osw.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                // ignore
            }
        }
    }

    /**
     * 生成CSV文件
     * @param outputList 输出的LIST
     * @return CSV文件
     */
    public static String createCsvFile(List<List<Object>> outputList) {
        StringBuilder os = new StringBuilder();
        // 回车符
        char returnChar = 13;
        // 换行符
        char lineChar = 10;
        for (List<Object> unitList : outputList) {
            if (unitList != null && !unitList.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (Object unitOb : unitList) {
                    String unit = "";
                    if (unitOb != null) {
                        unit = unitOb.toString().replaceAll(",", "");
                    }
                    sb.append(unit);
                    sb.append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append(returnChar);
                sb.append(lineChar);
                os.append(sb);
            }
        }
        return os.toString();
    }

}
