package it.fi.mirelli.com.project.examsystem.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import it.fi.mirelli.com.project.examsystem.controllers.StudentController;
import it.fi.mirelli.com.project.examsystem.dbs.StudentDatabase;
import it.fi.mirelli.com.project.examsystem.models.Student;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

	@Mock
	private StudentDatabase db;
	
	private List<Student> students;
	private StudentController studentController;

	@Before
	public void setUp() throws Exception {
		studentController = new StudentController(db);
		students = new ArrayList<Student>();
		when(db.getAll()).thenReturn(students);
	}

	@Test
	public void getAllStudentWhenNoOne() {
		List<Student> allStudents = studentController.getAllStudents();

		verify(db, times(1)).getAll();
		assertStudentNumber(allStudents, 0);
	}

	private void assertStudentNumber(List<Student> allStudents, int expected) {
		assertEquals(allStudents.size(), expected);
	}
	
	@Test
	public void getAllWhenOne() {
		students.add(createNewStudent(1, "stud1@mail.com"));
		List<Student> allStudents = studentController.getAllStudents();
		
		verify(db, times(1)).getAll();
		assertStudentNumber(allStudents, 1);
	}

	@Test
	public void getAllWhenMany() {
		students.add(createNewStudent(1, "stud1@mail.com"));
		students.add(createNewStudent(2, "stud2@mail.com"));
		List<Student> allStudents = studentController.getAllStudents();
		
		verify(db, times(1)).getAll();
		assertStudentNumber(allStudents, 2);
	}
	
	@Test 
	public void findByIdUnsuccessful() {
		int id = 1;
		students.add(createNewStudent(id, "stud1@mail.com"));
		Student fetchedStudent = studentController.findStudentById(2);
		
		verify(db, times(id)).findById(2);
		assertNull(fetchedStudent);
	}
	
	@Test 
	public void findByIdSuccessful() {
		students.add(createNewStudent(1, "stud1@mail.com"));
		when(db.findById(1)).thenReturn(createNewStudent(1, "stud1@mail.com"));
		Student fetchedStudent = studentController.findStudentById(1);
		
		ArgumentCaptor<Integer> idCaptor =
				ArgumentCaptor.forClass(Integer.class);
		
		verify(db, times(1)).findById(idCaptor.capture());
		
		int expected = 1;
		assertEquals(expected, idCaptor.getAllValues().get(0).intValue());
		assertNotNull(fetchedStudent);
	}

	private Student createNewStudent(int id, String email) {
		return new Student(id, email);
	}

	@Test
	public void addStudent() {
		ArgumentCaptor<Student> studentCaptor =
				ArgumentCaptor.forClass(Student.class);
		
		studentController.addStudent(createNewStudent(1, "stud1@mail.com"));
		
		verify(db, times(1)).add(studentCaptor.capture());
		
		int expectedId = 1;
		assertEquals(expectedId, studentCaptor.getAllValues().get(0).getId());

		String expectedEmail = "stud1@mail.com";
		assertEquals(expectedEmail, studentCaptor.getAllValues().get(0).getEmail());
	}
	
	@Test 
	public void getEmail() {
		ArgumentCaptor<Integer> idCaptor =
				ArgumentCaptor.forClass(Integer.class);
		
		when(db.getEmail(1)).thenReturn("stud1@mail.com");
		String fetchedEmail = studentController.getStudentEmail(1);
		verify(db, times(1)).getEmail(idCaptor.capture());
		
		int expectedId = 1;
		assertEquals(expectedId, idCaptor.getAllValues().get(0).intValue());
		
		String expectedEmail = "stud1@mail.com";
		assertEquals(expectedEmail, fetchedEmail);
	}
}
