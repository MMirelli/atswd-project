package it.fi.mirelli.studentproject.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StudentTest {

	@Test
	public void testToString() {
		
		Student student = new Student();
		student.setId(1);
		student.setEmail("stud1@mail.com");
		
		String expected = "Student [id=1, email=stud1@mail.com]";
		assertEquals(expected , student.toString());
	}

}
