package com.xmhx.buzz.utils.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 描述：加密类
 * @author wujin by 20150503
 *
 */
public class AesUtil {

	public static final String KEY_ALGORITHM = "AES";
	public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	public static final String VIPARA = "0102030405060708";
	public static final String ENCODING = "UTF-8";

	/**
	 * 加密
	 * @param content
	 * @param password
	 * @return
	 */
	public static byte[] encrypt(String content, String password) {
		try {
			SecretKeySpec skeySpec = getKey(password);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(VIPARA.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(content.getBytes());// "utf-8"
			return encrypted;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] decrypt(byte[] content, String password) {
		try {
			SecretKeySpec skeySpec = getKey(password);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(VIPARA.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(content);
			return original;
		} catch (NoSuchAlgorithmException e) {
			return null;
		} catch (IllegalBlockSizeException e) {
			return null;
		} catch (BadPaddingException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
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

	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static int encryptString(String srcStr, String password)
			throws UnsupportedEncodingException {
		String resultStr = "";
		password = CryptUtil.encryptToMD5(password);
		byte[] result = encrypt(srcStr, password);
		resultStr = bytesToHexString(result);
		return 0;
	}

	public static String encryptString1(String srcStr, String password)
			throws UnsupportedEncodingException {
		String resultStr = "";
		password = CryptUtil.encryptToMD5(password);
		byte[] result = encrypt(srcStr, password);
		resultStr = bytesToHexString(result);
		return resultStr;
	}

	public static String decryptString(String destStr, String password) {
		String resultStr = "";
		try {
			password = CryptUtil.encryptToMD5(password);
			byte[] result = hexStringToBytes(destStr);
			result = decrypt(result, password);
			resultStr = new String(result, "UTF-8");
		} catch (Exception e) {
			return null;
		}
		return resultStr;
	}

	private static SecretKeySpec getKey(String strKey) {
		byte[] arrBTmp = strKey.getBytes();
		byte[] arrB = new byte[16];

		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}

		SecretKeySpec skeySpec = new SecretKeySpec(arrB, KEY_ALGORITHM);
		return skeySpec;
	}

	public static void main(String[] args) throws Exception {
		String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<EPOSPROTOCOL><TRANCODE>199008</TRANCODE><PHONENUMBER>18917114557</PHONENUMBER><PACKAGEMAC>6C7EF8843D91D0ECD480586C94057EB5</PACKAGEMAC></EPOSPROTOCOL>";
		String password = "dynamicode";
		String result = "";
		// // ����
		System.out.println("����ǰ ��" + content);
		result = encryptString1(content, password);
		System.out.println("���ܺ�" + result);
		// System.out.println("���ܺ󳤶ȣ�" + result.length());
		// // ����
		password = "dynamicode";
		result = "7719abddab705fdfa19060520335d0afed617d7297115a1e4fc03a7846446c6b6e55bfb8d401ebc10ee1105710f5a3437bd2be8543c7db5990470d160f4a834d2f5bd880a3cb7b21c341e3c65636a6b3b1c802aeba7c895e34f889127abc4d6ce9c7761397315accf51d1e938a34a30731d43b41f4e550aa0870181140a51929a88b67bbc1f0211dfc1b4fc13885925454a2a81627fff6fe0fcb53aab9fd6adc411f22a8d3fbf93dc734022f7f70c5ad8f63f007eb605bdb1d68b8d3e58f0b0dc20d772edb3f23e1c44615c6cc511dd4f5ba716dead133b10a05c65d78d55a3f49ae8aa6ca5ef2b96f7a5065eafb9a71a0273945c3a078f539d4b857945e698f1f14e5861d9578cd11453df3bc6755c5788c5e9da8bb9740d2bdbc976722b25551f1af0abc46a7c8b8b73a0b42197676201fb6e06966cf7708027433d176d31e98da5cb742908d3bd84a867b8a1ff0d57b0062bb1533adb9ca8fe58f22e7c7f6f10185b91e7258439e526ac4a8171c13150d2a0aaf908da433c29b4104ae1580156ad5e65396a3ffb8f2581352208399e10d235ab30d5355d786fde39684fe3759c2c63e5c95bf0589bf89b37f22afb99924fcccac82eca4c0ac277036b23b49ee855f32682f6fea054fc3bc740af98fa1f0827e6099612b6121dd86bd426cd4ff495693f1af0ebb10a2e138cf1856a6f08f277e3ac265aede6631b4dd831b4de79145dc491bb363a1d7cb3dd8c6c2ae8d07285ae24e668f04b316f00375795578405c35a3b63e499758543ff8079d12a53683628ffcba72ad326f3f1737464148e17d97ce0da8ca4101c087d7306417b962f845ac51d8f8da61475d934a460dc3924c709fdf9c5b4a2302bf9db804a7fd599a58abc94f4c70b040f2478749fb6ac99468bffe8b85844b05966867b7a2c85a8001cac8dfcb3871557bc06c690681ce2cbc23903035268eff99bf4b59fc04e8ab931ac46aa0b7b8f41e7ab8c9fa95621bd9c8440213166f02ec69f255a27a74f735570727381488b9653b6dde576a44d44362bd18e320df10c074ce1fc19eda7dca7833b87c2f94fd4552d0009b41537c6b7bb6d814aa8f668951f91260ea2ae28b3672e55ffe9825d4a6eb4465";
		result = decryptString(result, password);

		// Etf.addNode("<EposProtocol><TransType>1000</TransType><PhoneNo>15800795692</PhoneNo><TerminalNo>12345567890abcdef</TerminalNo><PSAMNo>12345567890abcdef</PSAMNo><TerminalSerialNo>12345567890abcdef</TerminalSerialNo><Password>123456</Password><PackageMac>B2E3ADE1D1B2E3ADE1D1B2E3ADE1D1B2E3ADE1D1</PackageMac></EposProtocol>");
		System.out.println("���ܺ�" + result);
		// System.out.println(content.indexOf("<?xml version=\"1.0\" encoding=\"utf-8\"?>"));
		// CryptUtils.encryptToMD5(
	}

}
