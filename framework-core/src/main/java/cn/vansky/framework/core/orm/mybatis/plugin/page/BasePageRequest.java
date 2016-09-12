/*
 * Copyright (C) 2016 hyssop, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.orm.mybatis.plugin.page;

import cn.vansky.framework.core.orm.mybatis.plugin.search.vo.Sort;

/**
 * Created by framework .
 * Auth: hyssop
 * Date: 2016-09-12-14:47
 */
public interface BasePageRequest extends Pagination {
    /**
     * Returns the sorting parameters.
     *
     * @return
     */
    Sort getSort();

}
