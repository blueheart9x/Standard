package com.elcom.data;

public interface IBaseUnitOfWork {

    void start();

    void commit();

    void rollback();

    void close();
}
