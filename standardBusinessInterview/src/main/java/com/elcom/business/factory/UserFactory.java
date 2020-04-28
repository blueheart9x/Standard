package com.elcom.business.factory;

import com.elcom.data.UnitOfWork;
import com.elcom.business.aggregate.CommonAggregate;
import com.elcom.business.aggregate.UserAggregate;

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
