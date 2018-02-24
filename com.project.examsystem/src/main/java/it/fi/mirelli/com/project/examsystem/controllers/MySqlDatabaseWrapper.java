package it.fi.mirelli.com.project.examsystem.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import it.fi.mirelli.com.project.examsystem.dbs.Database;
import it.fi.mirelli.com.project.examsystem.models.Student;

public class MySqlDatabaseWrapper implements Database<Student, Integer> {

	private EntityManager em;
	private String select = "SELECT s FROM STUDENT s";
	
	public MySqlDatabaseWrapper(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public List<Student> getAll() {
		return this.em.createQuery(
				select, 
				Student.class).
				getResultList();
	}

	@Override
	public Student findById(Integer id) {
//		String query = DSL.using(SQLDialect.MYSQL)
		String whereIdIsEqual = " WHERE s.id = '"+id+"'";
		return this.em.createQuery(
				select + whereIdIsEqual, 
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
