/**
 * Copyright (c) 2005-2012 https://github.com/yuqiangcui
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * and 条件
 * <p>User: hyssop
 * <p>Date: 16-5-24 下午2:51
 * <p>Version: 1.0
 */
public class AndCondition implements SearchFilter {

    private List<SearchFilter> andFilters = Lists.newArrayList();

    AndCondition() {
    }

    public AndCondition add(SearchFilter filter) {
        this.andFilters.add(filter);
        return this;
    }

    public List<SearchFilter> getAndFilters() {
        return andFilters;
    }

    public String toString() {
        return "AndCondition{" + andFilters + '}';
    }
}
