package com.elcom.business.aggregate;

import com.elcom.data.UnitOfWork;
import com.elcom.data.entity.Rating;

public class CommonAggregate {

    private UnitOfWork _uok = null;

    public CommonAggregate(UnitOfWork uok) {
        this._uok = uok;
    }

    public boolean insertRating(Rating item) {
        return this._uok.commonRepository().insertRating(item);
    }
}
