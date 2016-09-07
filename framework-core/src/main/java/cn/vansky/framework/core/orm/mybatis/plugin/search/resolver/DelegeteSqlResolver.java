package cn.vansky.framework.core.orm.mybatis.plugin.search.resolver;

import cn.vansky.framework.core.orm.mybatis.plugin.page.dialect.Dialect;
import cn.vansky.framework.core.orm.mybatis.plugin.search.vo.Searchable;

/**
 * Created by IntelliJ IDEA .
 * Auth: hyssop
 * Date: 2016/9/6 0006
 */
public class DelegeteSqlResolver extends AbstractSqlResolverOuter {

    public static SqlResolver DEFUALTCALL = new DefaultSqlResolver("");

    public static SqlResolver sqlResolver = DEFUALTCALL;

    public DelegeteSqlResolver( SqlResolver sqlResolver ){
        if(sqlResolver!=null){
            this.sqlResolver = sqlResolver;
        }
    }

    @Override
    public void compositeSql(StringBuilder query, Searchable search, Dialect dialect) {
        sqlResolver.prepareSQL(query, search);
        if(search.hashSort()){
            sqlResolver.prepareOrder(query, search);
        }
        if(search.hasPageable()){
            sqlResolver.setPageable(query,search,dialect);
        }
    }
}
