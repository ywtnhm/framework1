/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.security;

/**
 * 对称解密接口
 *
 * @author CK
 */
public interface IDecrypt {
    /**
     * 解密
     *
     * @param data 解密前内容[数组]
     * @return 解密后内容[数组]
     */
    public byte[] decrypt(byte[] data);
}
