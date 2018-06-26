package com.xmhx.buzz.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

/**
 * 日期操作工具类
 * @author 吴进 by 20150717
 *
 */
public class DateUtils {
	
	private static Logger logger = Logger.getLogger(DateUtils.class);

	/**
	 * 世标获得今天是一周的第几天，星期日是第一天，星期二是第二天......
	 * @return
	 */
	public static int getMondayEN() {
		Calendar cd = Calendar.getInstance();
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
		return dayOfWeek;
	}
	
	/**
	 * 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
	 * 但因为按中国礼拜一作为第一天，所以这里减1
	 * @return
	 */
	public static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
		// 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	/**
	 * 获得下周星期一开始的截至日期
	 * @return
	 */
	public static Date getNextSunday(int day) {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + (day - 1));
		Date monday = currentDate.getTime();
		return monday;
	}

	/**
	 * 获取本周一
	 * @return
	 */
	public static String getMondayOFWeek() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	/**
	 * 获取本周日
	 * @return
	 */
	public static String getCurrentWeekday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	/**
	 * 获得下周星期日的日期
	 * @return
	 */
	public static Date getNextSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
		Date monday = currentDate.getTime();
		return monday;
	}

	/**
	 * 获得下周星期一的日期
	 * @return
	 */
	public static Date getNextMonday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
		Date monday = currentDate.getTime();
		return monday;
	}

	/**
	 * 得到指定时间下几天的时间
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getNextSimple(String srcdate, int num) {
		Date date = getSimplaDate(srcdate);
		date.setDate(date.getDate() + num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	/**
	 * 得到本月第一天
	 * @return
	 */
	public static String getMonthFirstDay() {
		DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM");
		return format1.format(new Date()) + "-01";
	}

	/**
	 * 得到当前年份
	 * @return
	 */
	public static int getCurrentYear() {
		DateFormat format1 = new java.text.SimpleDateFormat("yyyy");
		return Integer.valueOf(format1.format(new Date()));
	}

	/**
	 * N年前
	 * 
	 * @param num
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getNextYearSimple(int num) {
		Date date = new Date();
		date.setYear(date.getYear() - num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 根据当前时间计算月份
	 * 
	 * @param type
	 *            1:过去 2：将来
	 * @param num
	 *            要处理的月份数
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getMonthSimple(int type, int num) {
		Date date = new Date();
		if (type == 1) {
			date.setMonth(date.getMonth() - num);
		} else if (type == 2) {
			date.setMonth(date.getMonth() + num);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	/**
	 * N周前
	 * 
	 * @param num
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getNextWeekSimple(int num) {
		Date date = new Date();
		date.setDate(date.getDay() + num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 得到前几天的时间
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getAgoSimple(int num) {
		Date date = new Date();
		date.setDate(date.getDate() - num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 得到下几天的时间
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getNextSimple(int num) {
		Date date = new Date();
		date.setDate(date.getDate() + num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	/**
	 * 给定毫秒数转成的分钟或秒
	 * @param timeGap
	 * @return
	 */
	public static String converTime(long timeGap) {
		// long timeGap = currentSeconds - timestamp;// 与现在时间相差秒数
		String timeStr = null;
		// if (timeGap > 24 * 60 * 60) {// 1天以上
		// timeStr = timeGap / (24 * 60 * 60) + "天";
		// } else if (timeGap > 60 * 60) {// 1小时-24小时
		// timeStr = timeGap / (60 * 60) + "小时";
		// } else if (timeGap > 60) {// 1分钟-59分钟
		if (timeGap > 60) {
			timeStr = timeGap / 60 + "分钟";
		} else {
			timeStr = timeGap + "秒";
		}
		// } else {// 1秒钟-59秒钟
		// timeStr = "刚刚";
		// }
		return timeStr;
	}

	/**
	 * 日期转换String  yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String formatSimpleDate(Date date) {
		if (date != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}
		return null;
	}
	
	/**
	 * 日期转换String yyyy-MM-dd HH:mm
	 * @param date
	 * @return
	 */
	public static String formatSimpleMin(Date date) {
		if (date != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return sdf.format(date);
		}
		return null;
	}
	
	/**
	 * 日期转换String  yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		if (date != null){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
		}
		return null;
	}
	
	/**
	 * Timestamp转换String  yyyy-MM-dd HH:mm
	 * @param paramTimestamp
	 * @return
	 */
	public static String formatTimestampMin(Timestamp paramTimestamp) {
		return formatSimpleMin(qlTimestampToDate(paramTimestamp));
	}

	/**
	 * String转换日期  yyyy-MM-dd HH:mm:ss
	 * @param srcdate
	 * @return
	 */
	public static Date getSimpleDayDate(String srcdate) {
		if(srcdate != null){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return sdf.parse(srcdate);
			} catch (ParseException e) {
				logger.error(e);
				return null;
			}
		}
		return null;
	}

	/**
	 * String转换日期  yyyy-MM-dd
	 * @param srcdate
	 * @return
	 */
	public static Date getSimplaDate(String srcdate) {
		if(srcdate != null){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.parse(srcdate);
			} catch (ParseException e) {
				logger.error(e);
				return null;
			}
		}
		return null;
	}

	/**
	 * 获取当前时间  yyyy-MM-dd
	 * @return
	 */
	public static String getDateNow() {
		DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
		return format1.format(new Date());
	}

	/**
	 * 获取当前时间  yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDatetimeNow() {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format1.format(new Date());
	}
	
	/**
	 * 获取当前时间  yyyy-MM-dd HH:mm:ss:SSS
	 * @return
	 */
	public static String getTimeMinNow() {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		return format1.format(new Date());
	}

	/**
	 * 将yyyy-MM-dd HH:mm:ss格式的时间转换为yyyyMMddHHmmss格式
	 * @param normaldate
	 * @return
	 */
	public static String timeToPlainString(String normaldate) {
		try {
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = format1.parse(normaldate);
			format1 = new SimpleDateFormat("yyyyMMddHHmmss");
			return format1.format(date);
		} catch (ParseException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将yyyyMMddHHmmss格式的时间转换为yyyy-MM-dd HH:mm:ss格式
	 * @param FINTIME
	 * @return
	 */
	public static String convertTime(String FINTIME) {
		try {
			DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = format1.parse(FINTIME);
			format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format1.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 得到时间HH:mm:ss时间显示类型
	 * @return HH:mm:ss时间显示类型
	 */
	public static String getTimes() {
		DateFormat format1 = new SimpleDateFormat("_yyyyMMddSSS");
		return format1.format(new Date());
	}
	
	/**
	 * 得到(yyyyMMdd)格式化时间
	 * @return
	 */
	public static String getFormatDate() {
		DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		return format1.format(new Date());
	}
	
	/**
	 * 得到(yyyyMMddhhmmsssss)格式化时间
	 * @return
	 */
	public static String getFormatDateTimes() {
		DateFormat format1 = new SimpleDateFormat("yyyyMMddhhmmsssss");
		return format1.format(new Date());
	}
	
	/**
	 * 得到(yyyyMMddhhmmss)格式化时间
	 * @return
	 */
	public static String getFormatDateTime() {
		DateFormat format1 = new SimpleDateFormat("yyyyMMddhhmmss");
		return format1.format(new Date());
	}
	
	/**
	 * <b>传入参数：yyyy-MM-dd HH:mm:ss 或者 yyyy-MM-dd</b>
	 * 得到(yyyyMMddhhmmss)格式化时间
	 * @return
	 */
	public static String getFormatDateTime(String srcTime) {
		SimpleDateFormat sdf = null;
		if(srcTime.length()>12){
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}else{
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}
		DateFormat format1 = new SimpleDateFormat("yyyyMMddhhmmss");
		try {
			return format1.format(sdf.parse(srcTime));
		} catch (ParseException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * <b>传入参数：yyyy-MM-dd HH:mm:ss 或者 yyyy-MM-dd</b>
	 * 得到(yyyyMMdd)格式化时间
	 * @return
	 */
	public static String getFormatDate(String srcTime) {
		SimpleDateFormat sdf = null;
		if(srcTime.length()>12){
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}else{
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}
		DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		try {
			return format1.format(sdf.parse(srcTime));
		} catch (ParseException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 判断原时间是否早于当前时间
	 * @param check_time  yyyy-MM-dd
	 * @return
	 * @throws Exception
	 */
	public static boolean CompareToCurrentTime(String check_time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long time = sdf.parse(check_time).getTime();
			long cur_time = System.currentTimeMillis();
			if (time <= cur_time) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 短时间比较
	 * @param start_time  yyyy-MM-dd
	 * @param end_time  yyyy-MM-dd
	 * @return 1:大于 0：等于 -1：小于
	 */
	public static int Compare(String start_time, String end_time) {
		int result = 1;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long startTime = sdf.parse(start_time).getTime();
			long endTime = sdf.parse(end_time).getTime();
			if (startTime > endTime) {
				result = 1;
			} else if (startTime == endTime) {
				result = 0;
			} else if (startTime < endTime) {
				result = -1;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	/**
	 * 给指定时间加上指定的天数
	 * @param starttime
	 *            起始时间 yyyy-MM-dd hh:mm:ss
	 * @param loanPeriod
	 *            要加的天数
	 * @return 起始时间增加指定天数以后的时间
	 */
	public static String timeAddDay(String starttime, int days) {
		DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return format1.format(addDays(format1.parse(starttime),
					days));
		} catch (NumberFormatException e) {
			logger.error(e);
			return null;
		} catch (ParseException e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * 给指定时间加上指定的天数
	 * @param starttime
	 *            起始时间 yyyy-MM-dd
	 * @param loanPeriod
	 *            要加的天数
	 * @return 起始时间增加指定天数以后的时间
	 */
	public static String timeAddDays(String starttime, int days) {
		DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
		try {
			return format1.format(addDays(format1.parse(starttime), days));
		} catch (NumberFormatException e) {
			logger.error(e);
			return null;
		} catch (ParseException e) {
			logger.error(e);
			return null;
		}
	}
	
	/**
	 * 给指定时间加上指定的天数
	 * @param addDate
	 * @return
	 */
	public static Date getDateByAddDate(int addDate) {
	    Date retVal = new Date(new Date().getTime() + 86400000 * addDate);
	    return retVal;
	}
	
	public static String getDateByAdd(int n) {
	    String time = "";
	    Calendar c = Calendar.getInstance();
	    c.add(5, n);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String mDateTime = formatter.format(c.getTime());
	    time = mDateTime.substring(0, 19);
	    return time;
	}
	
	@Deprecated
	public static Date addDays(Date targetDate, int days){
		targetDate.setDate(targetDate.getDate() + days);
		return targetDate;
	}
	
	@Deprecated
	public static Date addMonths(Date targetDate,int months){
		targetDate.setMonth(targetDate.getMonth()+months);
		return targetDate;
	}

	/**
	 * 给指定时间加上指定的月数
	 * @param tradingStartTime
	 *            起始时间 yyyy-MM-dd
	 * @param loanPeriod
	 *            要加的月数
	 * @return 起始时间增加指定月数以后的时间
	 */
	public static String timeAddMonthWithShort(String tradingStartTime,
			String loanPeriod) {
		DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
		try {
			return format1.format(DateUtils.addMonths(
					format1.parse(tradingStartTime),
					Integer.parseInt(loanPeriod)));
		} catch (NumberFormatException e) {
			logger.error(e);
			return null;
		} catch (ParseException e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * 给指定时间加上指定的月数
	 * @param tradingStartTime
	 *            起始时间 yyyy-MM-dd HH:mm:ss
	 * @param loanPeriod
	 *            要加的月数
	 * @return 起始时间增加指定月数以后的时间
	 */
	public static String timeAddMonth(String tradingStartTime, String loanPeriod) {
		DateFormat format1 = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			return format1.format(DateUtils.addMonths(
					format1.parse(tradingStartTime),
					Integer.parseInt(loanPeriod)));
		} catch (NumberFormatException e) {
			logger.error(e);
			return null;
		} catch (ParseException e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * 根据字符串时间得到时间戳
	 * @param start_time  yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static long getTimeMillis(String target_time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(target_time).getTime();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return -1L;
		}
	}
	
	/**
	 * 得到当前时间到指定时间的天数间隔
	 * @param target_time 指定时间  yyyy-MM-dd
	 * @return 间隔天数 int型值
	 */
	public static int getTimeInterval(String target_time) {
		target_time = target_time.split(" ")[0];
		Date temp_date = getSimplaDate(target_time);
		int days = (int) ((temp_date.getTime()-System.currentTimeMillis())/(24*60*60*1000));
		return days+1;//都不能整除，需要加1
	}
	
	/**
	 * 当前时间毫秒数
	 * @return
	 */
	public static long getSerial() {
		Date date = new Date();
		return date.getTime();
	}
	
	/**
	 * 得到开始时间到指定时间的天数间隔
	 * @param target_time 指定时间 yyyy-MM-dd
	 * @return 间隔天数 int型值
	 */
	public static Integer getTimeInterval(String start_time, String target_time) {
		target_time = target_time.split(" ")[0];
		Date start_date = getSimplaDate(start_time.split(" ")[0]);
		Date temp_date = getSimplaDate(target_time);
		int days = (int) ((temp_date.getTime()-start_date.getTime())/(24*60*60*1000));
		return days+1;//都不能整除，需要加1
	}

	/**
	 * 把util.Date转成sql.Date
	 * @param paramDate
	 * @return
	 */
	public static java.sql.Date dateToSqlDate(java.util.Date paramDate) {
		return new java.sql.Date(paramDate.getTime());
	}

	/**
	 * 把sql.Date转成util.Date
	 * @param paramDate
	 * @return
	 */
	public static java.util.Date sqlDateToDate(java.sql.Date paramDate) {
		return new java.util.Date(paramDate.getTime());
	}

	/**
	 * Date与Timestamp转换
	 * @param paramDate
	 * @return
	 */
	public static Timestamp dateToSqlTimestamp(java.util.Date paramDate) {
		return new Timestamp(paramDate.getTime());
	}

	/**
	 * Timestamp与Date转换
	 * @param paramTimestamp
	 * @return
	 */
	public static java.util.Date qlTimestampToDate(Timestamp paramTimestamp) {
		return new java.util.Date(paramTimestamp.getTime());
	}
	
	/**
	 * 
	 * @param days 天数(可以为负)
	 * @return 当前时间的前或后数天，格式 "yyyy-MM-dd"
	 */
	public static String getDate(int days){
		SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date nowdate = calendar.getTime();
		long nowtime = nowdate.getTime();
		long befortime = nowtime + days*1000*60*60*24l;
		calendar.setTimeInMillis(befortime);
		Date beforedate = calendar.getTime();
		return matter.format(beforedate);
	}
	
	public static void main(String[] args) {
		SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		//calendar.add(Calendar.MONTH, month);
		Date date = calendar.getTime();
		long endtime = date.getTime();
		long l = -90l;
		long starttime = endtime - l*1000*60*60*24;
		calendar.setTimeInMillis(starttime);
		Date date2 = calendar.getTime();
		System.out.println(starttime);
		System.out.println(endtime);
		System.out.println(matter.format(date2));
		
	}
}
