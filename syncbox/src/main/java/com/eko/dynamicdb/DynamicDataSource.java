package com.eko.dynamicdb;

import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
	 
		return CustomerContextHolder.getCustomerType();
	}

	@Override
	public Logger getParentLogger() {
		// TODO Auto-generated method stub
		return super.getParentLogger();
	}

	 

}
