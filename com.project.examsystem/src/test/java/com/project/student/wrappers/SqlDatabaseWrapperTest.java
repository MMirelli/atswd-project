package com.project.student.wrappers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SqlDatabaseWrapperTest extends AbstractSqlDBWrapperTest {

	@Override
	protected EntityManagerFactory createEntityManagerFactory() {
		EntityManagerFactory emf = Persistence.
				createEntityManagerFactory("examSystem-inMemory-PU");
		return emf;
	}
	
}
