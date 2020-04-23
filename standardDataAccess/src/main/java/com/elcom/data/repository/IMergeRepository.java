package com.elcom.data.repository;


public interface IMergeRepository<T> extends IRepository<T> {

	void merge(T item);

}