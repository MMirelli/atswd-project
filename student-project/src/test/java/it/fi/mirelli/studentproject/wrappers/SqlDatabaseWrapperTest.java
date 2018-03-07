package it.fi.mirelli.studentproject.wrappers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SqlDatabaseWrapperTest extends AbstractStudentDBWrapperTest {

	@Override
	protected EntityManagerFactory createEntityManagerFactory() {
		return Persistence.
				createEntityManagerFactory
					("studentSystem-inMemory-PU");
	}
	
}
