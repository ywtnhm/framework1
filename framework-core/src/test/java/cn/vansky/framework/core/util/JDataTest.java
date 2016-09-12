/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.util;

import cn.vansky.framework.core.vo.BaseVo;
import org.testng.annotations.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDataTest {

    @Test
    public void testAddAll() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("1", "name1");
        map.put("2", "name2");
        map.put("3", "name3");
        String json = JsonResp.asData().addAll(map).toJson();
        System.out.println(json);
    }

    @Test
    public void testAdd() throws Exception {
        String json = JsonResp.asData().add("4", "name4").add("5", "name5").toJson();
        System.out.println(json);
    }

    @Test
    public void testObject() throws Exception {
        PageTestVo vo = new PageTestVo(10);
        List<PageTestBo> list = new ArrayList<PageTestBo>();
        for (int i = 0; i < 3; i++) {
            PageTestBo bo = new PageTestBo("name" + i);
            list.add(bo);
        }
        vo.setRows(list);
        String json = JsonResp.asData(vo).toJson();
        System.out.println(json);
    }

    public static class PageTestVo extends BaseVo<PageTestBo> {
        int age;

        public PageTestVo(int age) {
            this.age = age;
        }
    }

    public static class PageTestBo implements Serializable {
        String name;

        public PageTestBo(String name) {
            this.name = name;
        }
    }
}