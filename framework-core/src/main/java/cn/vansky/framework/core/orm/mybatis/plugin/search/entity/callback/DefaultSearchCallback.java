/**
 * Copyright (c) 2005-2012 https://github.com/yuqiangcui
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.vansky.framework.core.orm.mybatis.plugin.search.entity.callback;

import cn.vansky.framework.core.orm.mybatis.plugin.page.dialect.Dialect;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.callback.adaptor.*;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.SearchOperator;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.Searchable;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter.AndCondition;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter.CustomCondition;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter.OrCondition;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter.SearchFilter;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 将hibernate类型映射改成mybatis类型映射
 * <p>User: hyssop
 * <p>Date: 16-7-28 下午4:20
 * <p>Version: 1.1
 */
public class DefaultSearchCallback  implements SearchCallback {

    private Param param = new Param();
    private ConditionDelegate conditionDelegate = new ConditionDelegate();

    public DefaultSearchCallback() {
        this("");

        List<AbstractConditionAdaptor> conditionAdaptors = new ArrayList<AbstractConditionAdaptor>();
        conditionAdaptors.add(new CustomConditionAdaptor(param));
        conditionAdaptors.add(new OrConditionAdaptor(param));
        conditionAdaptors.add(new AndConditionAdaptor(param));
        conditionDelegate.setConditionAdaptor(conditionAdaptors);

    }

    public DefaultSearchCallback(String alias) {
        param.alias = alias;
        if (!StringUtils.isEmpty(alias)) {
            param.aliasWithDot = alias + ".";
        } else {
            param.aliasWithDot = "";
        }
        List<AbstractConditionAdaptor> conditionAdaptors = new ArrayList<AbstractConditionAdaptor>();
        conditionAdaptors.add(new CustomConditionAdaptor(param));
        conditionAdaptors.add(new OrConditionAdaptor(param));
        conditionAdaptors.add(new AndConditionAdaptor(param));
        conditionDelegate.setConditionAdaptor(conditionAdaptors);
    }

    public String getAlias() {
        return param.alias;
    }

    public String getAliasWithDot() {
        return param.aliasWithDot;
    }

    public void prepareSQL(StringBuilder ql, Searchable search) {
        if (!search.hasSearchFilter()) {
            return;
        }
        int paramIndex = 1;
        for (SearchFilter searchFilter : search.getSearchFilters()) {
            if (searchFilter instanceof CustomCondition) {
                CustomCondition customCondition = (CustomCondition) searchFilter;
                if (customCondition.getOperator() == SearchOperator.custom) {
                    continue;
                }
            }
            ql.append(" and ");
            genCondition(ql, searchFilter);
        }
    }




    private void genCondition(StringBuilder ql, SearchFilter searchFilter) {
        boolean needAppendBracket = searchFilter instanceof OrCondition || searchFilter instanceof AndCondition;
        if (needAppendBracket) {
            ql.append("(");
        }
        conditionDelegate.genCondition(ql,searchFilter);
        if (needAppendBracket) {
            ql.append(")");
        }
    }


    public void setValues(StringBuilder query, Searchable search) {
        int paramIndex = 1;
        for (SearchFilter searchFilter : search.getSearchFilters()) {
            paramIndex = conditionDelegate.setValues(query,searchFilter,paramIndex);
        }
    }

    public void setPageable(StringBuilder sql, Searchable searchable, Dialect dialect) {
        if (dialect.supportsLimit()&&searchable.getPage()!=null&& !ObjectUtils.equals(searchable.getPage(), ObjectUtils.NULL)) {
            int pageSize = searchable.getPage().getPageSize();
            int index = (searchable.getPage().getPageNumber() - 1) * pageSize;
            int start = index < 0 ? 0 : index;
             dialect.getLimitString(sql, start, pageSize);
        }
    }


    public void prepareOrder(StringBuilder ql, Searchable search) {
        if (search.hashSort()) {
            ql.append(" order by ");
            for (Sort.Order order : search.getSort()) {
                ql.append(String.format("%s%s %s, ", getAliasWithDot(), order.getProperty(), order.getDirection().name().toLowerCase()));
            }

            ql.delete(ql.length() - 2, ql.length());
        }
    }
}
