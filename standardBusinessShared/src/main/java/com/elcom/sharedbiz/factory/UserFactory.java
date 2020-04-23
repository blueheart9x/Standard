package com.elcom.sharedbiz.factory;

import com.elcom.data.UnitOfWork;
import com.elcom.sharedbiz.aggregate.CommonAggregate;
import com.elcom.sharedbiz.aggregate.UserAggregate;

public final class UserFactory {

    private UserFactory() {
    }

    public static UserAggregate getUserAgg(UnitOfWork uok) {

        return new UserAggregate(uok);
    }

    public static CommonAggregate getCommonAggregate(UnitOfWork uok) {

        return new CommonAggregate(uok);
    }
}
