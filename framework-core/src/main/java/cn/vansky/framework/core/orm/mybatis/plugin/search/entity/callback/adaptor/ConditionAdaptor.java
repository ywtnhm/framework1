package cn.vansky.framework.core.orm.mybatis.plugin.search.entity.callback.adaptor;

import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.SearchOperator;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter.Condition;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter.SearchFilter;
import org.springframework.util.StringUtils;

/**
 * @author hassop
 * @Description
 * @date 2016/8/30 0030
 * To change this template use File | Settings | File Templates.
 */
public class ConditionAdaptor extends AbstractConditionAdaptor {
    private Param param;

    public ConditionAdaptor(Param param) {
        this.param = param;
    }

    public boolean supportsCondition(SearchFilter searchFilter) {
        if (searchFilter.getClass().isAssignableFrom(Condition.class)) {
            return true;
        }
        return false;
    }

    public void genCondition(StringBuilder ql, SearchFilter searchFilter) {
        Condition condition = (Condition) searchFilter;
        String entityProperty = condition.getEntityProperty(); //自定义条件
        String operatorStr = condition.getOperatorStr();
        Object entityValue = condition.getValue();
        ql.append(param.aliasWithDot);
        ql.append(entityProperty);
        ql.append(" ");
        if (!condition.isUnaryFilter()) {
            ql.append(operatorStr);
            if ("in".equalsIgnoreCase(operatorStr)) {
                String tep = StringUtils.arrayToDelimitedString((String[]) entityValue, ",");
                ql.append("(");
                ql.append(tep);
                ql.append(")");
            } else {
                ql.append("\"");
                ql.append(entityValue);
                ql.append("\"");
            }

        }
    }

    public int setValues(StringBuilder query, SearchFilter searchFilter, int paramIndex) {
        Condition condition = (Condition) searchFilter;
        if (condition.getOperator() == SearchOperator.custom) {
            return paramIndex;
        }
        if (condition.isUnaryFilter()) {
            return paramIndex;
        }
        query.toString().replaceAll(param.paramPrefix + paramIndex++, param.formtValue(condition, condition.getValue()).toString());
        return paramIndex;
    }
}
