/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.kv.handler;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/11/13
 */
public class SimpleKvHandler extends DefaultKvHandler {

    public SimpleKvHandler(Integer name, String tableName, String keyField, String valueField,
                           Map<String, Object> extraFields, int sqlFlag) {
        this(name, tableName, keyField, valueField, extraFields, sqlFlag, true, 0);
    }

    public SimpleKvHandler(Integer name, String tableName, String keyField, String valueField,
                           Map<String, Object> extraFields, int sqlFlag, int defaultValueFlag) {
        this(name, tableName, keyField, valueField, extraFields, sqlFlag, true, defaultValueFlag);
    }

    public SimpleKvHandler(Integer name, String tableName, String keyField, String valueField,
                           Map<String, Object> extraFields, int sqFlag, boolean daoExecuteFlag,
                           int defaultValueFlag) {
        super(name, tableName, keyField, valueField, extraFields, sqFlag, daoExecuteFlag, defaultValueFlag);
    }
}
