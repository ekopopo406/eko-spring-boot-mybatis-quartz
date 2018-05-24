package com.eko.utils;

import java.lang.reflect.Method;

import com.eko.task.domain.TaskDomain;

public class TaskUtil {
	
	@SuppressWarnings("unchecked")
	public static void invokeTaskMethod(TaskDomain taskDomain){
		Object object = null;
		Class<TaskDomain> clazz = null;
		try{
//			System.out.println(SpringUtil.getApplicationContext().getBeanDefinitionCount());
//			for(String temp:SpringUtil.getApplicationContext().getBeanDefinitionNames()){
//			System.out.println(temp);
//			}
//		 
//			System.out.println("SpringUtil"+SpringUtil.containBean("scheduler"));
			if(SpringUtil.containBean(taskDomain.getSpringBeanName())){
				object = SpringUtil.getBean(taskDomain.getSpringBeanName());
				clazz = (Class<TaskDomain>) object.getClass();
				
			}else{
				clazz = (Class<TaskDomain>) Class.forName(taskDomain.getClassAbsName());
				object = clazz.newInstance();
			}
			 Method method = clazz.getDeclaredMethod(taskDomain.getMethodName());
			 method.invoke(object);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
}
