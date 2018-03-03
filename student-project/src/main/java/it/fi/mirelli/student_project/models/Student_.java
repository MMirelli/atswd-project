package it.fi.mirelli.student_project.models;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Student.class)
public abstract class Student_ {

	private static volatile SingularAttribute<Student, Integer> id;
	private static volatile SingularAttribute<Student, String> email;
	
	public static SingularAttribute<Student, Integer> getId() {
		return id;
	}
	public static void setId(SingularAttribute<Student, Integer> id) {
		Student_.id = id;
	}
	public static SingularAttribute<Student, String> getEmail() {
		return email;
	}
	
}
