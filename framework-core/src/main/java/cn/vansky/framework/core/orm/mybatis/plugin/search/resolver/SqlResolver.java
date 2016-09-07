package cn.vansky.framework.core.orm.mybatis.plugin.search.resolver;

import cn.vansky.framework.core.orm.mybatis.plugin.page.dialect.Dialect;
import cn.vansky.framework.core.orm.mybatis.plugin.search.vo.Searchable;

/**
 * Created by IntelliJ IDEA .
 * Auth: hyssop
 * Date: 2016/9/6 0006
 */
public interface SqlResolver {

    public void prepareSQL(StringBuilder ql, Searchable search);

    public void prepareOrder(StringBuilder ql, Searchable search);

    /**
     * 根据search给query赋值及设置分页信息
     *
     * @param query
     * @param search
     */
    public void setValues(StringBuilder query, Searchable search);

    public void setPageable(StringBuilder query, Searchable search , Dialect dialect);

}
