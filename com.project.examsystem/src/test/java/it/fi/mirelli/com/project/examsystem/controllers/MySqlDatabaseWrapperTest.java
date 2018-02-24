package it.fi.mirelli.com.project.examsystem.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import it.fi.mirelli.com.project.examsystem.models.Student;

public class MySqlDatabaseWrapperTest {

	private MySqlDatabaseWrapper sqlDbWrapper;
	private EntityManager em;

	@Before
	public void setUp() {
		String persistenceUnit = "examSystem-test-PU";
		// student.sql script creates an empty
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		em = emf.createEntityManager();
		sqlDbWrapper = new MySqlDatabaseWrapper(em);
	}

	@Test
	public void getAllWhenNoStudents() {
		System.out.println(sqlDbWrapper.getAll());
		assertNumberOfStudents(0, sqlDbWrapper);
	}

	@Test
	public void getAllWhenManyStudents() {
		beginTransaction();

		createAndPersistStudent(1, "stud1@mail.com");
		createAndPersistStudent(2, "stud2@mail.com");

		commitTransaction();

		assertNumberOfStudents(2, sqlDbWrapper);
	}

	private void commitTransaction() {
		em.getTransaction().commit();
	}

	private void beginTransaction() {
		em.getTransaction().begin();
	}

	@Test
	public void successfulFindStudentById() {
		beginTransaction();

		createAndPersistStudent(1, "stud1@mail.com");

		commitTransaction();

		assertEquals(1, sqlDbWrapper.findById(1).getId());
	}

	@Test(expected = NoResultException.class)
	public void unsuccessfulFindStudentById() {
		beginTransaction();

		createAndPersistStudent(1, "stud1@mail.com");

		commitTransaction();

		sqlDbWrapper.findById(2);
	}
	
	@Test
	public void successfulAddStudent() {
		sqlDbWrapper.add(createNewStudent(1, "stud1@mail.com"));
		
		Student foundStudent = em.find(Student.class, 1);
		assertNotNull(foundStudent);
	}

	@Test(expected=EntityExistsException.class)
	public void unsuccessfulAddStudent() {
		beginTransaction();
		createAndPersistStudent(1, "stud@mail.com");
		commitTransaction();
	
		sqlDbWrapper.add(createNewStudent(1, "stud1@mail.com"));	
		
	}
	
	private Student createAndPersistStudent(int id, String email) {
		Student student = createNewStudent(id, email);
		em.persist(student);
		return student;
	}

	private Student createNewStudent(int id, String email) {
		Student student = new Student();
		student.setId(id);
		student.setEmail(email);
		return student;
	}

	private void assertNumberOfStudents(int expected, MySqlDatabaseWrapper sqlDbWrapper) {
		assertEquals(expected, sqlDbWrapper.getAll().size());
	}

}
