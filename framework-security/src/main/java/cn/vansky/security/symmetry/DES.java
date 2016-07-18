/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.security.symmetry;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;

/**
 * DES-ECB加密解密实现
 * <p>DES加密算法对密钥有严格要求,密钥必须是8字节,数据没有硬性要求</p>
 * Author: CK.
 * Date: 2015/4/11.
 */
public class DES extends SymmetrySecurity {

    public DES(byte[] key) {
        this.key = key;
    }

    @Override
    public void validation(byte[] data, byte[] key) {
        if (key.length != 8) {
            throw new RuntimeException("Invalid DES key length (must be 8 bytes)");
        }
    }

    public Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance(DES_ECB_ALGORITHM);
    }

    /**
     * SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ConfigureEncryptAndDecrypt.DES_ECB_ALGORITHM);
     * DESKeySpec deSedeKeySpec = new DESKeySpec(key);
     * secretKeyFactory.generateSecret(deSedeKeySpec);
     * @param key the key array
     * @return
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.InvalidKeyException
     * @throws java.security.spec.InvalidKeySpecException
     */
    public Key generateRandomKey(byte[] key) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        return new SecretKeySpec(key, DES_ECB_ALGORITHM);
    }

    public AlgorithmParameterSpec getAlgorithmParameterSpec() {
        return null;
    }
}
