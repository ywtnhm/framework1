/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.orm.mybatis.plugin.page;

import cn.vansky.framework.core.orm.mybatis.plugin.page.dialect.Dialect;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.plugin.Interceptor;

import java.io.Serializable;
import java.util.Map;

/**
 * 1. 参数对象转换为Page对象。<br>
 * 2. 配置读取：dialectClass, sqlPattern, pageFieldName
 * Author: CK
 * Date: 2015/6/14
 */
public abstract class BaseInterceptor implements Interceptor, Serializable {

    protected static final Log log = LogFactory.getLog(BaseInterceptor.class);

    protected Dialect DIALECT;

    /**
     * 拦截的ID，在mapper中的id，可以匹配正则
     */
    protected String _SQL_PATTERN = ".*findPage*.*";

    protected static String MAP_PAGE_FIELD = Pagination.MAP_PAGE_FIELD;

    /**
     * 对参数进行转换和检查
     *
     * @param parameterObject 参数对象
     * @param pagination 参数VO
     * @return 参数VO
     */
    protected static Pagination convertParameter(Object parameterObject, Pagination pagination) {
        if (parameterObject instanceof Pagination) {
            pagination = (Pagination) parameterObject;
        } else if (parameterObject instanceof Map) {
            Map parameterMap = (Map) parameterObject;
            pagination = (Pagination) parameterMap.get(MAP_PAGE_FIELD);
            if (pagination == null) {
                throw new PersistenceException("分页参数不能为空");
            }
            parameterMap.put(MAP_PAGE_FIELD, pagination);
        }
        return pagination;
    }
}
