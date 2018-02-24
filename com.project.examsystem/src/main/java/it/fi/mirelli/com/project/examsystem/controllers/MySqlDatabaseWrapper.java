package it.fi.mirelli.com.project.examsystem.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import it.fi.mirelli.com.project.examsystem.dbs.Database;
import it.fi.mirelli.com.project.examsystem.models.Student;

public class MySqlDatabaseWrapper implements Database<Student, Integer> {

	private EntityManager em;

	public MySqlDatabaseWrapper(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public List<Student> getAll() {

		return this.em.createQuery(
				"SELECT s FROM STUDENT s", 
				Student.class).
				getResultList();
	}

	@Override
	public Student findById(Integer id) {
		return this.em.createQuery(
				"SELECT s FROM STUDENT s"
				+ " WHERE s.id = '"+id+"'", 
				Student.class).
				getSingleResult();
	}

	@Override
	public void add(Student element) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		em.persist(element);
		
		transaction.commit();
	}

}
