/**
 * Copyright (c) 2005-2012 https://github.com/yuqiangcui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.vansky.framework.common.entity.callback;


import cn.vansky.framework.common.entity.search.Searchable;

/**
 * <p>User: hyssop
 * <p>Date: 13-1-16 下午8:10
 * <p>Version: 1.0
 */
public final class NoneSearchCallback implements SearchCallback {

    public void prepareQL(StringBuilder ql, Searchable search) {
    }

    public void prepareOrder(StringBuilder ql, Searchable search) {
    }

    public void setValues(StringBuilder query, Searchable search) {
    }

    public void setPageable(StringBuilder query, Searchable search) {
    }
}
