package it.fi.mirelli.studentproject.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import org.junit.Before;
import org.junit.Test;

import it.fi.mirelli.studentproject.common.StudentTestHelper;
import it.fi.mirelli.studentproject.controllers.StudentController;
import it.fi.mirelli.studentproject.models.Student;
import it.fi.mirelli.studentproject.wrappers.SqlStudentsDBWrapper;

public abstract class AbstractStudentController {

	private SqlStudentsDBWrapper db;

	protected abstract EntityManagerFactory createEntityManagerFactory();

	private EntityManager em;
	private StudentController studentController;
	private StudentTestHelper helper;

	public AbstractStudentController() {
		super();
	}

	@Before
	public void setUp() {
		EntityManagerFactory emf = createEntityManagerFactory();
		
		em = emf.createEntityManager();
		db = new SqlStudentsDBWrapper(em);
		studentController = new StudentController(db);	
		helper = new StudentTestHelper(em);
	}

	@Test
	public void getAllStudentsWhenNoOne() {
		List<Student> students = studentController.getAllStudents();
		helper.assertStudentNumber(0, students);
	}

	@Test
	public void getAllWhenOne() {
		helper.persistNewStudent(1, "stud1@mail.com");
		List<Student> students = studentController.getAllStudents();
		helper.assertStudentNumber(1, students);
	}

	@Test
	public void getAllWhenMany() {
		helper.persistNewStudent(1, "stud1@mail.com");
		helper.persistNewStudent(2, "stud2@mail.com");
		List<Student> students = studentController.getAllStudents();
		helper.assertStudentNumber(2, students);
	}

	@Test
	public void findByIdSuccessful() {
		int searchedStudentId = 1;
		String searchedStudentEmail = "stud1@mail.com";
		helper.persistNewStudent(searchedStudentId, searchedStudentEmail);
		Student student = studentController.findStudentById(searchedStudentId);
		
		assertEquals(searchedStudentId, student.getId());
		assertEquals(searchedStudentEmail, student.getEmail());
	}

	@Test(expected = NoResultException.class)
	public void findByIdUnsuccessful() {
		
		helper.persistNewStudent(1, "stud1@mail.com");
	
		studentController.findStudentById(2);
	}

	@Test
	public void addStudent() {
		int addedStudentId = 1;
		String addedStudentEmail = "stud1@mail.com";
		studentController.
			addStudent(
			helper.createNewStudent(
					addedStudentId,
					addedStudentEmail));
		
		Student addedStudent = em.find(Student.class, addedStudentId);
		assertNotNull(addedStudent);
		assertEquals(addedStudentEmail, addedStudent.getEmail());
	}

	@Test
	public void getEmail() {
		int addedStudentId = 1;
		String addedStudentEmail = "stud1@mail.com";
		helper.persistNewStudent(addedStudentId, addedStudentEmail);
		
		String justFetchedEmail = 
				studentController.getStudentEmailById(1);
		
		assertEquals(addedStudentEmail, justFetchedEmail);
	}

}