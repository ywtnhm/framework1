package cn.vansky.framework.common.util.xml;

/**
 * Created by IntelliJ IDEA.
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
