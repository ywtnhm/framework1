/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.service;

import cn.vansky.framework.core.dao.SqlMapDao;
import cn.vansky.framework.core.dao.FieldAccessVo;
import cn.vansky.framework.core.orm.mybatis.plugin.page.Pagination;
import cn.vansky.framework.core.orm.mybatis.plugin.search.vo.Searchable;
import cn.vansky.framework.core.orm.mybatis.plugin.search.vo.Sort;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

/**
 * XML配置Service实现
 * Author: CK
 * Date: 2015/6/5.
 */
public abstract class GenericSqlMapServiceImpl<T extends FieldAccessVo, ID extends Serializable>
        implements GenericService<T, ID> {

    public abstract SqlMapDao<T, ID> getDao();

    @SuppressWarnings("unchecked")
    public void delete(T entity) {
        Assert.notNull(entity, "delete failed due to entity is null");
        getDao().delete((ID) entity.getPrimaryKey());
    }

    public List<T> findAll() {
        return getDao().findAll();
    }

    public T findById(ID id) {
        return getDao().findById(id);
    }

    @SuppressWarnings("unchecked")
    public ID saveEntity(T entity) {
        Assert.notNull(entity, "save entity failed due to entity is null");
        getDao().save(entity);
        return (ID) entity.getPrimaryKey();
    }

    @SuppressWarnings("unchecked")
    public ID saveEntitySelective(T entity) {
        Assert.notNull(entity, "save entity failed due to entity is null");
        getDao().saveSelective(entity);
        return (ID) entity.getPrimaryKey();
    }
    public void deleteBatch(ID[] ids){
        getDao().deleteBatch(ids);
    }

    public void updateEntity(T entity) {
        Assert.notNull(entity, "update entity failed due to entity is null");
        getDao().update(entity);
    }

    public void updateEntitySelective(T entity) {
        Assert.notNull(entity, "update entity failed due to entity is null");
        getDao().updateSelective(entity);
    }

    public T saveOrUpdate(T entity) {
        Assert.notNull(entity, "save or update entity failed due to entity is null");
        getDao().saveOrUpdate(entity);
        return entity;
    }

    public T saveOrUpdateSelective(T entity) {
        Assert.notNull(entity, "save or update entity failed due to entity is null");
        getDao().saveOrUpdateSelective(entity);
        return entity;
    }

    public void saveBatch(List<T> entitys) {
        Assert.notNull(entitys, "saveBatch entitys failed due to entitys is null");
        getDao().saveBatch(entitys);
    }

    /**
     * 条件查询 searchable
     *
     * @param searchable
     * @return
     */
    public Pagination<T> findBySearchable(Searchable searchable) {
        return getDao().findBySearchable(searchable);
    }

    /**
     * 条件查询 sort
     *
     * @param sort
     * @return
     */
    public List<T> findBySort(Sort sort) {
        return getDao().findBySort(sort);
    }

    /**
     * 根据条件统计所有记录数
     *
     * @param searchable
     * @return
     */
    public long countBySearchable(Searchable searchable) {
        return getDao().countBySearchable(searchable);
    }
}
