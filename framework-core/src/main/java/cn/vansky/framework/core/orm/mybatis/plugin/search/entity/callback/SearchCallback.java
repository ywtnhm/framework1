/**
 * Copyright (c) 2005-2012 https://github.com/yuqiangcui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.vansky.framework.core.orm.mybatis.plugin.search.entity.callback;


import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.Searchable;

/**
 * <p>User: hyssop
 * <p>Date: 13-1-16 下午4:01
 * <p>Version: 1.0
 */
public interface SearchCallback {

    public static final SearchCallback NONE = new NoneSearchCallback();
    public static final SearchCallback DEFAULT = new DefaultSearchCallback();


    /**
     * 动态拼HQL where、group by having
     *
     * @param ql
     * @param search
     */
    public void prepareSQL(StringBuilder ql, Searchable search);

    public void prepareOrder(StringBuilder ql, Searchable search);

    /**
     * 根据search给query赋值及设置分页信息
     *
     * @param query
     * @param search
     */
    public void setValues(StringBuilder query, Searchable search);

    public void setPageable(StringBuilder query, Searchable search);

}
