package it.fi.mirelli.com.project.examsystem.models;

public class Student {

	private int id;
	private String email;

	public Student(int id, String email) {
		super();
		this.id = id;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}


}
