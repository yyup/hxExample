package com.xmhx.buzz.utils.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class Des{

	private static final String Algorithm = "DESede"; 			// 定义 加密算法
	private static final String ECB = "DESEDE/ECB/NoPadding";	//
	private static final String DES = "DES"; 					// 定义 加密算法
	private static final String CFB = "DES/ECB/NoPadding"; 		// 

	public static void main(String[] args) throws Exception {
		String xx = DesEncrypt("73266245F1C22A3E", Hex.decodeHex("0000000000000000".toCharArray()));
		System.out.println(xx);
	}
	
	/**
	 * DES加密
	 */
	public static String DesEncrypt(String key, String szSrc) throws Exception {
		String str = "";
		if (szSrc.length() % 8 != 0) {
			throw new Exception("数据体的长度必须为8的倍数");
		}

		if (key.length() != 16) {
			throw new Exception("DES密钥长度必须为十六进制的16字节");
		}
		byte[] encoded = DESencryptMode(hexStr2ByteArr(key), szSrc.getBytes());
		str = new String(byte2hex(encoded));
		return str;
	}

	/**
	 * DES加密 byte[]
	 */
	public static String DesEncrypt(String key, byte[] szSrc) throws Exception {
		String str = "";
		if (szSrc.length % 8 != 0) {
			throw new Exception("数据体的长度必须为8的倍数");
		}
		if (key.length() != 16) {
			throw new Exception("DES密钥长度必须为十六进制的16字节");
		}
		byte[] encoded = DESencryptMode(hexStr2ByteArr(key), szSrc);
		str = new String(byte2hex(encoded));
		return str;
	}

	/**
	 * DES解密
	 */
	public static String DesDecrypt(String key, String szSrc) throws Exception {
		String str = "";
		if (key.length() != 16) {
			throw new Exception("DES密钥长度必须为十六进制的16字节");
		}
		byte[] srcBytes = DESdecryptMode(hexStr2ByteArr(key),
				hexStr2ByteArr(szSrc));
		str = new String(srcBytes);
		return str;
	}

	/**
	 * DES解密 byte[]
	 */
	public static byte[] DesDecrypt(String key, byte[] szSrc) throws Exception {
		if (key.length() != 16) {
			throw new Exception("DES密钥长度必须为十六进制的16字节");
		}
		byte[] srcBytes = DESdecryptMode(hexStr2ByteArr(key), szSrc);
		return srcBytes;
	}

	/**
	 * 3DES加密方法
	 */
	public static byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 加密
			Cipher c1 = Cipher.getInstance(ECB);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * 3DES解密方法
	 */
	public static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

			// 解密
			Cipher c1 = Cipher.getInstance(ECB);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// DES加密方法
	public static byte[] DESencryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, DES);
			// 加密
			Cipher c1 = Cipher.getInstance(CFB);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// DES解密方法
	public static byte[] DESdecryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, DES);
			// 解密
			Cipher c1 = Cipher.getInstance(CFB);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// 转换成十六进制字符串
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	// 将十六进制字符串转换成原始字节数组
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

}
