/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.core.orm.mybatis.annotation;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/6/21
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SqlMapper {
}
