package it.fi.mirelli.com.project.examsystem.it;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StudentControllerIT extends AbstractStudentController {

	@Override
	protected EntityManagerFactory createEntityManagerFactory() {
		EntityManagerFactory emf = Persistence.
				createEntityManagerFactory("examSystem-inMemory-PU");
		return emf;
	}
	
}
