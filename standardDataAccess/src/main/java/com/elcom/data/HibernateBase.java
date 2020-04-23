package com.elcom.data;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elcom.data.factory.HibernateFactory;
import com.elcom.model.enums.SessionType;

public class HibernateBase {

    private Transaction transaction;
    private Session session;
    private SessionType type = SessionType.THREAD;

    public HibernateBase() {
        this.session = HibernateFactory.getInstance().getCurrentSession();
    }

    public HibernateBase(SessionType type) {
        this.type = type;
        if (type == SessionType.THREAD) {
            this.session = HibernateFactory.getInstance().getCurrentSession();
        } else {
            this.session = HibernateFactory.getInstance().openSession();
        }
    }

    public Session getSession() {
        return this.session;
    }

    public void startTransaction() {
        this.transaction = this.session.beginTransaction();
    }

    public void closeTransaction() {
        //just close connection when type is JTA. if Session type is Thread, it will automatically close
        //if(this.session.isOpen() && type != SessionType.THREAD)
        if (this.type != SessionType.THREAD) {
            try {
                this.session.close();
            } catch (Throwable th) {
            }
        }
    }

    public boolean isTransactionActive() {
        return this.session.getTransaction().isActive();
    }

    public void endTransaction() {
        if (this.transaction != null) {
            try {
                this.transaction.commit();
            } catch (Exception e) {
                this.transaction.rollback();
                throw e;
            }
        }
    }

    public void rollbackTransaction() {

        if (this.transaction != null) {
            this.transaction.rollback();
        }
    }

    public final void evict(Object entity) {
        this.session.evict(entity);
    }

    public final void clear() {
        this.session.clear();
    }

    public final void flush() {
        this.session.flush();
    }
}
