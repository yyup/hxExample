package com.xmhx.cnlife.buzz.utils.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @anthor:wujin
 * @since 2015-1-1
 */
public class CipherUtil {
	/*
	 * public static String LEFTSTR(String args, int n) { return
	 * StringUtils.left(args.trim(), n); }
	 * 
	 * public static String RIGHTSTR(String args, int n) { return
	 * StringUtils.right(args.trim(), n); }
	 */

	/**
	 * @param length 长度
	 * @return 生成随机数
	 */
	public static String getRomdan(int length) {
		String flag = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < Integer.valueOf(length).intValue(); ++i) {
			sb.append(flag.charAt(random.nextInt(flag.length())));
		}
		return sb.toString();
	}

	// 加密
	public static String XOREncrypt(String data) {
		StringBuffer result = new StringBuffer();
		String key = "tangdiit";
		int j = 0;
		for (int i = 0; i < data.length(); ++i) {
			if (j == key.length())
				j = 0;
			result.append(StringUtils.leftPad(
					Integer.toHexString(data.charAt(i) ^ key.charAt(j)), 2, '0'));
			++j;
		}
		return result.toString();
	}

	// 解密
	public static String XORDecrypt(String data) {
		StringBuffer result = new StringBuffer();
		String key = "tangdiit";
		int j = 0;
		for (int i = 0; i < data.length(); i += 2) {
			if (j == key.length())
				j = 0;
			int c = Integer.parseInt(data.substring(i, i + 2), 16);
			result.append((char) (c ^ key.charAt(j)));
			++j;
		}
		return result.toString();
	}

	// 对字符串进行md5操作
	public static String MD5STR(String msg) {
		byte[] b = MD5(msg.getBytes());
		return new String(Base64.encodeBase64(b));
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

	public static String SUBSTR(String args, int pos, int len) {
		int beginIndex = pos - 1;
		if (beginIndex < 0)
			beginIndex = 0;
		if (beginIndex > args.length())
			beginIndex = args.length();

		int length = len;
		if (length < 0)
			length = 0;

		if (beginIndex + length > args.length())
			length = args.length() - beginIndex;

		byte[] bytes = args.getBytes();
		return new String(bytes, beginIndex, length);
	}

	// private static String TriDes = "DESede/ECB/NoPadding";
	// public static final String ZENKEY = "11111111111111111111111111111111";
	public static final String ZENKEY = "97A6313111571151311144044301F837";

	/**
	 * �?des
	 */
	public static String DESedeEncrypt1(String key, String data)
			throws Exception {

		return DESedeEncrypt(key, Hex.decodeHex(data.toCharArray()));
	}

	/**
	 * 3DES加密,普�?字符�?
	 */
	public static String DESedeEncrypt(String key, Object szSrc)
			throws Exception {
		int szSrclen = 0;
		byte[] bsrc = null;
		if (szSrc instanceof String) {
			if (szSrc == null || "".equals((String) szSrc)) {
				return "";
			}
			szSrclen = ((String) szSrc).length();
			bsrc = ((String) szSrc).getBytes();
		} else {
			if (szSrc == null || ((byte[]) szSrc).length == 0) {
				return "";
			}
			szSrclen = ((byte[]) szSrc).length;
			bsrc = (byte[]) szSrc;
		}
		if (szSrc instanceof byte[])
			if (szSrclen % 8 != 0) {
				return "";
			}
		if (key.length() != 32 && key.length() != 48) {
			return "";
		}
		if (key.length() == 32) {
			key = key + key.substring(0, 16);
		}
		byte[] encoded = Des.encryptMode(Des.hexStr2ByteArr(key), bsrc);
		return Des.byte2hex(encoded);
	}

	/**
	 * 3DES解密 调用方法：String xx = DESedeEncrypt(key,
	 * Hex.decodeHex(data.toCharArray()));
	 */
	public static String DESedeDecrypt(String key, Object szSrc)
			throws Exception {
		String src = "";
		if (szSrc instanceof String) {
			if (szSrc == null || "".equals((String) szSrc)) {
				return "";
			}
			src = (String) szSrc;
		} else {
			src = new String((byte[]) szSrc);
		}
		if (key.length() != 32 && key.length() != 48) {
		}
		if (key.length() == 32) {
			key = key + key.substring(0, 16);
		}
		byte[] srcBytes = Des.decryptMode(Des.hexStr2ByteArr(key),
				Des.hexStr2ByteArr(src));
		return byte2hex(srcBytes);

	}

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

	/*
	 * //算pin block public static String PinEncrypt(String account,String
	 * passwd){ String result=""; String accountTemp1 = ""; int passwdLen =
	 * passwd.length(); if(passwdLen==0){ passwd = "FFFFFF"; }else
	 * if(passwdLen<6){ for(int i=0;i<6-passwdLen;i++){ passwd += "F"; } }
	 * String passwdTemp1 = "0"+passwdLen+passwd+"FFFFFFFF";
	 * if(account!=null&&!"".equals(account)){ int len = account.length();
	 * String accountTemp = account.substring(len-13,len-1); accountTemp1 =
	 * "0000"+accountTemp; }
	 * 
	 * // if(accountTemp1.equals("")){ result = passwdTemp1; } //pinblock else{
	 * byte [] accountByte = str2Bcd(accountTemp1); byte [] passwdByte =
	 * str2Bcd(passwdTemp1);
	 * 
	 * byte [] resultByte = new byte [8];
	 * 
	 * for(int i=0;i<8;i++){ resultByte[i] = (byte) (accountByte[i] ^
	 * passwdByte[i]); } result = bytesToHexString(resultByte); }
	 * 
	 * return result.toUpperCase(); }
	 */

	public static String PinEncrypt(String account, String passwd) {
		String result = "";
		String accountTemp1 = "";// 锟斤拷锟斤拷锟斤拷锟斤拷锟侥匡拷锟剿猴拷
		int passwdLen = passwd.length();
		if (passwdLen == 0) {
			passwd = "FFFFFF";
		} else if (passwdLen < 6) {
			for (int i = 0; i < 6 - passwdLen; i++) {
				passwd += "F";
			}
		}
		String passwdTemp1 = "0" + passwdLen + passwd + "FFFFFFFF";
		if (account != null && !"".equals(account)) {
			int len = account.length();
			String accountTemp = account.substring(len - 13, len - 1);
			accountTemp1 = "0000" + accountTemp;
		}

		if (accountTemp1.equals("")) {
			result = passwdTemp1;
		} else {
			byte[] accountByte = str2Bcd(accountTemp1);
			byte[] passwdByte = str2Bcd(passwdTemp1);

			byte[] resultByte = new byte[8];

			for (int i = 0; i < 8; i++) {
				resultByte[i] = (byte) (accountByte[i] ^ passwdByte[i]);
			}
			result = bytesToHexString(resultByte);
		}

		return result.toUpperCase();
	}

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	public static byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;

		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}

		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}

		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;

		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}

			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}

			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}

}
