package com.project.student.dbs;

import com.project.student.models.Student;

public interface StudentDatabase extends Database<Student, Integer> {
	
	public String getEmailById(int id);
}
