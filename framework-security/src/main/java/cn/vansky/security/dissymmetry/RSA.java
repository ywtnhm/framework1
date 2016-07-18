/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.security.dissymmetry;

import cn.vansky.security.ISecurity;

import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

/**
 * <p>RSA公钥&私钥</p>
 *
 * @author CK
 */
public class RSA extends DisSymmetrySecurity {
    public KeyPairGenerator getKeyPairGenerator() throws NoSuchAlgorithmException {
        return KeyPairGenerator.getInstance(ISecurity.RSA_ALGORITHM);
    }

    public KeyFactory getKeyFactory() throws NoSuchAlgorithmException {
        return KeyFactory.getInstance(ISecurity.RSA_ALGORITHM);
    }

    public Signature getSignature(KeyFactory keyFactory) throws NoSuchAlgorithmException {
        return Signature.getInstance(ISecurity.SIGNATURE_ALGORITHM);
    }
}
