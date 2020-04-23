package com.elcom.common;

import java.util.concurrent.Callable;

import com.elcom.data.UnitOfWork;

public final class UnitOfWorkHelper {

	private UnitOfWorkHelper(){
	}
	
	public static <T> T tryCatch(UnitOfWork uok, Callable<T> func) throws Exception {

		try {
			uok.start();
			T result = func.call();
			uok.commit();
			return result;
		} catch (Throwable e) {
			uok.rollback();
			throw e;
		} finally {
			uok.close();
		}
	}

}