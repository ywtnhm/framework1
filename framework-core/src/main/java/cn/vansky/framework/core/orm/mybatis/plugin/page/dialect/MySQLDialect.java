/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.orm.mybatis.plugin.page.dialect;

/**
 * Mysql方言的实现
 * Author: CK
 * Date: 2015/6/14
 */
public class MySQLDialect implements Dialect {

    public String getLimitString(String sql, int offset, int pageSize) {
        StringBuilder sb = new StringBuilder(sql);
        sb.append(" limit ");
        if (offset > 0) {
            sb.append(offset).append(",").append(pageSize);
        } else {
            sb.append(pageSize);
        }
        return sb.toString();
    }

    public boolean supportsLimit() {
        return true;
    }
}
