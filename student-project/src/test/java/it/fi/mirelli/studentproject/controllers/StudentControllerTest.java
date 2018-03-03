package it.fi.mirelli.studentproject.controllers;

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

import it.fi.mirelli.studentproject.common.StudentTestHelper;
import it.fi.mirelli.studentproject.controllers.StudentController;
import it.fi.mirelli.studentproject.dbs.StudentDatabase;
import it.fi.mirelli.studentproject.models.Student;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

	@Mock
	private StudentDatabase db;
	
	private List<Student> students;
	private StudentController studentController;
	StudentTestHelper helper;

	@Before
	public void setUp() throws Exception {
		studentController = new StudentController(db);
		students = new ArrayList<Student>();
		helper = new StudentTestHelper();

		when(db.getAll()).thenReturn(students);
	}

	@Test
	public void getAllStudentsWhenNoOne() {
		List<Student> allStudents = 
				studentController.getAllStudents();

		verify(db, times(1)).getAll();
		helper.assertStudentNumber(0, allStudents);
	}

	@Test
	public void getAllWhenOne() {
		studentsAddNew(1, "stud1@mail.com");
		
		List<Student> allStudents = 
				studentController.getAllStudents();
		
		verify(db, times(1)).getAll();
		helper.assertStudentNumber(1, allStudents);
	}

	private void studentsAddNew(int id, String email) {
		students.add(helper.
				createNewStudent(id, email));
	}

	@Test
	public void getAllWhenMany() {
		studentsAddNew(1, "stud1@mail.com");
		studentsAddNew(2, "stud2@mail.com");
		
		List<Student> allStudents = 
				studentController.getAllStudents();
		
		verify(db, times(1)).getAll();
		helper.assertStudentNumber(2, allStudents);
	}
	
	
	@Test 
	public void findByIdSuccessful() {
		int searchedStudentId = 1;
		studentsAddNew
			(searchedStudentId, "stud1@mail.com");
		
		when(db.findById(searchedStudentId)).
			thenReturn(helper.createNewStudent
				(searchedStudentId, "stud1@mail.com"));
		
		Student fetchedStudent = 
				studentController.
					findStudentById(searchedStudentId);
		
		ArgumentCaptor<Integer> idCaptor =
				ArgumentCaptor.forClass(Integer.class);
		
		verify(db, times(1)).findById(idCaptor.capture());
		
		assertEquals(searchedStudentId, 
				idCaptor.getAllValues().get(0).intValue());
		assertNotNull(fetchedStudent);
	}

	@Test 
	public void findByIdUnsuccessful() {
		int addedStudentId = 1;
		studentsAddNew
			(addedStudentId, "stud1@mail.com");
		
		Student fetchedStudent = 
				studentController.findStudentById(2);
		
		verify(db, times(addedStudentId)).findById(2);
		assertNull(fetchedStudent);
	}

	@Test
	public void addStudent() {
		ArgumentCaptor<Student> studentCaptor =
				ArgumentCaptor.forClass(Student.class);
		
		studentController.addStudent
			(helper.createNewStudent(1, "stud1@mail.com"));
		
		verify(db, times(1)).add(studentCaptor.capture());
		
		int expectedId = 1;
		assertEquals(expectedId, studentCaptor.
							getAllValues().get(0).getId());

		String expectedEmail = "stud1@mail.com";
		assertEquals(expectedEmail, studentCaptor.
							getAllValues().get(0).getEmail());
	}
	
	@Test 
	public void getEmail() {
		ArgumentCaptor<Integer> idCaptor =
				ArgumentCaptor.forClass(Integer.class);
		
		when(db.getEmailById(1)).
				thenReturn("stud1@mail.com");
		
		String fetchedEmail = studentController.
								getStudentEmailById(1);
		
		verify(db, times(1)).getEmailById(idCaptor.capture());
		
		int expectedId = 1;
		assertEquals(expectedId, idCaptor.
				getAllValues().get(0).intValue());
		
		String expectedEmail = "stud1@mail.com";
		assertEquals(expectedEmail, fetchedEmail);
	}
}
