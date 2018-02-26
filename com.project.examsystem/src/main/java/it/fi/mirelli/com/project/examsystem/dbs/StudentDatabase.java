package it.fi.mirelli.com.project.examsystem.dbs;

import it.fi.mirelli.com.project.examsystem.models.Student;

public interface StudentDatabase extends Database<Student, Integer> {
	
	public String getEmailById(int id);
}
