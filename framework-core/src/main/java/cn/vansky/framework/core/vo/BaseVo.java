/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.vo;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.vansky.framework.core.orm.mybatis.plugin.page.BasePagination;
import cn.vansky.framework.core.orm.mybatis.plugin.page.Pagination;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;

/**
 * 分页基础Vo
 * Author: CK
 * Date: 2016/1/20
 */
public class BaseVo<T> extends BasePagination {
    /**
     * 获取对象属性对应的map
     * 
     * @param ignoreProperties 不需要的属性
     * @return map
     */
    public Map<String, Object> convertPageMap(String...ignoreProperties) {
        PropertyDescriptor[] ts = BeanUtils.getPropertyDescriptors(this.getClass());
        List<String> ignoreList = (ignoreProperties != null && ignoreProperties.length > 0)
                ? Arrays.asList(ignoreProperties) : new ArrayList<String>();
        ignoreList.add("pageNumberList");
        Map<String, Object> m = new HashMap<String, Object>();
        for (PropertyDescriptor t : ts) {
            Method r = t.getReadMethod();
            if (r != null && (ignoreProperties == null || (!ignoreList.contains(t.getName())))) {
                try {
                    if (!Modifier.isPublic(r.getDeclaringClass().getModifiers())) {
                        r.setAccessible(true);
                    }
                    // 属性中包括分页类
                    if (r.invoke(this) instanceof Pagination) {
                        m.put(Pagination.MAP_PAGE_FIELD, r.invoke(this));
                    } else {
                        m.put(t.getName(), r.invoke(this));
                    }
                } catch (Throwable e) {
                    throw new FatalBeanException("Could not copy property '" + t.getName()
                            + "' from source to target", e);
                }
            }
        }
        // 本身就是分页类
        if (this instanceof Pagination) {
            m.put(Pagination.MAP_PAGE_FIELD, this);
        }
        return m;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
