package com.elcom.data;

import org.hibernate.Session;

import com.elcom.data.interview.UnitOfWorkInterview;
import com.elcom.data.user.UnitOfWorkUser;

public class UnitOfWork extends BaseUnitOfWork {

    public UnitOfWorkUser user;
    public UnitOfWorkInterview vi;

    public UnitOfWork() {
        super();
        this.init(this.hibernateBase, this.session);
    }

    public UnitOfWork(HibernateBase hibernateBase, Session session) {
        super(hibernateBase, session);
        this.init(hibernateBase, session);
    }

    private void init(HibernateBase hibernateBase, Session session) {
        this.user = new UnitOfWorkUser(hibernateBase, session);
        this.vi = new UnitOfWorkInterview(hibernateBase, session);
    }
}
