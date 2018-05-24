package com.eko.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class SpringUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		SpringUtil.applicationContext=arg0;

	}
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
		
	}
	@SuppressWarnings("unchecked")
	public static<T> T getBean(String beanName) throws BeansException{
		return (T)applicationContext.getBean(beanName);
	}
	
	public static boolean containBean(String beanname) throws BeansException{
		return applicationContext.containsBean(beanname);
	}
}
