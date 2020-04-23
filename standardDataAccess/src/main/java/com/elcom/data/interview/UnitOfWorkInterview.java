package com.elcom.data.interview;

import org.hibernate.Session;

import com.elcom.data.BaseUnitOfWork;
import com.elcom.data.HibernateBase;
import com.elcom.data.interview.repository.CommonRepository;
import com.elcom.data.repository.IRepository;
import com.elcom.data.user.UnitOfWorkUser;

public class UnitOfWorkInterview extends BaseUnitOfWork {

    public UnitOfWorkUser user;

    public UnitOfWorkInterview(HibernateBase hibernateBase, Session session) {
        super(hibernateBase, session);
        this.init(hibernateBase, session);
    }

    public UnitOfWorkInterview() {
        super();
        this.init(hibernateBase, session);
    }

    private void init(HibernateBase hibernateBase, Session session) {
        this.user = new UnitOfWorkUser(hibernateBase, session);
    }

    @SuppressWarnings("rawtypes")
    private IRepository _commonRepository = null;

    public CommonRepository commonRepository() {
        if (_commonRepository == null) {
            _commonRepository = new CommonRepository(this.session);
        }
        return (CommonRepository) _commonRepository;
    }

    @Override
    public void reset() {
        super.reset();
        _commonRepository = null;
        _commonRepository = new CommonRepository(this.session);
    }
}
