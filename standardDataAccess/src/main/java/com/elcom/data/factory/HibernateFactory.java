package com.elcom.data.factory;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public final class HibernateFactory {
	
	public static SessionFactory factory;

	// to disallow creating objects by other classes.
	private HibernateFactory() {
	}

	// maling the Hibernate SessionFactory object as singleton
	//public static synchronized SessionFactory getInstance() { // TODO Mở lại nếu chạy gặp vấn đề
	public static SessionFactory getInstance() {
		
		synchronized (HibernateFactory.class) {
			
			if (factory == null) {
				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
				System.out.println("HibernateFactory.ServiceRegistry created successfully");

				SessionFactory sessFactory = new Configuration().buildSessionFactory(serviceRegistry);
				System.out.println("HibernateFactory.SessionFactory created successfully");
				
				factory = sessFactory;
			}
			
			return factory;
		}
	}
}
