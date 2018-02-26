package it.fi.mirelli.com.project.examsystem.it;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import it.fi.mirelli.com.project.examsystem.wrappers.AbstractSqlDBWrapperTest;

public class SqlDatabaseWrapperIT extends AbstractSqlDBWrapperTest{


	@Override
	protected EntityManagerFactory createEntityManagerFactory() {
		return Persistence.
				createEntityManagerFactory("examSystem-remote-PU");
	}	
}
