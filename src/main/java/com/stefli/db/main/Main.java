package com.stefli.db.main;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.stefli.db.service.IPersonService;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String args[]) throws SQLException,
			NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		IPersonService service = (IPersonService) ctx.getBean("personService");
		int len = 10;
		for (int i = 0; i < len; i++) {
			System.out.println(service.findAll());
			String name = "stefli-" + i;
			service.createPerson(name);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(service.findAll());
	}

}
