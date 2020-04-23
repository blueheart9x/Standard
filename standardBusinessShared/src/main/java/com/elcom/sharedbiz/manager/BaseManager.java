package com.elcom.sharedbiz.manager;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Callable;

import com.elcom.common.UnitOfWorkHelper;
import com.elcom.data.UnitOfWork;
import com.elcom.data.factory.UnitOfWorkFactory;
import com.elcom.model.enums.SessionType;

public class BaseManager implements Closeable {

    protected UnitOfWork uok = UnitOfWorkFactory.getInstance(SessionType.JTA);

    protected <T> T tryCatch(Callable<T> func) throws Exception {
        return UnitOfWorkHelper.tryCatch(uok, func);
    }

    public UnitOfWork getUok() {
        return uok;
    }

    public void setUok(UnitOfWork uok) {
        this.uok = uok;
    }

    @Override
    public void close() throws IOException {
        this.uok = null;
    }
}
