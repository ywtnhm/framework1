/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.cache.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/12/1
 */
public class RedisClientStatusPingChecker implements RedisClientStatusChecker {
    /**
     * 日志类
     */
    private final Log LOGGER = LogFactory.getLog(getClass());

    public boolean checkStatus(RedisClient redisClient) {
        if (redisClient == null) {
            return false;
        }
        try {
            // 客户端能ping通，返回true
            redisClient.ping();
            return true;
        } catch (Exception e) {
            LOGGER.error("check status by ping from client failed.", e);
        }
        return false;
    }
}
