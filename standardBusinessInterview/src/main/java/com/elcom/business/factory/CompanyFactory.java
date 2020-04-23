package com.elcom.business.factory;

import com.elcom.business.aggregate.CompanyAggregate;
import com.elcom.data.interview.UnitOfWorkInterview;

public final class CompanyFactory {

	private CompanyFactory() {
	}
	
	public static CompanyAggregate getCompanyAggregateInstance(UnitOfWorkInterview uok) {
		return new CompanyAggregate(uok);
	}
}
