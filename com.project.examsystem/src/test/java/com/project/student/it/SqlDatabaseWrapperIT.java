package com.project.student.it;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.project.student.wrappers.AbstractSqlDBWrapperTest;

public class SqlDatabaseWrapperIT extends AbstractSqlDBWrapperTest{


	@Override
	protected EntityManagerFactory createEntityManagerFactory() {
		return Persistence.
				createEntityManagerFactory("examSystem-remote-PU");
	}	
}
