/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.business.factory;

import com.elcom.business.aggregate.CommonAggregate;
import com.elcom.business.aggregate.UserAggregate;
import com.elcom.business.validation.UserValidation;
import com.elcom.data.UnitOfWork;

/**
 *
 * @author Admin
 */
public class StandardFactory {

    public static UserValidation getUserValidation() {
        return new UserValidation();
    }

    public static CommonAggregate getCommonAggregateInstance(UnitOfWork uok) {
        return new CommonAggregate(uok);
    }
    
    public static UserAggregate getUserAggregateInstance(UnitOfWork uok) {
        return new UserAggregate(uok);
    }
}
