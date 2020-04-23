package com.elcom.data;

import org.hibernate.Session;

public abstract class BaseUnitOfWork implements IBaseUnitOfWork {

    protected HibernateBase hibernateBase;

    protected Session session;

    public BaseUnitOfWork() {
        if (this.hibernateBase == null) {
            hibernateBase = new HibernateBase();
        }

        if (this.session == null) {
            this.session = hibernateBase.getSession();
        }
    }

    public BaseUnitOfWork(HibernateBase hibernateBase, Session session) {
        this.hibernateBase = hibernateBase;
        this.session = session;
    }

    public void start() {
        if (!this.hibernateBase.isTransactionActive()) {
            this.hibernateBase.startTransaction();
        }
    }

    public void flush() {
        this.hibernateBase.flush();
    }

    public boolean isActive() {
        return this.hibernateBase.isTransactionActive();
    }

    public void commit() {
        this.hibernateBase.endTransaction();
    }

    public void rollback() {
        this.hibernateBase.rollbackTransaction();
    }

    public void close() {
        this.hibernateBase.closeTransaction();
    }

    public void clearCache() {
        this.session.clear();
    }

    public void reset() {
        this.hibernateBase = null;
        this.session = null;
        hibernateBase = new HibernateBase();
        this.session = hibernateBase.getSession();
    }

}
