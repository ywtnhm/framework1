/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.orm.mybatis.plugin.page;

import cn.vansky.framework.core.orm.mybatis.plugin.page.dialect.Dialect;
import cn.vansky.framework.core.orm.mybatis.plugin.page.dialect.DialectType;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Plugin;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

/**
 * 1. 参数对象转换为Page对象。<br>
 * 2. 配置读取：dialectClass, sqlPattern, pageFieldName
 * Author: CK
 * Date: 2015/6/14
 */
public abstract class BaseInterceptor implements Interceptor, Serializable {

    protected static final Log log = LogFactory.getLog(BaseInterceptor.class);

    protected static Dialect DIALECT ;

    protected static String MAP_PAGE_FIELD = Pagination.MAP_PAGE_FIELD;

    private String sqlPattern ;

    public BaseInterceptor(String _SQL_PATTERN){
        setSqlPattern(_SQL_PATTERN);
    }

    public void setSqlPattern(String sqlPattern) {
        this.sqlPattern = sqlPattern;
    }

    /**
     * 对参数进行转换和检查
     *
     * @param parameterObject 参数对象
     * @param pagination      参数VO
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


    public MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
                ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        String[] keyProperties = ms.getKeyProperties();
        builder.keyProperty(StringUtils.join(keyProperties, ','));
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        return builder.build();
    }
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {

    }

    public String getSqlPattern() {
        return sqlPattern;
    }

    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
}
