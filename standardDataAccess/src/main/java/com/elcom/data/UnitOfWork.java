package com.elcom.data;

import org.hibernate.Session;

import com.elcom.data.repository.standard.CommonRepository;
import com.elcom.data.repository.IRepository;
import com.elcom.data.repository.standard.UsersRepository;

public class UnitOfWork extends BaseUnitOfWork {

    public UnitOfWork() {
        super();
    }

    public UnitOfWork(HibernateBase hibernateBase, Session session) {
        super(hibernateBase, session);
    }

    @SuppressWarnings("rawtypes")
    private IRepository _commonRepository = null;
    
    @SuppressWarnings("rawtypes")
    private IRepository _usersRepository = null;

    public CommonRepository commonRepository() {
        if (_commonRepository == null) {
            _commonRepository = new CommonRepository(this.session);
        }
        return (CommonRepository) _commonRepository;
    }
    
    public UsersRepository usersRepository() {
        if (_usersRepository == null) {
            _usersRepository = new UsersRepository(this.session);
        }
        return (UsersRepository) _usersRepository;
    }

    @Override
    public void reset() {
        super.reset();
        _commonRepository = null;
        _commonRepository = new CommonRepository(this.session);
        
        _usersRepository = null;
        _usersRepository = new UsersRepository(this.session);
    }
}
