package cn.vansky.framework.core.orm.mybatis.plugin.search.entity.callback.adaptor;

import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.SearchOperator;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter.CustomCondition;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter.SearchFilter;
import org.springframework.util.StringUtils;

/**
 * @author hassop
 * @Description
 * @date 2016/8/30 0030
 * To change this template use File | Settings | File Templates.
 */
public class CustomConditionAdaptor extends AbstractConditionAdaptor {
    private Param param;

    public CustomConditionAdaptor(Param param) {
        this.param = param;
    }

    public boolean supportsCondition(SearchFilter searchFilter) {
        if (searchFilter.getClass().isAssignableFrom(CustomCondition.class)) {
            return true;
        }
        return false;
    }

    public void genCondition(StringBuilder ql, SearchFilter searchFilter) {
        CustomCondition customCondition = (CustomCondition) searchFilter;
        String entityProperty = customCondition.getEntityProperty(); //自定义条件
        String operatorStr = customCondition.getOperatorStr();
        Object entityValue = customCondition.getValue();
        ql.append(param.aliasWithDot);
        ql.append(entityProperty);
        ql.append(" ");
        if (!customCondition.isUnaryFilter()) {
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
        CustomCondition customCondition = (CustomCondition) searchFilter;
        if (customCondition.getOperator() == SearchOperator.custom) {
            return paramIndex;
        }
        if (customCondition.isUnaryFilter()) {
            return paramIndex;
        }
        query.toString().replaceAll(param.paramPrefix + paramIndex++, param.formtValue(customCondition, customCondition.getValue()).toString());
        return paramIndex;
    }
}
