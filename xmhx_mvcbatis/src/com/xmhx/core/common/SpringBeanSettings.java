package com.xmhx.core.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * 得到Spring Bean实例工具类
 * 
 * @author 吴进 by 2015-05-09
 * 
 */
public class SpringBeanSettings implements BeanFactoryAware {

	private static BeanFactory beanFactory = null;
	private static SpringBeanSettings factoryUtils = null;

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		SpringBeanSettings.beanFactory = beanFactory;
	}

	public static BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public static SpringBeanSettings getInstance() {
		if (factoryUtils == null) {
			factoryUtils = new SpringBeanSettings();
		}
		return factoryUtils;
	}

	/**
	 * 得到Spring管理Bean的 实例
	 * 
	 * @param resouceName
	 * @return
	 */
	public static Object getBean(String name) {
		return beanFactory.getBean(name);
	}
}
