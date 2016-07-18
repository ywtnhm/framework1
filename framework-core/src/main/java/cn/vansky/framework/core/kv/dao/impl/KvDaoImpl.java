/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.kv.dao.impl;

import cn.vansky.framework.core.kv.dao.KvDao;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/11/13
 */
public class KvDaoImpl implements KvDao {

    private DataSource dataSource;

    public List<Map<String, Object>> execute(String sql, Map<String, Object> namedParams) {
        NamedParameterJdbcTemplate tpl = new NamedParameterJdbcTemplate(dataSource);
        return tpl.queryForList(sql, namedParams);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
