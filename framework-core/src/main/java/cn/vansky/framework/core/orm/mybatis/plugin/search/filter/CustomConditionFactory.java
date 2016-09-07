/**
 * Copyright (c) 2005-2012 https://github.com/yuqiangcui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.vansky.framework.core.orm.mybatis.plugin.search.filter;


import cn.vansky.framework.core.orm.mybatis.plugin.search.enums.RelationEnum;
import cn.vansky.framework.core.orm.mybatis.plugin.search.enums.SearchOperator;
import cn.vansky.framework.core.orm.mybatis.plugin.search.exception.SearchException;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

import java.util.Arrays;

/**
 * <p>User: hyssop
 * <p>Date: 16-5-24 下午4:10
 * <p>Version: 1.0
 */
public final class CustomConditionFactory {
    /**
     * 根据查询key和值生成Condition
     *
     * @param key   如 name_like
     * @param value
     * @return
     */
    public static SearchFilter newCustomCondition(final String key, final Object value) throws SearchException {
        return CustomCondition.newCondition(key, value);
    }

    /**
     * 根据查询属性、操作符和值生成Condition
     *
     * @param searchProperty
     * @param operator
     * @param value
     * @return
     */
    public static SearchFilter newCustomCondition(final String searchProperty, final SearchOperator operator, final Object value) {
        return CustomCondition.newCondition(searchProperty, operator, value);
    }


    /**
     * 拼or条件
     *
     * @param first
     * @param others
     * @return
     */
    public static SearchFilter or(SearchFilter first, SearchFilter... others) {
        if (ObjectUtils.notEqual(first,ObjectUtils.NULL)&&ArrayUtils.isNotEmpty(others)) {
            if(others instanceof CustomCondition[]){
                ((CustomCondition)first).addCustomCondition(RelationEnum.valueOf("or"),Arrays.asList((CustomCondition[])others));
            }

        }
        return first;
    }


    /**
     * 拼and条件
     *
     * @param first
     * @param others
     * @return
     */
    public static SearchFilter and(SearchFilter first, SearchFilter... others) {
        if (ObjectUtils.notEqual(first,ObjectUtils.NULL)&&ArrayUtils.isNotEmpty(others)) {
            if(others instanceof CustomCondition[]){
                ((CustomCondition)first).addCustomCondition(RelationEnum.valueOf("and"),Arrays.asList((CustomCondition[])others));
            }
        }
        return first;
    }

}