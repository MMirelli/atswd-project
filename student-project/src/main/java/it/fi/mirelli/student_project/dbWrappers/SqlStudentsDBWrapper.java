package it.fi.mirelli.student_project.dbWrappers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.fi.mirelli.student_project.dbs.StudentDatabase;
import it.fi.mirelli.student_project.models.Student;
import it.fi.mirelli.student_project.models.Student_;

public class SqlStudentsDBWrapper implements StudentDatabase {

	private EntityManager em;

	public SqlStudentsDBWrapper(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public List<Student> getAll() {
		CriteriaQuery<Student> criteria = selectAllFromStudent();

		return createQuery(criteria).getResultList();
	}

	@Override
	public Student findById(Integer id) {
		Student selectedStudent = selectStudentById(id);
		return selectedStudent;

	}
	

	@Override
	public void add(Student element) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		em.persist(element);

		transaction.commit();
	}

	@Override
	public String getEmailById(int id) {
		Student selectedStudent = selectStudentById(id); 
		return selectedStudent.getEmail();
	}

	private TypedQuery<Student> createQuery(CriteriaQuery<Student> criteria) {
		return em.createQuery(criteria);
	}

	private CriteriaQuery<Student> selectAllFromStudent() {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
		Root<Student> studentRoot = criteria.from(Student.class);
		criteria.select(studentRoot);
		return criteria;
	}

	private CriteriaQuery<Student> selectFromStudentWhereEqualId(Integer id) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
		Root<Student> studentRoot = criteria.from(Student.class);
		criteria.select(studentRoot);

		criteria.where(builder.equal(studentRoot.get(Student_.id), id));
		return criteria;
	}

	private Student selectStudentById(int id) {
		CriteriaQuery<Student> criteria = selectFromStudentWhereEqualId(id);
		
		Student selectedStudent = createQuery(criteria).getSingleResult();
		
		return selectedStudent;
	}

}