package com.xmhx.cnlife.buzz.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 文本操作工具类 
 * @author wujin
 * @since 2014-08-20
 */
public class TextUtils {
	
	private static Logger logger = Logger.getLogger(TextUtils.class);
	
	private static List<String> sqlBegin;
	
	static{
		sqlBegin = new ArrayList<String>();
		sqlBegin.add("select");
		sqlBegin.add("insert");
		sqlBegin.add("update");
		sqlBegin.add("delete");
		sqlBegin.add("drop");
	}
	
	/**
	 * 对字符串对象进行非空判断
	 * @param target 目标字符串
	 * @return target!=null && target!='' 则返回true , 否则返回false
	 */
	public static boolean notEmpty(String target) {
		return (target != null && !target.trim().equals(""));
	}
	
	/**
	 * 对字符串对象进行空判断
	 * @param target 目标字符串
	 * @return target!=null && target!='' 则返回true , 否则返回false
	 */
	public static boolean isEmpty(String target) {
		return (target == null || target.trim().equals(""));
	}
	
	/**
	 * 对字符串对象进行非空判断，而且不能为零
	 * @param target 目标字符串
	 * @return target!=null && !target.equals("") && !"0".equals(target) 则返回true , 否则返回false
	 */
	public static boolean notEmptyAndNotZero(String target) {
		return (target != null && !target.equals("") && !"0".equals(target));
	}

	/**
	 * 将阿拉伯数字转换为中文数字
	 * @param temp
	 * @return
	 */
	public static String NumToChinese(String temp) {
		// 单位数组
		String[] units = new String[] { "十", "百", "千", "万", "十", "百", "千", "亿" };
		// 中文大写数字数组
		String[] numeric = new String[] { "零", "一", "二", "三", "四", "五", "六",
				"七", "八", "九" };
		String res = "";
		if (null != temp) {
			// 遍历一行中所有数字
			for (int k = -1; temp.length() > 0; k++) {
				// 解析最后一位
				int j = Integer.parseInt(temp.substring(temp.length() - 1,
						temp.length()));
				String rtemp = numeric[j];
				// 数值不是0且不是个位 或者是万位或者是亿位 则去取单位
				if (j != 0 && k != -1 || k % 8 == 3 || k % 8 == 7) {
					rtemp += units[k % 8];
				}
				// 拼在之前的前面
				res = rtemp + res;
				// 去除最后一位
				temp = temp.substring(0, temp.length() - 1);
			}
			// 去除后面连续的零零..
			while (res.endsWith(numeric[0])) {
				res = res.substring(0, res.lastIndexOf(numeric[0]));
			}
			// 将零零替换成零
			while (res.indexOf(numeric[0] + numeric[0]) != -1) {
				res = res.replaceAll(numeric[0] + numeric[0], numeric[0]);
			}
			// 将 零+某个单位 这样的窜替换成 该单位 去掉单位前面的零
			for (int m = 1; m < units.length; m++) {
				res = res.replaceAll(numeric[0] + units[m], units[m]);
			}
		}
		return res;
	}

	/**
	 * 过滤文本中HTML标签，返回文本字符串
	 * @param inputString
	 * @return
	 */
	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
	
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签
	
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
	
			textStr = htmlStr;	
		} catch (Exception e) {
			logger.error("Html2Text: " + e.getMessage());
		}
		return textStr;
	}
	
	/**
	 * 判断内容是否为HTML/JS
	 * @param inputString 字符
	 * @return
	 */
	public static boolean TextisHtml(String inputString) {
		if (inputString == null) {
			return Boolean.TRUE;
		}
		if (!inputString.equals(Html2Text(inputString))){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	/**
	 * 判断一个字符串是否是SQL语句
	 * @param descsort
	 * @return
	 */
	public static boolean notSQL(String descsort) {
		for(String sql : sqlBegin){
			if(descsort.startsWith(sql)){//包含则认为字符串为SQL
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}
	
	/**
	 * 格式化小数
	 * @param number 数字字符串
	 * @param fixed 保存小数位数
	 * @return
	 */
	public static String formatNumber(String number, int fixed) {
		//方式一：
		StringBuilder mat = new StringBuilder("0.");
		for (int i = 0; i < fixed; i++) {
			mat.append("0");
		}
		DecimalFormat format = new DecimalFormat(mat.toString());
		return format.format(new BigDecimal(number));
		
		//方式二：
		/*NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        return nf.format(new BigDecimal(number));*/
	}
	
	/**
	 * 字符串去空格
	 * @param text 字符串
	 * @return
	 */
	public static String trim(String text) {
		if(notEmpty(text)){
			return text.trim();
		}
		return "";
	}
	
	/**
	 * 将Object对象转化为Integer对象
	 * @param object 必须为数字型
	 * @return
	 */
	public static int objToInteger(Object object) {
		if (object != null) {
			return Integer.valueOf(String.valueOf(object));
		}
		return 0;
	}
	
	/**
	 * 判断字符串是否为纯数字
	 * @param chars
	 * @return
	 */
	public static boolean charsIsNumber(String chars) {
		if(TextUtils.notEmpty(chars)){
			Pattern pattern = Pattern.compile("[0-9]{1,}");
			Matcher matcher = pattern.matcher((CharSequence)chars);
			return matcher.matches();
		}
		return false;
	}
	
	/**
	 * 字符串指定截位
	 * @param args
	 * @param pos
	 * @param len
	 * @return
	 */
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
	
	public static int strToInt(String paramString, int paramInt) {
		try {
			paramInt = Integer.parseInt(paramString);
		} catch (Exception localException) {
			logger.error(localException);
		}
		return paramInt;
	}

	public static long strToLong(String paramString, long paramLong) {
		long l = paramLong;
		try {
			l = Long.parseLong(paramString);
		} catch (Exception localException) {
			logger.error(localException);
		}
		return l;
	}

	public static float strToFloat(String paramString, float paramFloat) {
		try {
			paramFloat = Float.parseFloat(paramString);
		} catch (Exception localException) {
			logger.error(localException);
		}
		return paramFloat;
	}

	public static double strToDouble(String paramString, double paramDouble) {
		double d = paramDouble;
		try {
			d = Double.parseDouble(paramString);
		} catch (Exception localException) {
			logger.error(localException);
		}
		return d;
	}

	public static boolean strToBoolean(String paramString, boolean paramBoolean) {
		try {
			paramBoolean = Boolean.parseBoolean(paramString);
		} catch (Exception localException) {
			logger.error(localException);
		}
		return paramBoolean;
	}

	public static String strToStr(String paramString1, String paramString2) {
		if ((paramString1 != null) && (!(paramString1.isEmpty())))
			paramString2 = paramString1;
		return paramString2;
	}
	
	/**
	 * 去除字符串最后的逗号
	 * @param input	,
	 * @return
	 */
	public static String removelastComma(String input) {
		String regex = "^.+\\,$";
		if (notEmpty(input) && input.matches(regex)) {
			return removelastComma(input.substring(0, input.length() - 1));
		} else {
			return input;
		}
	}
	
	/**
	 * 去除字符串最后的根号
	 * @param input \ 或者 /
	 * @return
	 */
	public static String removelastSymbol(String input) {
		String regex = "^.+[\\\\/]$";
		if (notEmpty(input) && input.matches(regex)) {
			return removelastSymbol(input.substring(0, input.length() - 1));
		} else {
			return input;
		}
	}
	
	/**
	 * 去除字符串首字符为分号
	 * @param input
	 * @return
	 */
	public static String removefirstsemicolon(String input) {
		String regex = "^;.+$";
		if (notEmpty(input) && input.matches(regex)) {
			return removefirstsemicolon(input.substring(1));
		} else {
			return input;
		}
	}
	
	/**
	 * 邮件前缀
	 * @return
	 */
	public static String mailprefix() {
		StringBuilder mail = new StringBuilder();
		mail.append("<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'></head><body>");		
		return mail.toString();
	}
	
	/**
	 * 邮件后缀
	 * @return
	 */
	public static String mailsuffix() {
		StringBuilder mail = new StringBuilder();
		mail.append("</body></html>");
		return mail.toString();
	}
	
	/**
	 * 替换成换行
	 * @param input
	 * @return
	 */
	public static String replacen(String input) {
		if (notEmpty(input)) {
			return input.replace("\n", "<br>");
		} else {
			return input;
		}
	}
	
	/**
	 * 自定义替换
	 * @param input
	 * @param in
	 * @param rep
	 * @return
	 */
	public static String replaces(String input, String in, String rep) {
		if (notEmpty(input)) {
			return input.replace(in, rep);
		} else {
			return input;
		}
	}

}
