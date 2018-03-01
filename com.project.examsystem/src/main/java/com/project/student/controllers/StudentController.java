package com.project.student.controllers;

import java.util.List;

import com.project.student.dbs.StudentDatabase;
import com.project.student.models.Student;

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
