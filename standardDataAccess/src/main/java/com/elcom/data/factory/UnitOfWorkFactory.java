package com.elcom.data.factory;

import org.hibernate.Session;

import com.elcom.data.HibernateBase;
import com.elcom.data.UnitOfWork;
import com.elcom.model.enums.SessionType;

public final class UnitOfWorkFactory {
	
	private UnitOfWorkFactory() {
	}
	
	public static UnitOfWork getInstance(SessionType type) {
		
		UnitOfWork uok = null;
		
		if(type == SessionType.THREAD)
			uok = new UnitOfWork();//init Thread nhibernate session by using call getCurrentSession
		else {
			HibernateBase hibernateBase = new HibernateBase(type);//init nhibernate session depending on SessionType
			Session session = hibernateBase.getSession();
			uok = new UnitOfWork(hibernateBase,session);
		}
		
		return uok;
	}
}
