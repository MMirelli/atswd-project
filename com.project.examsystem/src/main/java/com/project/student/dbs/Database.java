package com.project.student.dbs;

import java.util.List;

public interface Database<T, I> {

	public List<T> getAll();

	public T findById(I id);

	public void add(T element);

	
}
