package com.stefli.db.core;

import java.lang.reflect.Field;
import java.util.Random;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.mysql.jdbc.Connection;

public class DataSourceAdvice implements MethodInterceptor,
		ApplicationContextAware {

	private ApplicationContext context;

	public Object invoke(MethodInvocation invocation) throws Throwable {
		final String methodName = invocation.getMethod().getName();
		final DataSourceType[] slaves = new DataSourceType[] {
				DataSourceType.SLAVE1, DataSourceType.SLAVE2 };
		final Random random = new Random();
		try {
			if (methodName.startsWith("find")) {
				CustomContextHolder
						.setDataSourceType(slaves[random.nextInt(2)]);
			} else {
				CustomContextHolder.setDataSourceType(DataSourceType.MASTER);
			}
			DataSourceTransactionManager dtm = (DataSourceTransactionManager) this.context
					.getBean("transactionManager");
			Connection conn = (Connection) dtm.getDataSource().getConnection();
			Field field = conn.getClass().getDeclaredField("myURL");
			field.setAccessible(true);
			System.out.println("Method[" + methodName
					+ "] --> " + field.get(conn));
			return invocation.proceed();
		} catch (IllegalArgumentException e) {
			throw e;
		} finally {
			CustomContextHolder.clearDataSourceType();
		}
	}

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

}
