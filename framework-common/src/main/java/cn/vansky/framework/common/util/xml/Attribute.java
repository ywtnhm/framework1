package cn.vansky.framework.common.util.xml;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/8/15
 */
public class Attribute implements Element {
    /**
     * 属性名
     */
    private String name;
    /**
     * 属性值
     */
    private String value;

    public Attribute(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }

    public String getFormattedContent(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("=\"");
        sb.append(value);
        sb.append('\"');
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
