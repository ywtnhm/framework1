package cn.vansky.framework.core.orm.mybatis.plugin.search;

import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.Searchable;
import cn.vansky.framework.core.orm.mybatis.plugin.page.BaseInterceptor;
import cn.vansky.framework.core.orm.mybatis.plugin.page.SQLHelp;
import cn.vansky.framework.core.orm.mybatis.plugin.page.dialect.DialectType;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @author hassop
 * @Description 查询插件 根据searchble接口的查询条件动态调整查询sql完成查询（
 * 包括了条件、排序和分页条件）
 * @date 2016/7/28
 */
@Intercepts({@Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class SearchableInterceptor extends BaseInterceptor {

    protected static String _SQL_PATTERN_Search = ".*BySearchable*.*";

    public SearchableInterceptor() {
        super(_SQL_PATTERN_Search);
    }

    public Object intercept(Invocation invocation) throws Throwable {

         MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        if (mappedStatement.getId().matches(_SQL_PATTERN_Search)) {

            Object parameter = invocation.getArgs()[1];

            if (!Searchable.class.isAssignableFrom(parameter.getClass())) {
                invocation.proceed();
            }

            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            String originalSql = boundSql.getSql().trim();

            if (StringUtils.isBlank(boundSql.getSql())) {
                return null;
            }
            //参数转换（判断一下库）
            if (null == DIALECT) {
                String databaseId = mappedStatement.getConfiguration().getDatabaseId();
                DIALECT = DialectType.getDialect(databaseId);
            }
            //parameter is searhabel
            String realSql = SQLHelp.generateRealSql(originalSql, (Searchable) parameter, DIALECT);

            log.info("最终 的sql为:" + realSql);

            invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);

            BoundSql newBoundSql = SQLHelp.createNewBoundSql(mappedStatement, boundSql.getParameterObject(),
                    boundSql, realSql);

            MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));

            invocation.getArgs()[0] = newMs;

        }
        return invocation.proceed();
    }
 }