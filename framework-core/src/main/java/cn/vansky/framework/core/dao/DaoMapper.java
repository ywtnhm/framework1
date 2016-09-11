/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.dao;

import cn.vansky.framework.core.orm.mybatis.plugin.search.vo.Searchable;
import cn.vansky.framework.core.orm.mybatis.plugin.search.vo.Sort;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * insert、delete、update、select method SQL of XML Mapper file
 * Author: CK
 * Date: 2015/6/5.
 */
public interface DaoMapper<T, ID extends Serializable> {
    /**
     * delete action by primary key
     *
     * @param id
     *            primary key
     * @return delete result. 1 means success
     */
    int deleteByPrimaryKey(ID id);

    /**
     * do insert entity
     *
     * @param record
     *            entity bean to insert
     * @return insert result 1 means success
     */
    int insert(T record);

    /**
     * do insert entity ignore null property
     *
     * @param record
     *            entity bean to insert
     * @return insert result 1 means success
     */
    int insertSelective(T record);

    /**
     * find entity by primary key
     *
     * @param id
     *            primary key
     * @return entity bean
     */
    T selectByPrimaryKey(ID id);

    /**
     * update entity by primary key ignore null property
     *
     * @param record
     *            entity bean
     * @return effective count
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * update entity by primary key
     *
     * @param record
     *            entity bean
     * @return effective count
     */
    int updateByPrimaryKey(T record);

    /**
     * do select count(*) mapped id
     *
     * @return record count
     */
    int count();

    /**
     * find all records
     *
     * @return all records
     */
    List<T> selectAll();
    /**
     * save batch
     */
    void insertBatch(List<T> list);

    void deleteBantch(ID[] ids);

    <T > List<T> findBySearchable(Searchable searchable) ;

    <T > List<T> findBySearchableForTree(Searchable searchable) ;

    <T > List<T> findBySort(Sort sort);

    long countBySearchable(Searchable searchable);
}
