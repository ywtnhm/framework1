/*
 * Copyright (C) 2016 hyssop, Inc. All Rights Reserved.
 */

package cn.vansky.framework.tree.convert;

import cn.vansky.framework.core.web.easyUI.model.EasyUITreeModel;
import cn.vansky.framework.tree.bo.Treeable;
import cn.vansky.framework.tree.model.common.Tree;

import java.util.List;

/**
 * Created by framework .
 * Auth: hyssop
 * Date: 2016-09-09
 */
public interface ConvertTool<T extends Tree, S extends Treeable> {
    /**
     * 返回给前端Tree数据
     * @param list 业务数据list
     * @param mc 业务回调接口
     * @return 返回给前端Tree数据
     */
    public String findTreeStr(List<S> list, ModelCall<T,S> mc);

    /**
     * 返回树列表
     * @param list 列表
     * @param mc 回调接口
     * @return 返回值
     */
    public List<T> findChildren(List<S> list, ModelCall<T,S> mc);
    /**
     * 具体业务实现接口
     * @param <T> 业务数据转换成EasyUITreeModel
     */
    public static interface ModelCall<S,T>{
        /**
         * 业务数据转换成属性结构
         * @param t 业务数据
         * @return EasyUITreeModel
         */
        T  convert(S t);
    }
}
