package cn.vansky.framework.core.web.easyUI.service;

import cn.vansky.framework.common.util.JsonUtil;
import cn.vansky.framework.core.web.easyUI.model.EasyUITreeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2016/1/23
 */
public class EasyUITreeServiceImpl<T> implements EasyUITreeService<T> {
    public String findTreeStr(List<T> list, ModelCall<T> mc) {
        return JsonUtil.toJson(findModel(list, mc).getChildren());
    }

    public List<EasyUITreeModel> findChildren(List<T> list, ModelCall<T> mc) {
        return findModel(list, mc).getChildren();
    }

    private EasyUITreeModel findModel(List<T> list, ModelCall<T> mc) {
        if (null == list) {
            throw new RuntimeException("没有EasyUI菜单");
        }
        Map<Integer, EasyUITreeModel> p = new HashMap<Integer, EasyUITreeModel>(list.size() + 1);
        // 最外层,默认为0
        EasyUITreeModel root = new EasyUITreeModel();
        root.setId(0);
        p.put(root.getId(), root);
        findModel(list, p, mc);
        root.setId(null);
        return root;
    }


    /**
     * 递归遍历菜单
     * @param list 菜单列表
     * @param p 最终的菜单map
     * @param mc 具体业务回调
     */
    private void findModel(List<T> list, Map<Integer, EasyUITreeModel> p, ModelCall<T> mc) {
        if (null == list || list.isEmpty()) {
            return;
        }
        List<T> fail = new ArrayList<T>();
        for (T t : list) {
            // 当前菜单没添加到map中
            setTreeModel(t, p, mc, fail);
        }
        if (!fail.isEmpty()) {
            // 递归
            findModel(fail, p, mc);
        }
    }

    /**
     * map中不包括当前菜单且父菜单存在,添加当前菜单到map中
     * @param t 业务自己数据
     * @param p 最终map
     * @param mc 自己实现的业务回调接口
     * @param fail 没有找到父菜单的菜单列表
     */
    private void setTreeModel(T t, Map<Integer, EasyUITreeModel> p, ModelCall<T> mc, List<T> fail) {
        EasyUITreeModel model = mc.convert(t);
        if (model == null) {
            throw new RuntimeException("回调方法生成EasyUITreeModel错误");
        }
        // map中不包括当前菜单且包括父菜单
        if (!p.containsKey(model.getId()) && null != p.get(model.getPid())) {
            // 添加到map中
            p.put(model.getId(), model);
            EasyUITreeModel obj = p.get(model.getPid());
            if (null != obj) {
                obj.addChildren(model);
            }
        } else {
            fail.add(t);
        }
    }
}
