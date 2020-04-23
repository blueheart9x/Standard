package com.elcom.data;

import org.hibernate.HibernateException;

public class DataException extends HibernateException {

    /**
     *
     */
    private static final long serialVersionUID = -2314160790558575671L;

    public DataException() {
        super("Default DataException");
    }

    public DataException(HibernateException ex) {
        super(ex.getMessage());
    }

    public DataException(String msg) {
        super(msg);
    }
}
