package cn.vansky.framework.common.util.xml;

/**
 * 元素接口,定义获取元素最终字符串
 * Author: CK
 * Date: 2015/8/15
 */
public interface Element {
    /**
     * 定义子类格式化内容
     * @param indentLevel 级别
     * @return 返回字符串
     */
    public abstract String getFormattedContent(int indentLevel);
}
