package cn.vansky.framework.core.orm.mybatis.plugin.search.entity.callback.adaptor;

import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter.AndCondition;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter.SearchFilter;

import java.io.Serializable;

/**
 * @author hassop
 * @Description
 * @date 2016/8/30 0030
 * To change this template use File | Settings | File Templates.
 */
public class AndConditionAdaptor extends AbstractConditionAdaptor implements Serializable {
    private Param param;

    public AndConditionAdaptor(Param param){
        this.param = param;
    }

    public boolean supportsCondition(SearchFilter searchFilter) {
        if(searchFilter.getClass().isAssignableFrom(AndCondition.class)){
            return true;
        }
        return false;
    }

    public void genCondition(StringBuilder ql, SearchFilter searchFilter) {
        boolean isFirst = true;
        for (SearchFilter andSearchFilter : ((AndCondition) searchFilter).getAndFilters()) {
            if (!isFirst) {
                ql.append(" and ");
            }
            genCondition(ql, andSearchFilter);
            isFirst = false;
        }
    }

    @Override
    public int setValues(StringBuilder query, SearchFilter searchFilter, int paramIndex) {
        for (SearchFilter andSearchFilter : ((AndCondition) searchFilter).getAndFilters()) {
            paramIndex = setValues(query, andSearchFilter, paramIndex);
        }
        return  paramIndex;
    }
}
