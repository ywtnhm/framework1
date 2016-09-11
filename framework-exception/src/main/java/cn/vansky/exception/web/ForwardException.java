/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.exception.web;

/**
 * 错误页面异常
 * Auth: CK
 * Date: 2016/9/9
 */
public class ForwardException extends RuntimeException {
    public ForwardException() {
        super();
    }

    public ForwardException(String message) {
        super(message);
    }

    public ForwardException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForwardException(Throwable cause) {
        super(cause);
    }
}
