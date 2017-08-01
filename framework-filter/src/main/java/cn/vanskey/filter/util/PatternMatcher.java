/*
 * Copyright (C) 2016 hyssop, Inc. All Rights Reserved.
 */

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package cn.vanskey.filter.util;


public interface PatternMatcher {

    /**
     * Returns <code>true</code> if the given <code>source</code> matches the specified <code>pattern</code>,
     * <code>false</code> otherwise.
     * @param pattern the pattern to match against
     * @param source  the source to match
     * @return <code>true</code> if the given <code>source</code> matches the specified <code>pattern</code>,
     *         <code>false</code> otherwise.
     */
    boolean matches(String pattern, String source);
}
