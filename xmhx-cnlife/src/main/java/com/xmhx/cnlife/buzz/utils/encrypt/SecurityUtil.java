package com.xmhx.cnlife.buzz.utils.encrypt;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;


public class SecurityUtil {
	private static final String encrypt_key = "5tri6i25500271qP6f399c2z1xhzPn6w";
	public static String MD5(String paramString) {
		return MD5(paramString, Charset.defaultCharset());
	}

	public static String MD5(String paramString1, String paramString2) {
		return MD5(paramString1, Charset.forName(paramString2));
	}

	public static String MD5(String paramString, Charset paramCharset) {
		char[] localObject = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', 'a', 'b', 'c', 'd', 'e', 'f' };
		MessageDigest localMessageDigest = null;
		try {
			localMessageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		localMessageDigest.update(paramString.getBytes(paramCharset));

		byte[] tempbyte = localMessageDigest.digest();
		char[] tempchar = new char[32];
		int i = 0;
		for (int j = 0; j < 16; ++j) {
			int k = tempbyte[j];
			tempchar[(i++)] = localObject[(k >>> 4 & 0xF)];
			tempchar[(i++)] = localObject[(k & 0xF)];
		}
		return new String(tempchar);
	}
	
	public static byte[] MD5(byte[] msg) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(msg);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return messageDigest.digest();
	}
	
	//对字符串进行md5操作
	public static String MD5STR(String msg) {
		byte[] b = MD5(msg.getBytes());
		return new String(Base64.encodeBase64(b));
	}
	
	/**
	 * 加密方法
	 * 
	 * @param paramString1
	 *            需要加密的字符串
	 * @param paramString2
	 *            加密密钥
	 * @return 返回加密后的字符串
	 */
	public static String encrypt(String src) {
		try {
			return AesUtil.encryptString1(src, MD5STR(encrypt_key));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 加密方法
	 * 
	 * @param paramString1
	 *            需要解密的字符串
	 * @param paramString2
	 *            解密密钥
	 * @return 返回解密后的字符串
	 */
	public static String decrypt(String src) {
		return AesUtil.decryptString(src,MD5STR(encrypt_key));
	}
	public static void main(String[] paramArrayOfString) {
	}
}
