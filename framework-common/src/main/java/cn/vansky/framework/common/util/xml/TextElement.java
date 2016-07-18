package cn.vansky.framework.common.util.xml;

import cn.vansky.framework.common.util.OutputUtils;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/8/15
 */
public class TextElement implements Element {

    /** SQL内容 */
    private String content;

    public TextElement(String content) {
        super();
        this.content = content;
    }

    @Override
    public String getFormattedContent(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        OutputUtils.xmlIndent(sb, indentLevel);
        sb.append(content);
        return sb.toString();
    }

    public String getContent() {
        return content;
    }
}
