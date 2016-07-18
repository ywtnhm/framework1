/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.security.dissymmetry;

import cn.vansky.security.ISecurity;
import cn.vansky.security.single.BASE64;

import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

/**
 * DSA.
 * Digital Signature Algorithm，数字签名
 * Author: CK.
 * Date: 2015/4/11.
 */
public class DSA extends DisSymmetrySecurity {

    public KeyPairGenerator getKeyPairGenerator() throws NoSuchAlgorithmException {
        return KeyPairGenerator.getInstance(ISecurity.DSA_ALGORITHM);
    }

    public KeyFactory getKeyFactory() throws NoSuchAlgorithmException {
        return KeyFactory.getInstance(ISecurity.DSA_ALGORITHM);
    }

    public Signature getSignature(KeyFactory keyFactory) throws NoSuchAlgorithmException {
        return Signature.getInstance(keyFactory.getAlgorithm());
    }

    private static String encryptBASE64(byte[] data) {
        return BASE64.encryptBASE64(data);
    }

    private byte[] decryptBASE64(String data) {
        return BASE64.decryptBASE64B(data);
    }
}
