/**
 * Copyright (c) 2005-2012 https://github.com/yuqiangcui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter;

import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.callback.adaptor.AbstractConditionAdaptor;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.callback.adaptor.AndConditionAdaptor;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.callback.adaptor.ConditionAdaptor;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.callback.adaptor.OrConditionAdaptor;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * or 条件
 * <p>User: hyssop
 * <p>Date: 16-5-24 下午2:51
 * <p>Version: 1.0
 */
public class OrCondition implements SearchFilter {

    private List<SearchFilter> orFilters = Lists.newArrayList();

    OrCondition() {
    }

    public OrCondition add(SearchFilter filter) {
        this.orFilters.add(filter);
        return this;
    }

    public List<SearchFilter> getOrFilters() {
        return orFilters;
    }

    public String toString() {
        return "OrCondition{" + orFilters + '}';
    }
}
