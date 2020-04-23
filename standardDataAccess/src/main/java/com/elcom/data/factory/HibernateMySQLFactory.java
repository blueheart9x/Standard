package com.elcom.data.factory;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public final class HibernateMySQLFactory {
	
	public static SessionFactory mySqlfactory;

	// to disallow creating objects by other classes.
	private HibernateMySQLFactory() {
	}

	// maling the Hibernate SessionFactory object as singleton
	//public static synchronized SessionFactory getInstance() { // TODO Mở lại nếu chạy gặp vấn đề
	public static SessionFactory getInstance() {
		
		synchronized (HibernateMySQLFactory.class) {
			
			if (mySqlfactory == null) {
				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
				System.out.println("HibernateMySQLFactory.ServiceRegistry created successfully");

				SessionFactory sessFactory = new Configuration().buildSessionFactory(serviceRegistry);
				System.out.println("HibernateMySQLFactory.SessionFactory created successfully");
				
				mySqlfactory = sessFactory;
			}
			
			return mySqlfactory;
		}
	}
}
