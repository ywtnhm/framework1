/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.kv.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/11/13
 */

public interface KvDao {

    /**
     * Execute Sql.
     *
     * @param sql the sql
     * @param namedParams the named params
     * @return the list
     */
    public List<Map<String, Object>> execute(String sql, Map<String, Object> namedParams);
}
