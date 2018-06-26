package com.xmhx.cnlife.buzz.utils.encrypt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CryptUtil {
	/**
	 * ����MD5����
	 * 
	 * @param info
	 *            Ҫ���ܵ���Ϣ
	 * @return String ���ܺ���ַ�
	 * @throws UnsupportedEncodingException 
	 */
	public static String encryptToMD5(String info) throws UnsupportedEncodingException {
		byte[] digesta = null;
		try {
			// �õ�һ��md5����ϢժҪ
			MessageDigest alga = MessageDigest.getInstance("MD5");
			// ���Ҫ���м���ժҪ����Ϣ
			alga.update(info.getBytes("utf-8"));
			// �õ���ժҪ
			digesta = alga.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// ��ժҪתΪ�ַ�
		String rs = byte2hex(digesta);
		return rs;
	}

	/**
	 * mac����ԭ�Ӻ���
	 * 
	 * @param info
	 * @return
	 * @throws UnsupportedEncodingException 
	 */

	
	/**
	 * ����SHA����
	 * 
	 * @param info
	 *            Ҫ���ܵ���Ϣ
	 * @return String ���ܺ���ַ�
	 */
	public static String encryptToSHA(String info) {
		byte[] digesta = null;
		try {
			// �õ�һ��SHA-1����ϢժҪ
			MessageDigest alga = MessageDigest.getInstance("SHA-1");
			// ���Ҫ���м���ժҪ����Ϣ
			alga.update(info.getBytes());
			// �õ���ժҪ
			digesta = alga.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// ��ժҪתΪ�ַ�
		String rs = byte2hex(digesta);
		return rs;
	}

	// //////////////////////////////////////////////////////////////////////////
	/**
	 * �����ܳ�
	 * 
	 * @param algorithm
	 *            �����㷨,���� DES,DESede,Blowfish
	 * @return SecretKey ���ܣ��Գƣ���Կ
	 */
	public static SecretKey createSecretKey(String algorithm) {
		// ����KeyGenerator����
		KeyGenerator keygen;
		// ���� ��Կ����
		SecretKey deskey = null;
		try {
			// �������ָ���㷨��������Կ�� KeyGenerator ����
			keygen = KeyGenerator.getInstance(algorithm);
			// ���һ����Կ
			deskey = keygen.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// �����ܳ�
		return deskey;
	}

	/**
	 * ����ܳ׽���DES����
	 * 
	 * @param key
	 *            �ܳ�
	 * @param info
	 *            Ҫ���ܵ���Ϣ
	 * @return String ���ܺ����Ϣ
	 */
	public static String encryptToDES(SecretKey key, String info) {
		// ���� �����㷨,���� DES,DESede,Blowfish
		String Algorithm = "DES";
		// �������������� (RNG),(���Բ�д)
		SecureRandom sr = new SecureRandom();
		// ����Ҫ��ɵ�����
		byte[] cipherByte = null;
		try {
			// �õ�����/������
			Cipher c1 = Cipher.getInstance(Algorithm);
			// ��ָ������Կ��ģʽ��ʼ��Cipher����
			// ����:(ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE,UNWRAP_MODE)
			c1.init(Cipher.ENCRYPT_MODE, key, sr);
			// ��Ҫ���ܵ����ݽ��б��봦��,
			cipherByte = c1.doFinal(info.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �������ĵ�ʮ�������ʽ
		return byte2hex(cipherByte);
	}

	/**
	 * ����ܳ׽���DES����
	 * 
	 * @param key
	 *            �ܳ�
	 * @param sInfo
	 *            Ҫ���ܵ�����
	 * @return String ���ؽ��ܺ���Ϣ
	 */
	public static String decryptByDES(SecretKey key, String sInfo) {
		// ���� �����㷨,
		String Algorithm = "DES";
		// �������������� (RNG)
		SecureRandom sr = new SecureRandom();
		byte[] cipherByte = null;
		try {
			// �õ�����/������
			Cipher c1 = Cipher.getInstance(Algorithm);
			// ��ָ������Կ��ģʽ��ʼ��Cipher����
			c1.init(Cipher.DECRYPT_MODE, key, sr);
			// ��Ҫ���ܵ����ݽ��б��봦��
			cipherByte = c1.doFinal(hex2byte(sInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return byte2hex(cipherByte);
		return new String(cipherByte);
	}

	// /////////////////////////////////////////////////////////////////////////////
	/**
	 * �����ܳ��飬�������ף�˽�׷��뵽ָ���ļ���
	 * 
	 * Ĭ�Ϸ���mykeys.bat�ļ���
	 */
	public static void createPairKey() {
		try {
			// ����ض����㷨һ����Կ�������
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("DSA");
			// �������������� (RNG)
			SecureRandom random = new SecureRandom();
			// �������ô������������
			random.setSeed(1000);
			// ʹ�ø�����Դ����Ĭ�ϵĲ���ϣ���ʼ��ȷ����Կ��С����Կ�������
			keygen.initialize(512, random);// keygen.initialize(512);
			// �����Կ��
			KeyPair keys = keygen.generateKeyPair();
			// �õ�����
			PublicKey pubkey = keys.getPublic();
			// �õ�˽��
			PrivateKey prikey = keys.getPrivate();
			// ������˽��д�뵽�ļ�����
			doObjToFile("mykeys.bat", new Object[] { prikey, pubkey });
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����˽�׶���Ϣ����ǩ�� ��ǩ������Ϣ���뵽ָ�����ļ���
	 * 
	 * @param info
	 *            Ҫǩ�����Ϣ
	 * @param signfile
	 *            ������ļ�
	 */
	public static void signToInfo(String info, String signfile) {
		// ���ļ����ж�ȡ˽��
		PrivateKey myprikey = (PrivateKey) getObjFromFile("mykeys.bat", 1);
		// ���ļ��ж�ȡ����
		PublicKey mypubkey = (PublicKey) getObjFromFile("mykeys.bat", 2);
		try {
			// Signature �����������ɺ���֤����ǩ��
			Signature signet = Signature.getInstance("DSA");
			// ��ʼ��ǩ��ǩ���˽Կ
			signet.initSign(myprikey);
			// ����Ҫ���ֽ�ǩ�����֤�����
			signet.update(info.getBytes());
			// ǩ�����֤���и����ֽڵ�ǩ����ǩ��
			byte[] signed = signet.sign();
			// ������ǩ��,����,��Ϣ�����ļ���
			doObjToFile(signfile, new Object[] { signed, mypubkey, info });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ����ǩ���ļ� ��ݹ��ף�ǩ����Ϣ��֤��Ϣ�ĺϷ���
	 * 
	 * @return true ��֤�ɹ� false ��֤ʧ��
	 */
	public static boolean validateSign(String signfile) {
		// ��ȡ����
		PublicKey mypubkey = (PublicKey) getObjFromFile(signfile, 2);
		// ��ȡǩ��
		byte[] signed = (byte[]) getObjFromFile(signfile, 1);
		// ��ȡ��Ϣ
		String info = (String) getObjFromFile(signfile, 3);
		try {
			// ��ʼһ��Signature����,���ù�Կ��ǩ�������֤
			Signature signetcheck = Signature.getInstance("DSA");
			// ��ʼ����֤ǩ��Ĺ�Կ
			signetcheck.initVerify(mypubkey);
			// ʹ��ָ���� byte �������Ҫǩ�����֤�����
			signetcheck.update(info.getBytes());
			System.out.println(info);
			// ��֤�����ǩ��
			return signetcheck.verify(signed);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ��������ת��Ϊ16�����ַ�
	 * 
	 * @param b
	 *            �������ֽ�����
	 * @return String
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	/**
	 * ʮ������ַ�ת��Ϊ2����
	 * 
	 * @param hex
	 * @return
	 */
	public static byte[] hex2byte(String hex) {
		byte[] ret = new byte[8];
		byte[] tmp = hex.getBytes();
		for (int i = 0; i < 8; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}

	/**
	 * ������ASCII�ַ�ϳ�һ���ֽڣ� �磺"EF"--> 0xEF
	 * 
	 * @param src0
	 *            byte
	 * @param src1
	 *            byte
	 * @return byte
	 */
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	/**
	 * ��ָ���Ķ���д��ָ�����ļ�
	 * 
	 * @param file
	 *            ָ��д����ļ�
	 * @param objs
	 *            Ҫд��Ķ���
	 */
	public static void doObjToFile(String file, Object[] objs) {
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			for (int i = 0; i < objs.length; i++) {
				oos.writeObject(objs[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �������ļ���ָ��λ�õĶ���
	 * 
	 * @param file
	 *            ָ�����ļ�
	 * @param i
	 *            ��1��ʼ
	 * @return
	 */
	public static Object getObjFromFile(String file, int i) {
		ObjectInputStream ois = null;
		Object obj = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			for (int j = 0; j < i; j++) {
				obj = ois.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	/**
	 * AES����
	 * 
	 * @param content
	 *            ��Ҫ���ܵ�����
	 * @param password
	 *            ��������
	 * @return
	 */
	public static byte[] encryptToAES(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// ����������
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// ��ʼ��
			byte[] result = cipher.doFinal(byteContent);
			return result; // ����
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ����
	 * 
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		/*
		 * StringBuffer sb = new StringBuffer("hello"); // ִ��MD5����"Hello world!"
		 * System.out.println("Hello����MD5:" +
		 * CryptUtils.encryptToMD5(sb.toString()).length()); // ���һ��DES�㷨���ܳ�
		 * SecretKey key = CryptUtils.createSecretKey("DES"); //
		 * ���ܳ׼�����Ϣ"Hello world!" String str1 = CryptUtils.encryptToDES(key,
		 * sb.toString()); System.out.println("ʹ��des������ϢHelloΪ:" + str1); //
		 * ʹ������ܳ׽��� String str2 = CryptUtils.decryptByDES(key, str1);
		 * System.out.println("���ܺ�Ϊ��" + str2); // �������׺�˽��
		 */

		// <?xml version='1.0' encoding='utf-8'?>
		// <TokenProtocol><Epid>84cedebb2fed95d25c8ef2a705ec72f6</Epid>
		// <TokenSN>6FA7723CA56BF3313C108DDE35FC8506</TokenSN>
		// <Dynamicode>0F293B3A3CA1829A</Dynamicode>
		// <PackageMac>5010C2FF67011A266A3339608F942C244F4E8F6B</PackageMac>
		// </TokenProtocol>
		// <?xml version='1.0' encoding='utf-8'?>
		// <TokenProtocol><Epid>84cedebb2fed95d25c8ef2a705ec72f6</Epid>
		// <TokenSN>90324BC743686526819BAED12A31943F</TokenSN>
		// <Dynamicode>0C26EE0531AA6052</Dynamicode>
		// <PackageMac>UBDC/2cBGiZqMzlgj5QsJE9Oj2s=</PackageMac>
		// </TokenProtocol>

		// String str
		// ="62810440;68606773f1412f65022c09ab419bff29dd77414e730e8bbf76ae81617d6f8df85532d4cc741924a71b5de01e045e1a2be7b61cd6c2e90c92bd80edb9152e6b29b93506fa2bb3719d432c425d0b13c97afb575b3ce5fa0dc3a55df3f89c196cc0737363e256781c4e1ff0d3081c33157728f1d3ac0b16f80cf9bb8aada33e2a19ca0e11eb81d67025ba2b19e0c7b6e026";
		// System.out.println(EposCryptUtil.encryptToSHA(str));
		// //e10adc3949ba59abbe56e057f20f883e
		// //21232F297A57A5A743894A0E4A801FC3
		// System.out.println("#################"+EposCryptUtil.encryptToMD5("123456"));
		//	    	
		/*
		 * try { System.out.println("##########MD5#######"+MD5.md5("123456")); }
		 * catch (NoSuchAlgorithmException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */

		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
				"<EPOSPROTOCOL><TRANCODE>199008</TRANCODE><PHONENUMBER>18917114557</PHONENUMBER></EPOSPROTOCOL>";
//		System.out.println(encryptToSHA(str));
            System.out.println(encryptToMD5(str));
	}
}
