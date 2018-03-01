package it.fi.mirelli.student_project.dbs;

import it.fi.mirelli.student_project.models.Student;

public interface StudentDatabase extends Database<Student, Integer> {
	
	public String getEmailById(int id);
}
