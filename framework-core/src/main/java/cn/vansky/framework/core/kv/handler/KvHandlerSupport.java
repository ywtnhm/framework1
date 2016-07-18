/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.kv.handler;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/11/13
 */
public class KvHandlerSupport {
    /**
     * 默认值
     */
    private Object defaultValue;
    /**
     * 处理Handler
     */
    private KvHandler kvHandler;

    /**
     * handler是否存在
     * @return true:存在,false:不存在
     */
    public boolean isExist() {
        return kvHandler != null ? true : false;
    }

    /**
     * 扩展执行
     * @param params 参数
     * @return list
     */
    public List<Map<String, Object>> execute(Map<String, Object> params) {
        return kvHandler.extendExecute(params);
    }

    /**
     * 是否使用dao执行
     * @return
     */
    public boolean isDaoExecute() {
        return kvHandler.isDaoExecute();
    }

    /**
     * 创建SQL
     * @return SQL
     */
    public String getSql(Map<String, Object> params) {
        return kvHandler.createSql(params);
    }

    /**
     * set kvHandler
     * @param kvHandler kvHandler
     */
    public void setKvHandler(KvHandler kvHandler) {
        this.kvHandler = kvHandler;
    }

    /**
     * get kvHandler
     * @return kvHandler
     */
    public KvHandler getKvHandler() {
        return kvHandler;
    }

    /**
     * 获取默认值
     * @return defaultValue
     */
    public Object getDefaultValue() {
        return defaultValue;
    }

    /**
     * 获取默认值标志
     * @return defaultValueFlag
     */
    public int getDefaultValueFlag() {
        return kvHandler.getDefaultValueFlag();
    }

    /**
     * 设置默认值
     * @param defaultValue 默认值
     */
    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }
}
