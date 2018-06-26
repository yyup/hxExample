package com.xmhx.cnlife.core.authority;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.xmhx.cnlife.buzz.utils.EnumUtils.OptionType;

/**
 * 用户菜单权限控制
 * @author 吴进 by 20150717
 */
@Deprecated
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MenurightAuth {
	String value() default "";
	/** 
	 * 菜单Code
	 */
	String menuCode();
	/**
	 * 数据操作类型 1：查询 2：保存  3：更新 4：删除
	 * @return
	 */
	OptionType option() default OptionType.QUERY;
}
