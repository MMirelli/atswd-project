package it.fi.mirelli.student_project.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import it.fi.mirelli.student_project.controllers.StudentController;
import it.fi.mirelli.student_project.dbWrappers.SqlStudentsDBWrapper;
import it.fi.mirelli.student_project.dbs.StudentDatabase;
import it.fi.mirelli.student_project.models.Student;

public class Main {

	private static Logger LOGGER = Logger.getLogger(Main.class);
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
	
		
		StudentController studentController = setUpStudentController();

		LOGGER.info("Student1 is being added...");
		studentController.addStudent(createStudent(1, "stud1@mail.com"));
		LOGGER.info("Student2 is being added...");
		studentController.addStudent(createStudent(2, "stud2@mail.com"));
		
		
		Student stud1 = studentController.findStudentById(1);
		LOGGER.info("First student is been fetched: " + stud1);
		
		Student stud2 = studentController.findStudentById(2);
		LOGGER.info("Second student is been fetched: " + stud2);
		
		List<Student> allStudents = studentController.getAllStudents();
		LOGGER.info("All students are been fetched: " + allStudents);
		
		String studentEmailById1 = studentController.getStudentEmailById(1);
		LOGGER.info("First student email has been fetched: " + studentEmailById1);
	}


	private static StudentController setUpStudentController() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentSystem-PU");
		StudentDatabase db = new SqlStudentsDBWrapper(emf.createEntityManager());
		StudentController studentController = new StudentController(db);
		return studentController;
	}

	
	private static Student createStudent(int id, String email) {
		Student stud1 = new Student();
		stud1.setId(id);
		stud1.setEmail(email);
		return stud1;
	}

}
