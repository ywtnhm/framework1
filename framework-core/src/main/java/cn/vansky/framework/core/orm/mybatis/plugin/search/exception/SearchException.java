/**
 * Copyright (c) 2005-2012 https://github.com/yuqiangcui
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.vansky.framework.core.orm.mybatis.plugin.search.exception;


/**
 * <p>User: hyssop
 * <p>Date: 16-1-17 上午11:43
 * <p>Version: 1.0
 */
public class SearchException extends RuntimeException {

    public SearchException(String msg) {
        super(msg);
    }

    public SearchException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
