package com.elcom.data.repository;

public interface IGetRepository<T> extends IRepository<T> {
	T get(Long id);
}
