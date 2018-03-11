package it.fi.mirelli.studentproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "STUDENT") 
public class Student {

	public Student() {
		super();
	}

	@Id
	private Integer id;

	@Column
	private String email;

	public void setId(int id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", email=" + email + "]";
	}

}
