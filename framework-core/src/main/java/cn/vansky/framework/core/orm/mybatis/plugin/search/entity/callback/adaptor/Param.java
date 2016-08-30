package cn.vansky.framework.core.orm.mybatis.plugin.search.entity.callback.adaptor;

import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.SearchOperator;
import cn.vansky.framework.core.orm.mybatis.plugin.search.entity.search.filter.Condition;

/**
 * @author hassop
 * @Description
 * @date 2016/8/30 0030
 * To change this template use File | Settings | File Templates.
 */
public class Param {
    public static final String paramPrefix = "param_";
    public String alias = "";
    public String aliasWithDot = " ";

    public Object formtValue(Condition condition, Object value) {
        SearchOperator operator = condition.getOperator();
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
