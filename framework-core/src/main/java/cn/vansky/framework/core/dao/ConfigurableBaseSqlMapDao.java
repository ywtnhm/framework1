/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.dao;

import cn.vansky.framework.core.orm.mybatis.SqlMapDaoSupport;
import cn.vansky.framework.core.orm.mybatis.plugin.page.BasePagination;
import cn.vansky.framework.core.orm.mybatis.plugin.page.Pagination;
import cn.vansky.framework.core.orm.mybatis.plugin.search.vo.Searchable;
import cn.vansky.framework.core.orm.mybatis.plugin.search.vo.Sort;


import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/6/5.
 */
public abstract class ConfigurableBaseSqlMapDao<T extends FieldAccessVo, ID extends Serializable>
        extends SqlMapDaoSupport {

    public void flush() {
        getSqlSession().clearCache();
    }

    public abstract DaoMapper<T, ID> getDaoMapper();

    public void delete(ID id) {
        getDaoMapper().deleteByPrimaryKey(id);
    }

    public List<T> findAll() {
        return getDaoMapper().selectAll();
    }

    public T findById(ID id) {
        return getDaoMapper().selectByPrimaryKey(id);
    }

    public void save(T entity) {
        getDaoMapper().insert(entity);
    }

    public void saveSelective(T entity) {
        getDaoMapper().insertSelective(entity);
    }

    public void update(T entity) {
        getDaoMapper().updateByPrimaryKey(entity);
    }

    public void updateSelective(T entity) {
        getDaoMapper().updateByPrimaryKeySelective(entity);
    }

    public void saveOrUpdate(T entity) {
        ID id = entity.getPrimaryKey();
        if (id == null) {
            save(entity);
        } else {
            if (findById(id) != null) {
                update(entity);
            } else {
                save(entity);
            }
        }
    }

    public void saveOrUpdateSelective(T entity) {
        ID id = entity.getPrimaryKey();
        if (id == null) {
            saveSelective(entity);
        } else {
            if (findById(id) != null) {
                updateSelective(entity);
            } else {
                saveSelective(entity);
            }
        }
    }

    public void saveBatch(List<T> entitys) {
        getDaoMapper().insertBatch(entitys);
    }

    @SuppressWarnings("all")
    public Pagination page(Pagination pagination, SqlMapDao.SqlCallback selectCount, SqlMapDao.SqlCallback select) {
        int totalCount = getSqlSession().selectOne(selectCount.getSqlId(), selectCount.getParameters());

        List dataList = getSqlSession().selectList(select.getSqlId(), select.getParameters());
        pagination.init(totalCount, pagination.getLimit(), pagination.getCurrentPage());
        pagination.setRows(dataList);
        return pagination;
    }

    public void deleteBatch(ID[] ids) {
        getDaoMapper().deleteBatch(ids);
    }

    @SuppressWarnings("unchecked")
    public Pagination<T> findBySearchable(Searchable searchable) {
        List<T> content = getDaoMapper().findBySearchable(searchable);
        int currentPage = searchable.hasPageable() ? searchable.getPage().getCurrentPage() : 1;
        Pagination pagination = searchable.getPage();
        long total = searchable.hasPageable() ? countBySearchable(searchable.setPage(null)) : content.size();
        searchable.setPage(pagination);
        return new BasePagination<T>(content, Integer.parseInt(String.valueOf(total)), currentPage);
    }

    public List<T> findBySort(Sort sort) {
        return getDaoMapper().findBySort(sort);
    }

    public long countBySearchable(Searchable searchable) {
        return getDaoMapper().countBySearchable(searchable);
    }
}
