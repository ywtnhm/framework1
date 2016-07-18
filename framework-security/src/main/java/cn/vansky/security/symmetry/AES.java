/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.security.symmetry;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

/**
 * AES
 * <p>AES加密算法对密钥有严格要求,密钥必须是16字节,数据没有硬性要求</p>
 * Author: CK
 */
public class AES extends SymmetrySecurity {
    public AES(byte[] key) {
        this.key = key;
    }

    /**
     * 验证AES数据有效性及密钥有效性
     * @param data 加密数据
     * @param key 密钥(AES密钥必须是16位)
     */
    public void validation(byte[] data, byte[] key) {
        if (key.length != 16) {
            throw new RuntimeException("Invalid AES key length (must be 16 bytes)");
        }
    }

    public Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance(AES_ALGORITHM);
    }

    public Key generateRandomKey(byte[] key) {
        return new SecretKeySpec(key, AES_ALGORITHM);
    }

    public AlgorithmParameterSpec getAlgorithmParameterSpec() {
        return null;
    }
}
