package com.elcom.data.user;

import org.hibernate.Session;

import com.elcom.data.BaseUnitOfWork;
import com.elcom.data.HibernateBase;
import com.elcom.data.repository.IRepository;
import com.elcom.data.user.repository.UsersRepository;

public class UnitOfWorkUser extends BaseUnitOfWork {

	@SuppressWarnings("rawtypes")
	private IRepository _usersRepository = null;

	public UnitOfWorkUser(HibernateBase hibernateBase,Session session) {
		super(hibernateBase,session);
	}
	
	public UnitOfWorkUser() {
		super();
	}
	
	public UsersRepository usersRepository() {

		if (_usersRepository == null)
			_usersRepository = new UsersRepository(this.session);

		return (UsersRepository) _usersRepository;
	}
}
