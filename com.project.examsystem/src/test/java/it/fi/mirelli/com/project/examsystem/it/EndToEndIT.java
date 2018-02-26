package it.fi.mirelli.com.project.examsystem.it;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EndToEndIT extends AbstractStudentController {

	@Override
	protected EntityManagerFactory createEntityManagerFactory() {
		EntityManagerFactory emf = Persistence.
				createEntityManagerFactory("examSystem-remote-PU");
		return emf;
	}

}
