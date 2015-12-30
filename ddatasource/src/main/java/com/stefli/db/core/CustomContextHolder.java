package com.stefli.db.core;

import org.springframework.util.Assert;

public class CustomContextHolder {

	private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<DataSourceType>();

	public static void setDataSourceType(DataSourceType dsType) {
		Assert.notNull(dsType, "dsType cannot be null");
		contextHolder.set(dsType);
	}

	public static DataSourceType getDataSourceType() {
		return (DataSourceType) contextHolder.get();
	}

	public static void clearDataSourceType() {
		contextHolder.remove();
	}

}