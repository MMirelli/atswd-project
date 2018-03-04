package it.fi.mirelli.studentproject.it;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import it.fi.mirelli.studentproject.wrappers.AbstractSqlDBWrapperTest;

public class SqlDatabaseWrapperIT extends AbstractSqlDBWrapperTest{


	@Override
	protected EntityManagerFactory createEntityManagerFactory() {
		return Persistence.
				createEntityManagerFactory
					("studentSystem-remote-PU");
	}	
}
