package com.xmhx.buzz.utils;

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
	public enum OptionType{
		/** 查询操作 **/
		QUERY(1,"查询操作"),
		/** 保存操作 **/
		PERSIST(2,"保存操作"),
		/** 更新操作 **/
		UPDATE(3,"更新操作"),
		/** 删除操作 **/
		DELETE(4,"删除操作"), 
		/** 查看详情  **/
		DETAILS(5,"查看详情操作");
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
	
}
