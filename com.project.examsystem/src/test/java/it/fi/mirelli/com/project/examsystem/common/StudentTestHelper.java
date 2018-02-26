package it.fi.mirelli.com.project.examsystem.common;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import it.fi.mirelli.com.project.examsystem.models.Student;

public class StudentTestHelper {

	private EntityManager em;
	
	public StudentTestHelper() {
		super();
	}

	public StudentTestHelper(EntityManager em) {
		super();
		this.em = em;
	}

	public void assertStudentNumber(int expected, List<Student> allStudents) {
		assertEquals(allStudents.size(), expected);
	}
	
	public Student createNewStudent(int id, String email) {
		Student student = new Student();
		student.setId(id);
		student.setEmail(email);
		return student;
	}
	
	public void persistNewStudent(int id, String email) {
		em.getTransaction().begin();
		em.persist(createNewStudent(id, email));
		em.getTransaction().commit();
	}

}
