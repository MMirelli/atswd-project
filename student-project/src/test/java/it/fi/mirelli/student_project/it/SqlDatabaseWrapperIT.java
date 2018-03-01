package it.fi.mirelli.student_project.it;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import it.fi.mirelli.student_project.wrappers.AbstractSqlDBWrapperTest;

public class SqlDatabaseWrapperIT extends AbstractSqlDBWrapperTest{


	@Override
	protected EntityManagerFactory createEntityManagerFactory() {
		return Persistence.
				createEntityManagerFactory("studentSystem-remote-PU");
	}	
}
