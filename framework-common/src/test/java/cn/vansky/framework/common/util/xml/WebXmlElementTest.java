package cn.vansky.framework.common.util.xml;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class WebXmlElementTest {

    @Test
    public void testGetFormattedContent() throws Exception {
        WebXmlElement webXmlElement = new WebXmlElement("input");
        Attribute id = new Attribute("id", "add");
        webXmlElement.addAttribute(id);
        Attribute type = new Attribute("type", "button");
        webXmlElement.addAttribute(type);
        Attribute path = new Attribute("path", "user/addUser");
        webXmlElement.addAttribute(path);
        Attribute value = new Attribute("value", "新增");
        webXmlElement.addAttribute(value);
        System.out.println(webXmlElement.getFormattedContent(0));
    }
}