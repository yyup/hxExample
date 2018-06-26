package com.xmhx.cnlife.buzz.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

public class CommonUtils {
	
	// 下载编码字符集
	public static final String GBK = "GBK";
	public static final String UTF8 = "UTF-8";
	
	/**
	 * 将参数转为对象，支持一级对象
	 * @param clazz
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T SerializableObj(Map<String, String[]> parrmmap, Class<T> clazz) {
		T serobj = null;
		Map<String, Object> mymaps = new HashMap<String, Object>();
		try {
			Object serlizeobj = clazz.newInstance();// 调用默认方法来创建实例
			Field[] fields = clazz.getDeclaredFields();
			for (String key : parrmmap.keySet()) {
				mymaps.put(key, Array.get(parrmmap.get(key), 0));
			}
			for (Field field : fields) {
				if (mymaps.keySet().contains(field.getName())) {
					String value = String.valueOf(mymaps.get(field.getName()))
							.trim();
					field.setAccessible(true);
					field.set(serlizeobj, ConvertValue(field.getType(), value));
				}
			}
			serobj = (T) serlizeobj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serobj;
	}

	/**
	 * 
	 * @param clazz
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T MapToObj(Map<String, Object> parammap, Class<T> clazz) {
		T serobj = null;
		try {
			Object serlizeobj = clazz.newInstance();// 调用默认方法来创建实例
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (parammap.keySet().contains(field.getName())) {
					String value = String.valueOf(parammap.get(field.getName())).trim();
					field.setAccessible(true);
					field.set(serlizeobj, ConvertValue(field.getType(), value));
				}
			}
			serobj = (T) serlizeobj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serobj;
	}

	/**
	 * 类型转换
	 * @param clazz
	 * @param value
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Object ConvertValue(Class<?> clazz, String value) {
		Object obj = null;
		if (value != null && !"".equals(value.trim())) {
			if (clazz == int.class || clazz == Integer.class) {
				obj = Integer.valueOf(value);
			} else if (clazz == String.class) {
				obj = value;
			} else if (clazz == short.class || clazz == Short.class) {
				obj = Short.valueOf(value);
			} else if (clazz == long.class || clazz == Long.class) {
				obj = Long.valueOf(value);
			} else if (clazz == boolean.class || clazz == Boolean.class) {
				obj = Boolean.valueOf(value);
			} else if (clazz == float.class || clazz == Float.class) {
				obj = Float.valueOf(value);
			} else if (clazz == double.class || clazz == Double.class) {
				obj = Double.valueOf(value);
			} else if (clazz == Date.class) {
				obj = Date.parse(value);
			} else if (clazz == byte.class || clazz == Byte.class) {
				obj = Byte.valueOf(value);
			}
		}
		return obj;
	}
	
	/**
	 * 校验银行卡卡号
	 * 
	 * @param cardId 卡号
	 * @return
	 */
	public static boolean checkBankCard(String cardId) {
		char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
		if (bit == 'N') {
			return false;
		}
		return cardId.charAt(cardId.length() - 1) == bit;
	}

	/**
	 * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
	 * 
	 * @param nonCheckCodeCardId
	 * @return
	 */
	private static char getBankCardCheckCode(String nonCheckCodeCardId) {
		if (nonCheckCodeCardId == null
				|| nonCheckCodeCardId.trim().length() == 0
				|| !nonCheckCodeCardId.matches("\\d+")) {
			// 如果传的不是数据返回N
			return 'N';
		}
		char[] chs = nonCheckCodeCardId.trim().toCharArray();
		int luhmSum = 0;
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
	}

	/**
	 * Email隐藏字段
	 * @param email
	 * @return
	 */
	public static String encodeEmail(String email){
		if(email==null||email.trim().equals("")){
			return email;
		}
		if(email.indexOf("@")==1){
			return "*"+email.substring(email.indexOf("@"));
		}else if(email.indexOf("@")==2){
			return email.substring(0,1)+"*"+email.substring(email.indexOf("@"));
		}else if(email.indexOf("@")==3){
			return email.substring(0,1)+"**"+email.substring(email.indexOf("@"));
		}else if(email.indexOf("@")>3){
			return email.substring(0,3)+"******"+email.substring(email.indexOf("@"));
		}
		return null;
	}
	
	/**
	 * 获取内容图片路径
	 * 
	 * @param fileName 文件名
	 * @return 图片路径
	 */
	public static String getContentImgPath() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")); // 获取东八区时间
		return "images/content/" + c.get(Calendar.YEAR) + "/"
				+ (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DAY_OF_MONTH);
	}
	
	public static String getImgPath(HttpServletRequest request) {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")); // 获取东八区时间
		String string = "images/content/" + c.get(Calendar.YEAR) + "/"
				+ (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DAY_OF_MONTH);
		return request.getSession().getServletContext().getRealPath(string);
	}

}
