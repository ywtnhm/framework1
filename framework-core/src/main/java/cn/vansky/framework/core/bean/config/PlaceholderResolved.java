/*
 * Copyright (C) 2016 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.bean.config;

/**
 * Placeholder resolved call back
 * Auth: CK
 * Date: 2016/8/29
 */
public interface PlaceholderResolved {
    /**
     * parsed placeholder value returned.
     * @param placeholder
     * @return parsed string
     */
    String doResolved(String placeholder);
}
