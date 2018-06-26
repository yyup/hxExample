package com.xmhx.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.xmhx.buzz.utils.EnumUtils.DataType;

/**
 * 标明列
 * @author wujin
 * @since 2015-07-01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
	public String columnName();
	public DataType dataType() default DataType.STRING;
}
