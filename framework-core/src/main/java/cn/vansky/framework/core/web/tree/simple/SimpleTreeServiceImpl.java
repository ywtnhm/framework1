/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.web.tree.simple;

import cn.vansky.framework.common.util.JsonUtil;
import cn.vansky.framework.core.web.easyUI.model.SimpleTreeModel;
import cn.vansky.framework.core.web.tree.SimpleTreeService;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单树结构实现
 * Auth: CK
 * Date: 2016/8/27
 */
public class SimpleTreeServiceImpl<T> implements SimpleTreeService<T> {
    public String findTreeStr(List<T> list, ModelCall<T> mc) {
        return JsonUtil.toJson(findTree(list, mc));
    }

    public List<SimpleTreeModel> findTree(List<T> list, ModelCall<T> mc) {
        List<SimpleTreeModel> simpleTreeModels = new ArrayList<SimpleTreeModel>(list.size());
        for (T t : list){
            simpleTreeModels.add(mc.convert(t));
        }
        return simpleTreeModels;
    }
}
