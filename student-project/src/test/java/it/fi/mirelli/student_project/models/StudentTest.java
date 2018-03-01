package it.fi.mirelli.student_project.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.fi.mirelli.student_project.models.Student;

public class StudentTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testToString() {
		Student student = new Student();
		student.setId(1);
		student.setEmail("stud1@mail.com");
		String expected = "Student [id=1, email=stud1@mail.com]";
		assertEquals(expected , student.toString());
	}

}
