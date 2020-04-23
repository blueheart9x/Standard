package com.elcom.data.factory;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public final class HibernateSqlServerFactory {
	
	public static SessionFactory sqlServerfactory;

	// to disallow creating objects by other classes.
	private HibernateSqlServerFactory() {
	}

	// maling the Hibernate SessionFactory object as singleton
	//public static synchronized SessionFactory getInstance() { // TODO Mở lại nếu chạy gặp vấn đề
	public static SessionFactory getInstance() {
		
		synchronized (HibernateSqlServerFactory.class) {
			
			if (sqlServerfactory == null) {
				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.sql-server.cfg.xml").build();
				System.out.println("HibernateSqlServerFactory.ServiceRegistry created successfully");

				SessionFactory sessFactory = new Configuration().buildSessionFactory(serviceRegistry);
				System.out.println("HibernateSqlServerFactory.SessionFactory created successfully");
				
				sqlServerfactory = sessFactory;
			}
			
			return sqlServerfactory;
		}
	}
}
