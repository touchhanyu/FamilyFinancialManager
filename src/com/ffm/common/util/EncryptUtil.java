package com.ffm.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

import com.ffm.common.dict.ENCRYPT;

public class EncryptUtil {
	/**
	 * MD5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeMD5(String str) {
		return javaEncodeMD5(str);
	}

	/**
	 * 使用java提供的MD5方法加密
	 * 
	 * @param str
	 * @return
	 */
	protected static String javaEncodeMD5(String str) {
		String md5 = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] bytes = str.getBytes();
			messageDigest.update(bytes);
			byte[] digest = messageDigest.digest();
			BASE64Encoder base64Encoder = new BASE64Encoder();
			md5 = base64Encoder.encode(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			md5 = str;
			e.printStackTrace();
		}
		return md5;
	}

	public static String encodeAES(String signature, String str) {
		String res = null;
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(ENCRYPT.AES.toString());
			kgen.init(128, new SecureRandom(signature.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] encoded = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(encoded, ENCRYPT.AES.toString());
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = str.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			res = new String(result);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	protected static String encode(String str, ENCRYPT type) {
		String res = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(type.toString());
			byte[] bytes = str.getBytes();
			messageDigest.update(bytes);
			byte[] digest = messageDigest.digest();
			BASE64Encoder base64Encoder = new BASE64Encoder();
			res = base64Encoder.encode(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			res = str;
			e.printStackTrace();
		}
		return res;
	}
}