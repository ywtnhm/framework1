package cn.vansky.framework.core.orm.mybatis.plugin.search.entity.callback.adaptor;

import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter.AndCondition;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter.OrCondition;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter.SearchFilter;

import java.io.Serializable;

/**
 * @author hassop
 * @Description
 * @date 2016/8/30 0030
 * To change this template use File | Settings | File Templates.
 */
public class OrConditionAdaptor extends AbstractConditionAdaptor implements  Serializable {
    private Param param;

    public OrConditionAdaptor(Param param){
        this.param = param;
    }

    public boolean supportsCondition(SearchFilter searchFilter) {
        if(searchFilter.getClass().isAssignableFrom(OrCondition.class)){
            return true;
        }
        return false;
    }

    public void genCondition(StringBuilder ql, SearchFilter searchFilter) {
        boolean isFirst = true;
        for (SearchFilter orSearchFilter : ((OrCondition) searchFilter).getOrFilters()) {
            if (!isFirst) {
                ql.append(" or ");
            }
            genCondition(ql, orSearchFilter);
            isFirst = false;
        }
    }

    @Override
    public int setValues(StringBuilder query, SearchFilter searchFilter, int paramIndex) {
        for (SearchFilter orSearchFilter : ((OrCondition) searchFilter).getOrFilters()) {
            paramIndex = setValues(query, orSearchFilter, paramIndex);
        }
        return  paramIndex;
    }
}
