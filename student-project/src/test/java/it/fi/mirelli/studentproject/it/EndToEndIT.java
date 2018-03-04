package it.fi.mirelli.studentproject.it;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EndToEndIT extends AbstractStudentController {

	@Override
	protected EntityManagerFactory createEntityManagerFactory() {
		EntityManagerFactory emf = Persistence.
				createEntityManagerFactory("studentSystem-remote-PU");
		return emf;
	}

}
