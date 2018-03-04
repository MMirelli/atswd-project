package it.fi.mirelli.studentproject.dbs;

import it.fi.mirelli.studentproject.models.Student;

public interface StudentDatabase extends Database<Student, Integer> {
	
	public String getEmailById(int id);
}
