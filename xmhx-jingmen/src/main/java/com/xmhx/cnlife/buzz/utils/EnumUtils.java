package com.xmhx.cnlife.buzz.utils;

import java.util.Date;

/**
 * 系统枚举配置
 * @author 吴进 by 20150517
 *
 */
public class EnumUtils {
	
	/**
	 * 数据库操作类型
	 * @author 吴进
	 */
	public enum OptionType {
		/** 查询操作 **/
		QUERY(1, "查询操作"),
		/** 保存操作 **/
		PERSIST(2, "保存操作"),
		/** 更新操作 **/
		UPDATE(3, "更新操作"),
		/** 删除操作 **/
		DELETE(4, "删除操作"), 
		/** 查看详情  **/
		DETAILS(5, "查看详情操作");
		private int type;
		private String name;
		private OptionType(int type, String name) {
			this.type = type;
			this.name = name;
		}
		
		public static OptionType typeOf(int type){
			for(OptionType option : OptionType.values()){
				if(option.getType()==type){
					return option;
				}
			}
			return null;
		}
		
		public int getType() {
			return type;
		}
		public String getName() {
			return name;
		}
	}
	
	/**
	 * 数据类型
	 * @author 吴进
	 *
	 */
	public enum DataType {
		INT(int.class.getName()),
		INTEGER(Integer.class.getName()),
		STRING(String.class.getName()),
		SLONG(long.class.getName()),
		LONG(Long.class.getName()),
		SBOOLEAN(boolean.class.getName()),
		BOOLEAN(Boolean.class.getName()),
		SFLOAT(float.class.getName()),
		FLOAT(Float.class.getName()),
		SDOUBLE(double.class.getName()),
		DOUBLE(Double.class.getName()),
		DATE(Date.class.getName()),
		SBYTE(byte.class.getName()),
		BYTE(Byte.class.getName()),
		SSHORT(short.class.getName()),
		SHORT(Short.class.getName());
		
		private String type;
		
		private DataType(String str) {
			type = str;
		}
		
		public static DataType typeOf(String type){
			for (DataType c : DataType.values()) {   
				if (c.type.equals(type)) {   
					return c;   
				}
			}
			return null;
		}
		
		public String getType() {
			return type;
		}
	}
	
	/**
	 * 三方编号
	 * @author 吴进
	 */
	public enum ThreesidesNo {
		/** 物业 **/
		PROPERTYRIGHT("ffee98a5-ba89-11e6-9a0b-9ea20a09670a", "物业", "1"),
		/** 集采 **/
		PURCHASING("0b9c00d5-ba8a-11e6-9a0b-9ea20a09670a", "集采", "3"),
		/** 视频 **/
		VIDEO("13ae0e2e-ba8a-11e6-9a0b-9ea20a09670a", "视频", "2");
		
		private String code;
		private String name;
		private String firstLetter;//类型首字母
		private ThreesidesNo(String code, String name, String firstLetter) {
			this.code = code;
			this.name = name;
			this.firstLetter = firstLetter;
		}
		
		public static ThreesidesNo typeOf(String code) {
			for (ThreesidesNo option : ThreesidesNo.values()) {
				if (code.equals(option.getCode())) {
					return option;
				}
			}
			return null;
		}
		
		public String getCode() {
			return code;
		}
		public String getName() {
			return name;
		}
		public String getFirstLetter(){
			return firstLetter;
		}
	}
	
	/**
	 * 消息类型
	 * @author yyp
	 */
	public enum MessageType {
		ONEZEROONE("101", "物业费逾期提醒 "),
		ONEZEROTWO("102", "租赁合同即将到期提醒"),
		
		threezeroone("301", "买家下单提醒-已下单"),
		threezerotwo("302", "买家下单提醒-新订单"),
		threezerothree("303", "已接单提醒"),
		threezerofour("304", "已发货提醒"),
		threezerofive("305", "买家退单提醒"),
		threezerosix("306", "商家退单提醒");
		
		private String type;
		private String description;
		
		public static MessageType getDescription(String type){
			for (MessageType c : MessageType.values()) {   
				if (c.getType().equals(type)) {   
					return c;   
				}
			}
			return null;
		}
		private MessageType(String type, String description) {
			this.type = type;
			this.description = description;
		}
		public String getType() {
			return type;
		}
		public String getDescription() {
			return description;
		}
		
	}
	
	/**
	 * 推送提醒方式
	 * @author yyp
	 *
	 */
	public enum MessageJpushType {
		MESSAGEALL("10", "自定义消息-整个平台"),
		MESSAGEANDROID("11", "自定义-安卓平台"),
		MESSAGEIOS("12", "自定义-ios平台"),
		NOTIFYALL("20", "通知-整个平台"),
		NOTIRFYANDROID("21", "通知-安卓平台"),
		NOTIRFYIOS("22", "通知-ios平台");
		
		private String pushType;
		private String dsc;
		
		private MessageJpushType(String pushType, String dsc) {
			this.pushType = pushType;
			this.dsc = dsc;
		}

		public static MessageJpushType typeOf(String type){
			for (MessageJpushType c : MessageJpushType.values()) {   
				if (c.pushType.equals(type)) {   
					return c;   
				}
			}
			return null;
		}
		public String getPushType() {
			return pushType;
		}
		public String getDsc() {
			return dsc;
		}

	}
	
	/**
	 * 分页常量
	 * @author 吴进 by 20170110
	 *
	 */
	public enum ConstantPagelimit {
		newspagelimit("新闻分页常量", 4);
		
		private String title;
		private int number;
		
		private ConstantPagelimit(String title, int number) {
			this.title = title;
			this.number = number;
		}

		public static ConstantPagelimit typeOf(String title){
			for (ConstantPagelimit c : ConstantPagelimit.values()) {   
				if (c.getTitle().equals(title)) {   
					return c;   
				}
			}
			return null;
		}

		public String getTitle() {
			return title;
		}

		public int getNumber() {
			return number;
		}
	}
}
