package cn.vansky.framework.core.orm.mybatis.plugin.search.vo;

import cn.vansky.framework.core.orm.mybatis.plugin.search.enums.SearchOperator;
import cn.vansky.framework.core.orm.mybatis.plugin.search.filter.CustomCondition;

/**
 * @author hassop
 */
public class Param {
    public static final String paramPrefix = "param_";
    public String alias = "";
    public String aliasWithDot = " ";

    public Object formtValue(CustomCondition customCondition, Object value) {
        SearchOperator operator = customCondition.getOperator();
        if (operator == SearchOperator.like || operator == SearchOperator.notLike) {
            return "%" + value + "%";
        }
        if (operator == SearchOperator.prefixLike || operator == SearchOperator.prefixNotLike) {
            return value + "%";
        }

        if (operator == SearchOperator.suffixLike || operator == SearchOperator.suffixNotLike) {
            return "%" + value;
        }
        return value;
    }

}
