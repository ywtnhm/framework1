package cn.vansky.framework.common.util.xml;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AttributeTest {

    @Test
    public void testGetFormattedContent() throws Exception {
        Attribute attribute = new Attribute("id", "add");
        System.out.println(attribute.getFormattedContent(0));
    }
}