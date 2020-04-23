package com.elcom.data.repository;


public interface IUpsertRepository<T> extends IRepository<T> {

	void upsert(T item);

}