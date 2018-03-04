package it.fi.mirelli.studentproject.controllers;

import java.util.List;

import it.fi.mirelli.studentproject.dbs.StudentDatabase;
import it.fi.mirelli.studentproject.models.Student;

public class StudentController {
	
	StudentDatabase studentDatabase;

	public StudentController(StudentDatabase db) {
		this.studentDatabase = db;
	}

	public List<Student> getAllStudents() {
		return studentDatabase.getAll();
	}

	public Student findStudentById(int id) {
		return studentDatabase.findById(id);
	}

	public void addStudent(Student newStudent) {
		studentDatabase.add(newStudent);		
	}

	public String getStudentEmailById(int id) {
		return studentDatabase.getEmailById(id);
	}

}
