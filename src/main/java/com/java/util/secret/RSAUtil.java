package com.java.util.secret;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import com.java.util.base.Base64;
 
public class RSAUtil {
    
	public static final String PUBLIC_KEY_STR = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnQkqVIaqfWO2ltxe3dTi3HbOvGeKcKX3hPmOwpMYQslI61R0Qh8/hbTs9C6l89qxBjfOh2JVh8NyamjR+Rpc5QiqLtfpevwQUquw26rY80DYvbuSlvouDn3vVSh++gwp+lkD6igrMEBmIVq0eJlUiywNeZEtvnI+Mz9klKyhiBH5JJRFq4lWvd4433jng9Ykxj7PsafMaEu3IuPrhRNqt9v+ZKJsdn0C1SbiLysObZXMjlTq1PuNzJ+9ig6iDr5by6wrJxFmwUQ6bsrhEPxelEu5CumF+8r3Xygbh9mDkGM59UA0MU/qgyUqqD2v6mUYPScwbODmMrngZHOaWl5uoQIDAQAB";
	public static final String PRIVATE_KEY_STR = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCdCSpUhqp9Y7aW3F7d1OLcds68Z4pwpfeE+Y7CkxhCyUjrVHRCHz+FtOz0LqXz2rEGN86HYlWHw3JqaNH5GlzlCKou1+l6/BBSq7DbqtjzQNi9u5KW+i4Ofe9VKH76DCn6WQPqKCswQGYhWrR4mVSLLA15kS2+cj4zP2SUrKGIEfkklEWriVa93jjfeOeD1iTGPs+xp8xoS7ci4+uFE2q32/5komx2fQLVJuIvKw5tlcyOVOrU+43Mn72KDqIOvlvLrCsnEWbBRDpuyuEQ/F6US7kK6YX7yvdfKBuH2YOQYzn1QDQxT+qDJSqoPa/qZRg9JzBs4OYyueBkc5paXm6hAgMBAAECggEAWa7GUgemjn3H5VhthVrzRN1FLCwl0De4qGStt0mybVHvJxbQXoLiEEZHnRyXAjE/MEHm3UK3fhWM3mCGqjD4JIVSS/ZbDTWvTMdo9csYo7PBFHpvOXIfo38glA3QlpUj1CnKJoxSPfhJW3sl3koTEnhSKb9T1JQCGdp2YsJEbiSK4BEk7+195KuBVz8r907WSzwn9SlBhRnwuWnXROJNhN+gSDSRlPOz2JbPMonizsLIz5P1yF3N/ACFMOWAn0OW3vuLwiCf36mFMkjakTGnB0mBN3cOliXeMq1iRJ17yEkMJg2N+ghBQEd6d9kQlOyunRjg50tq6xfsTvfoxWIvAQKBgQD6iJGFHu4yyquLFlj9z6a860nhmd95K8+ds0OgJKrNbmSqX2WhJLmQ8DrT/ggGjR2/u6F7HkdxjfTXAxE/iEIxgc78ZQANhfYGIKlvJGLJbo0LsN3Dd0Dwp5UH+hYAAq7Dh26Pqe8LpOy6hTnK3/U3eEbwU6bgyJe9tew6J2pzGQKBgQCgdlZF3P+CRQwPr3VObGcgGvXkpRyguo9EAukXtCv4URBsIAQtAjizceLIOapeFMvTrk7Typi1K5vDQGl4fVF4YaZkCtcyUy43LeMyb9cqqLWsKy2BzhPcCHN/VdfdzKcbvUklv8cd5mVX0a303j5HaJjNRVPVRcfosBY4TOyQyQKBgH9qw6/Tiku0vcgQRpRXbrK4a6vcaUakHIRU7rjczS2LzpDD0dlB50qqkxDMKuLCFHY2BCusu6MqEnLj8XrBXwD0xwOgjoVSM0zsgZ+v/rn5iqx+eFr4JMQARxB2hYfd1WpGT9mrEJN4fpliNoFSo9FhT5rcwIukGWB3zLvK3h3JAoGAfn7LZCfkBP4JlBPtlfU/FsAqOCUxfEOVzTe+KUGPqCG/oH1czV6C9HNDJDTRaXlbdXRkD/IWkDvgfvu4KZQhNZVFZnhlTPbl5/n0sT4ZkTeOgDtJlJqwbTT8V9WEZwV+dw+xKmEUxy1pluFlETRiREjgrHGKtQZdocGuwpNhqoECgYEAkgWiDd69Ngx94Ok9G0Eu6IvYAq2Oi2LAqnINmGWKt9iC7O70ir1sDOC6ud0a1xIjnQ8qVK7ISlTgUKDCMRkaUXZS/9HN22ebeTeFwlo0GUC8m131QTzp9wi2O/QUS3+Hu7PhcwvAbWon4vmg5Nkyplwz1IAFeuRYmu0UZvfhbyk=";
	
	/**
	 * 加密
	 */
	public static String encrypt(String painText,String pubStr) throws Exception {
		//将Base64编码后的公钥转换成PublicKey对象
		PublicKey publicKey = RSAUtil.string2PublicKey(pubStr);
		//用公钥加密
		byte[] publicEncrypt = RSAUtil.publicEncrypt(painText.getBytes(), publicKey);
		//加密后的内容Base64编码
		String byte2Base64 = RSAUtil.byte2Base64(publicEncrypt);
		return byte2Base64;
	}
	
	/**
	 * 解密
	 */
	public static String decrypt(String secretText,String privateStr) throws Exception {
		//将Base64编码后的私钥转换成PrivateKey对象
        PrivateKey privateKey = RSAUtil.string2PrivateKey(privateStr);
        //加密后的内容Base64解码
        byte[] base642Byte = RSAUtil.base642Byte(secretText);
        //用私钥解密
        byte[] privateDecrypt = RSAUtil.privateDecrypt(base642Byte, privateKey);
        //解密后的明文
        return new String(privateDecrypt);
	}
    
    //将Base64编码后的公钥转换成PublicKey对象
    private static PublicKey string2PublicKey(String pubStr) throws Exception{
        byte[] keyBytes = base642Byte(pubStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }
    
    //将Base64编码后的私钥转换成PrivateKey对象
    private static PrivateKey string2PrivateKey(String priStr) throws Exception{
        byte[] keyBytes = base642Byte(priStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }
    
    //公钥加密
    private static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }
    
    //私钥解密
    private static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }
    
    //字节数组转Base64编码
    private static String byte2Base64(byte[] bytes){
        return Base64.encode(bytes);
    }
    
    //Base64编码转字节数组
    private static byte[] base642Byte(String base64Key) throws IOException{
        return Base64.decode(base64Key);
    }
    
//    //生成秘钥对
//    private static KeyPair getKeyPair() throws Exception {
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        keyPairGenerator.initialize(2048);
//        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//        return keyPair;
//    }
//    
//    //获取公钥(Base64编码)
//    private static String getPublicKey(KeyPair keyPair){
//        PublicKey publicKey = keyPair.getPublic();
//        byte[] bytes = publicKey.getEncoded();
//        return byte2Base64(bytes);
//    }
//    
//    //获取私钥(Base64编码)
//    private static String getPrivateKey(KeyPair keyPair){
//        PrivateKey privateKey = keyPair.getPrivate();
//        byte[] bytes = privateKey.getEncoded();
//        return byte2Base64(bytes);
//    }
    
}
