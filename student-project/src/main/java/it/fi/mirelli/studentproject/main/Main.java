package it.fi.mirelli.studentproject.main;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import it.fi.mirelli.studentproject.controllers.StudentController;
import it.fi.mirelli.studentproject.dbWrappers.SqlStudentsDBWrapper;
import it.fi.mirelli.studentproject.dbs.StudentDatabase;
import it.fi.mirelli.studentproject.models.Student;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws InterruptedException  {

		Thread.sleep(15000); // let db docker set up

		String hostName = "localhost";

		if (args.length > 0)
			hostName = args[0];

		
		String dbAccessURL = "jdbc:mysql://" + hostName + ":3306/StudentDB?useSSL=false";

		StudentController studentController = setUpStudentController(dbAccessURL);

		logger.info("Student1 is being added...");
		studentController.addStudent(createStudent(1, "stud1@mail.com"));
		logger.info("Student2 is being added...");
		studentController.addStudent(createStudent(2, "stud2@mail.com"));

		Student stud1 = studentController.findStudentById(1);
		logger.info("First student is been fetched: " + stud1);

		Student stud2 = studentController.findStudentById(2);
		logger.info("Second student is been fetched: " + stud2);

		List<Student> allStudents = studentController.getAllStudents();
		logger.info("All students are been fetched: " + allStudents);

		String studentEmailById1 = studentController.getStudentEmailById(1);
		logger.info("First student email has been fetched: " + studentEmailById1);
		
		logger.info("Student-app terminated");
	}

	private static StudentController setUpStudentController(String hostName) {

		HashMap<String, String> urlProperty = new HashMap<>();
		urlProperty.put("javax.persistence.jdbc.url", hostName);

		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("studentSystem-PU", urlProperty);

		StudentDatabase db = new SqlStudentsDBWrapper(emf.createEntityManager());
		return new StudentController(db);
	}

	private static Student createStudent(int id, String email) {
		Student stud1 = new Student();
		stud1.setId(id);
		stud1.setEmail(email);
		return stud1;
	}

}
