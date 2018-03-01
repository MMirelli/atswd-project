package it.fi.mirelli.student_project.wrappers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SqlDatabaseWrapperTest extends AbstractSqlDBWrapperTest {

	@Override
	protected EntityManagerFactory createEntityManagerFactory() {
		EntityManagerFactory emf = Persistence.
				createEntityManagerFactory("studentSystem-inMemory-PU");
		return emf;
	}
	
}
