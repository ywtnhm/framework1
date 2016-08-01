/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.dao;

import cn.vansky.framework.common.entity.search.Searchable;
import cn.vansky.framework.core.orm.mybatis.SqlMapDaoSupport;
import cn.vansky.framework.core.orm.mybatis.plugin.page.Pagination;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
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
        ID id = (ID) entity.getPrimaryKey();
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
        ID id = (ID) entity.getPrimaryKey();
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

    public Pagination page(Pagination pagination, SqlMapDao.SqlCallback selectCount, SqlMapDao.SqlCallback select) {
        int totalCount = (Integer) getSqlSession().selectOne(selectCount.getSqlId(), selectCount.getParameters());

        List dataList = getSqlSession().selectList(select.getSqlId(), select.getParameters());
        pagination.init(totalCount, pagination.getLimit(), pagination.getCurrentPage());
        pagination.setRows(dataList);
        return pagination;
    }
    public  void deleteBantch(ID[] ids){
        getDaoMapper().deleteBantch(ids);
    }

    public <T extends FieldAccessVo> Page<T> findBySearchable(Searchable searchable) throws InvocationTargetException, IllegalAccessException {
      List<T> content=   getDaoMapper().findBySearchable(searchable);
        long total = searchable.hasPageable() ? countBySearchable(searchable) : content.size();
        return new PageImpl<T>(
                content,
                searchable.getPage(),
                total
        );
    }

    public <T extends FieldAccessVo> List<T> findBySort(Sort sort){
        return getDaoMapper().findBySort(sort);

    }

    public long countBySearchable(Searchable searchable){
        return getDaoMapper().countBySearchable(searchable);
    }
}
