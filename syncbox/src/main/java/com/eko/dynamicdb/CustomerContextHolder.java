package com.eko.dynamicdb;

public class CustomerContextHolder {
	
	public static final String DATA_SOURCE_FROMDBDATASOURCE = "fromdbDatasource";

	public static final String DATA_SOURCE_TARGET_DB = "targetdbDatasource";

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setCustomerType(String customerType) {
		contextHolder.set(customerType);
	}

	public static String getCustomerType() {
		return contextHolder.get();
	}

	public static void clearCustomerType() {
		contextHolder.remove();
	}
}
