/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.orm.mybatis.plugin.search;

import cn.vansky.framework.core.orm.mybatis.plugin.page.dialect.Dialect;
import cn.vansky.framework.core.orm.mybatis.plugin.search.resolver.AbstractSqlResolverOuter;
import cn.vansky.framework.core.orm.mybatis.plugin.search.resolver.DelegeteSqlResolver;
import cn.vansky.framework.core.orm.mybatis.plugin.search.vo.Searchable;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * setParameters 设置参数 getCount 查询总记录数 generatePageSql 生成分页查询SQL
 * Author: CK
 * Date: 2015/6/14
 */
public class SqlFacade {

    protected static Log log = LogFactory.getLog(SqlFacade.class);

    public static DelegeteSqlResolver DEFAULTDELEGETE = new DelegeteSqlResolver(null);

    public static AbstractSqlResolverOuter delegeteSqlResolver = DEFAULTDELEGETE;

    public void setDelegeteSqlResolver(AbstractSqlResolverOuter sqlResolver){
        this.delegeteSqlResolver = sqlResolver;
    }

    public static String generateRealSql(String originalSql, Searchable parameter, Dialect dialect) {
        StringBuilder sb = new StringBuilder(originalSql);
        //条件拼接
        delegeteSqlResolver.compositeSql(sb, parameter, dialect);
         return sb.toString();
    }

    /**
     * 分页字符串拼接
     * @param sql
     * @param searchable
     * @param dialect
     * @return
     */
    public static String generateRealPageSql(String sql, Searchable searchable, Dialect dialect) {
        if (dialect.supportsLimit()&&searchable.getPage()!=null&& !ObjectUtils.equals(searchable.getPage(),ObjectUtils.NULL)) {
            int pageSize = searchable.getPage().getLimit();
            int index = (searchable.getPage().getCurrentPage() - 1) * pageSize;
            int start = index < 0 ? 0 : index;
            return dialect.getLimitString(sql, start, pageSize);
        } else {
            return sql;
        }
    }
}
