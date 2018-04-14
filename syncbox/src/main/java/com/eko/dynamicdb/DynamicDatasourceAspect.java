package com.eko.dynamicdb;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DynamicDatasourceAspect {
	@Before("@annotation(SwitchDatasource)")
	public void beforeSwitchDatasourcec(JoinPoint joinPoint){
		Class<?> clazz = joinPoint.getTarget().getClass();
		String methodName = joinPoint.getSignature().getName();
		Class[] methodParams = ((MethodSignature)joinPoint.getSignature()).getParameterTypes();
		String datasource = CustomerContextHolder.DATA_SOURCE_FROMDBDATASOURCE;
		try{
			Method method = clazz.getMethod(methodName, methodParams);
			if(method.isAnnotationPresent(SwitchDatasource.class)){
				SwitchDatasource switchDatasource = method.getAnnotation(SwitchDatasource.class);
				datasource = switchDatasource.value();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		CustomerContextHolder.setCustomerType(datasource);
	}
	@After("@annotation(SwitchDatasource)")
	public void afterSwitchDatasource(JoinPoint joinPoint){
		CustomerContextHolder.clearCustomerType();
	}
}
