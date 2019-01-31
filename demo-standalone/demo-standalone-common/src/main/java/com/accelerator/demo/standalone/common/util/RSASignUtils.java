package com.accelerator.demo.standalone.common.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public abstract class RSASignUtils {

    private static final String CIPHER_ALG = "RSA", SIGN_ALG = "SHA1withRSA";

    private static RuntimeException wrapperException(Exception e, String errorMsg, Object... args) {
        String renderErrorMsg = ArrayUtils.isEmpty(args) ? errorMsg : String.format(errorMsg, args);
        return new RuntimeException(renderErrorMsg, e);
    }

    public static boolean verify(String publicKey, String data, String sign) {
        return verify(publicKey, StringUtils.getBytesUtf8(data), Base64.decodeBase64(sign));
    }

    public static boolean verify(String publicKey, String data, byte[] sign) {
        return verify(publicKey, StringUtils.getBytesUtf8(data), sign);
    }

    public static boolean verify(String publicKey, byte[] data, byte[] sign) {
        return verify(loadPublicKey(publicKey), data, sign);
    }

    public static boolean verify(PublicKey publicKey, String data, String sign) {
        return verify(publicKey, StringUtils.getBytesUtf8(data), Base64.decodeBase64(sign));
    }

    public static boolean verify(PublicKey publicKey, String data, byte[] sign) {
        return verify(publicKey, StringUtils.getBytesUtf8(data), sign);
    }

    public static boolean verify(PublicKey publicKey, byte[] data, byte[] sign) {
        try {
            Signature signature = Signature.getInstance(SIGN_ALG);
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(sign);
        } catch (NoSuchAlgorithmException e) {
            throw wrapperException(e, "无%s算法！", SIGN_ALG);
        } catch (InvalidKeyException e) {
            throw wrapperException(e, "非法私钥！");
        } catch (SignatureException e) {
            throw wrapperException(e, "验签错误！");
        }
    }

    public static String sign(String privateKey, String data) {
        return sign(privateKey, StringUtils.getBytesUtf8(data));
    }

    public static String sign(String privateKey, byte[] data) {
        return sign(loadPrivateKey(privateKey), data);
    }

    public static String sign(PrivateKey privateKey, String data) {
        return sign(privateKey, StringUtils.getBytesUtf8(data));
    }

    public static String sign(PrivateKey privateKey, byte[] data) {
        try {
            Signature signature = Signature.getInstance(SIGN_ALG);
            signature.initSign(privateKey);
            signature.update(data);
            return Base64.encodeBase64String(signature.sign());
        } catch (NoSuchAlgorithmException e) {
            throw wrapperException(e, "无%s算法！", SIGN_ALG);
        } catch (InvalidKeyException e) {
            throw wrapperException(e, "非法私钥！");
        } catch (SignatureException e) {
            throw wrapperException(e, "签名错误！");
        }
    }

    public static PrivateKey loadPrivateKey(String privateKey) {
        try {
            byte[] _privateKey = Base64.decodeBase64(privateKey);
            KeySpec keySpec = new PKCS8EncodedKeySpec(_privateKey);
            KeyFactory keyFactory = KeyFactory.getInstance(CIPHER_ALG);
            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw wrapperException(e, "无%s算法！", CIPHER_ALG);
        } catch (InvalidKeySpecException e) {
            throw wrapperException(e, "非法私钥");
        }
    }

    public static PublicKey loadPublicKey(String publicKey) {
        try {
            byte[] _publicKey = Base64.decodeBase64(publicKey);
            KeySpec keySpec = new X509EncodedKeySpec(_publicKey);
            KeyFactory keyFactory = KeyFactory.getInstance(CIPHER_ALG);
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw wrapperException(e, "无%s算法！", CIPHER_ALG);
        } catch (InvalidKeySpecException e) {
            throw wrapperException(e, "非法公钥！");
        }
    }

    public static String generatorKey() {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(CIPHER_ALG);
            SecureRandom secureRandom = new SecureRandom();
            keyPairGen.initialize(1024, secureRandom);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            StringBuilder keyInfoBuilder = new StringBuilder();

            String privateKeyEncode = Base64.encodeBase64String(privateKey.getEncoded());
            keyInfoBuilder.append("PrivateKey: ")
                    .append(System.lineSeparator())
                    .append(privateKeyEncode);

            keyInfoBuilder.append(System.lineSeparator())
                    .append(System.lineSeparator());

            String publicKeyEncode = Base64.encodeBase64String(publicKey.getEncoded());
            keyInfoBuilder.append("PublicKey: ")
                    .append(System.lineSeparator())
                    .append(publicKeyEncode);

            return keyInfoBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw wrapperException(e, "无%s算法！", CIPHER_ALG);
        }
    }


}
