package it.fi.mirelli.com.project.examsystem.models;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Student.class)
public abstract class Student_ {

	public static volatile SingularAttribute<Student, Integer> id;
	public static volatile SingularAttribute<Student, String> email;
	
}
