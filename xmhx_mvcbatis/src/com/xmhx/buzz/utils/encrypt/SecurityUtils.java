package com.xmhx.buzz.utils.encrypt;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;


public class SecurityUtils {
	private static final String encrypt_key = "04b1780745e8db8da8e68495c02b14b2";
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
	 * 利用Java 自带MD5算法进行加密
	 * @param target 进行加密的字符串
	 * @return
	 */
	public final static String encryetMD5(String target) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = target.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	/**
	 * 用户MD5进行双重加密
	 * @param target
	 * @return
	 */
	public static String MD52Encryet(String target){
		return encryetMD5(encryetMD5(target).toLowerCase());
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
}
