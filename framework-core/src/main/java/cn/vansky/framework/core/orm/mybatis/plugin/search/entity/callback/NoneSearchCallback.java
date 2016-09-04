/**
 * Copyright (c) 2005-2012 https://github.com/yuqiangcui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.vansky.framework.core.orm.mybatis.plugin.search.entity.callback;


import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.Searchable;

/**
 * <p>User: hyssop
 * <p>Date: 16-1-16 下午8:10
 * <p>Version: 1.0
 */
public final class NoneSearchCallback implements SearchCallback {

    public void prepareSQL(StringBuilder ql, Searchable search) {
    }

    public void prepareOrder(StringBuilder ql, Searchable search) {
    }

    public void setValues(StringBuilder query, Searchable search) {
    }

    public void setPageable(StringBuilder query, Searchable search) {
    }
}
