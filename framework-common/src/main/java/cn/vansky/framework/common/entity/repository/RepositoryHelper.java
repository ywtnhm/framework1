/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.vansky.framework.common.entity.repository;


import cn.vansky.framework.common.entity.callback.SearchCallback;
import cn.vansky.framework.common.entity.search.Searchable;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.orm.jpa.SharedEntityManagerCreator;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * 仓库辅助类
 * <p>User: hyssop
 * <p>Date: 13-4-14 下午5:28
 * <p>Version: 1.0
 */
public class RepositoryHelper {

    private Class<?> entityClass;
    private boolean enableQueryCache = false;

    /**
     * @param entityClass 是否开启查询缓存
     */
    public RepositoryHelper(Class<?> entityClass) {
        this.entityClass = entityClass;

    }









    /**
     * <p>ql条件查询<br/>
     * searchCallback默认实现请参考 {@see com.sishuok.es.common.repository.callback.DefaultSearchCallback}<br/>
     * <p/>
     * 测试用例请参考：{@see com.sishuok.es.common.repository.UserRepositoryImplForCustomSearchIT}
     * 和{@see com.sishuok.es.common.repository.UserRepositoryImplForDefaultSearchIT}
     *
     * @param sql
     * @param searchable     查询条件、分页 排序
     * @param searchCallback 查询回调  自定义设置查询条件和赋值
     * @return
     */
    public <M> StringBuilder  findAll(final String sql, final Searchable searchable, final SearchCallback searchCallback) {
        assertConverted(searchable);
        StringBuilder s = new StringBuilder(sql);
        searchCallback.prepareQL(s, searchable);
        searchCallback.prepareOrder(s, searchable);
        searchCallback.setValues(s, searchable);
        searchCallback.setPageable(s, searchable);
        return s;
    }

    /**
     * <p>按条件统计<br/>
     * 测试用例请参考：{@see com.sishuok.es.common.repository.UserRepositoryImplForCustomSearchIT}
     * 和{@see com.sishuok.es.common.repository.UserRepositoryImplForDefaultSearchIT}
     *
     * @param ql
     * @param searchable
     * @param searchCallback
     * @return
     */
    public StringBuilder count(final String ql, final Searchable searchable, final SearchCallback searchCallback) {

        assertConverted(searchable);

        StringBuilder s = new StringBuilder(ql);
        searchCallback.prepareQL(s, searchable);
        searchCallback.setValues(s, searchable);

        return s;
    }



    /**
     * @param ql
     * @param params
     * @param <M>
     * @return
     * @see RepositoryHelper#findAll(String, Pageable, Object...)
     */
    public <M> StringBuilder findAll(final String ql, final Object... params) {

        //此处必须 (Pageable) null  否则默认有调用自己了 可变参列表
        return findAll(ql, (Pageable) null, params);

    }

    /**
     * <p>根据ql和按照索引顺序的params执行ql，pageable存储分页信息 null表示不分页<br/>
     * 具体使用请参考测试用例：{@see com.sishuok.es.common.repository.UserRepository2ImplIT#testFindAll()}
     *
     * @param ql
     * @param pageable null表示不分页
     * @param params
     * @param <M>
     * @return
     */
    public <M> StringBuilder   findAll(final String ql, final Pageable pageable, final Object... params) {
        StringBuilder sb = new StringBuilder(ql);
        setParameters(sb, params);
       /* if (pageable != null) {
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
*/
        return sb;
    }

    /**
     * <p>根据ql和按照索引顺序的params执行ql，sort存储排序信息 null表示不排序<br/>
     * 具体使用请参考测试用例：{@see com.sishuok.es.common.repository.UserRepository2ImplIT#testFindAll()}
     *
     * @param ql
     * @param sort   null表示不排序
     * @param params
     * @param <M>
     * @return
     */
    public <M> StringBuilder findAll(final String ql, final Sort sort, final Object... params) {

        StringBuilder sb = new StringBuilder(ql);
        setParameters(sb, params);

        return sb;
    }




    /**
     * <p>根据ql和按照索引顺序的params执行ql统计<br/>
     * 具体使用请参考测试用例：com.sishuok.es.common.repository.UserRepository2ImplIT#testCountAll()
     *
     * @param ql
     * @param params
     * @return
     */
    public StringBuilder count(final String ql, final Object... params) {

   /*     Query query = entityManager.createQuery(ql);
        applyEnableQueryCache(query);*/
        StringBuilder sb = new StringBuilder(ql);
        setParameters(sb, params);

        return sb;
    }



    /**
     * 按顺序设置Query参数
     *
     * @param query
     * @param params
     */
    public void setParameters(StringBuilder query, Object[] params) {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.toString().replace(String.valueOf(i + 1), params[i].toString());
            }
        }
    }

    /**
     * 拼排序
     *
     * @param sort
     * @return
     */
    public String prepareOrder(Sort sort) {
        if (sort == null || !sort.iterator().hasNext()) {
            return "";
        }
        StringBuilder orderBy = new StringBuilder("");
        orderBy.append(" order by ");
        orderBy.append(sort.toString().replace(":", " "));
        return orderBy.toString();
    }





    private void assertConverted(Searchable searchable) {
        if (!searchable.isConverted()) {
            searchable.convert(this.entityClass);
        }
    }




}
