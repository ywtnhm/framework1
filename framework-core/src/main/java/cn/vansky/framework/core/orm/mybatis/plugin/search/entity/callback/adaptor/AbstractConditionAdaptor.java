package cn.vansky.framework.core.orm.mybatis.plugin.search.entity.callback.adaptor;

import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter.SearchFilter;

/**
 * @author hassop
 * @Description
 * @date 2016/8/30 0030
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractConditionAdaptor {
    abstract public boolean supportsCondition(SearchFilter searchFilter);
    abstract public void genCondition(StringBuilder ql, SearchFilter searchFilter);
    abstract  public int setValues(StringBuilder query, SearchFilter searchFilter, int paramIndex);
}
