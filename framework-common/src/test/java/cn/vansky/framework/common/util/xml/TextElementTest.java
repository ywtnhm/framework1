package cn.vansky.framework.common.util.xml;

import org.testng.annotations.Test;

public class TextElementTest {

    @Test
    public void testGetFormattedContent() throws Exception {
        WebXmlElement webXmlElement = new WebXmlElement("a");
        Attribute href = new Attribute("href", "javascript:void(0)");
        webXmlElement.addAttribute(href);
        Attribute id = new Attribute("id", "auth");
        webXmlElement.addAttribute(id);
        Element element = new TextElement("分配权限");
        webXmlElement.addElement(element);
        String str = webXmlElement.getFormattedContent(0);
        System.out.println(str);
    }
}