package cn.vansky.framework.core.orm.mybatis.plugin.search.entity.callback.adaptor;

import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter.SearchFilter;

import java.util.List;

/**
 * @author hassop
 * @Description
 * @date 2016/8/30
 * To change this template use File | Settings | File Templates.
 */
public class ConditionDelegate {

    private List<AbstractConditionAdaptor> conditionAdaptors;

    public void setConditionAdaptor(List<AbstractConditionAdaptor> conditionAdaptors) {
        this.conditionAdaptors = conditionAdaptors;
    }

    public void genCondition(StringBuilder ql, SearchFilter searchFilter) {

        for (AbstractConditionAdaptor conditionAdaptor : conditionAdaptors) {
            if (conditionAdaptor.supportsCondition(searchFilter)) {
                conditionAdaptor.genCondition(ql, searchFilter);
                return;
            }
        }
    }

    public int setValues(StringBuilder query, SearchFilter searchFilter, int paramIndex) {

        for (AbstractConditionAdaptor conditionAdaptor : conditionAdaptors) {
            if (conditionAdaptor.supportsCondition(searchFilter)) {
                return conditionAdaptor.setValues(query, searchFilter, paramIndex);
            }
        }
        return 0;
    }


}
