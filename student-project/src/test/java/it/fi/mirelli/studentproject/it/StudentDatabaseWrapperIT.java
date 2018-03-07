package it.fi.mirelli.studentproject.it;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import it.fi.mirelli.studentproject.wrappers.AbstractStudentDBWrapperTest;

public class StudentDatabaseWrapperIT extends AbstractStudentDBWrapperTest{


	@Override
	protected EntityManagerFactory createEntityManagerFactory() {
		return Persistence.
				createEntityManagerFactory
					("studentSystem-remote-PU");
	}	
}
