package it.fi.mirelli.com.project.examsystem.wrappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.fi.mirelli.com.project.examsystem.common.StudentTestHelper;
import it.fi.mirelli.com.project.examsystem.dbWrappers.SqlStudentsDBWrapper;
import it.fi.mirelli.com.project.examsystem.models.Student;

public abstract class AbstractSqlDBWrapperTest {

	private EntityManager em;
//	private Session session;

	private SqlStudentsDBWrapper sqlDbWrapper;
	private StudentTestHelper helper;
	private EntityManagerFactory emf;

	protected abstract EntityManagerFactory createEntityManagerFactory();
	
	@Before
	public void setUp() {
		
		emf = createEntityManagerFactory();
	
		em = emf.createEntityManager();
		sqlDbWrapper = new SqlStudentsDBWrapper(em);
		helper = new StudentTestHelper(em);
		
		
//		Configuration config = new Configuration().configure("remote_hibernate.cfg.xml");
//		SessionFactory sf = config.buildSessionFactory();
//		session = sf.openSession();
//		session.getTransaction().begin();
	}
	
//	@After
//	public void tearDown() {
//		if(em.getTransaction().isActive() && !em.getTransaction().getRollbackOnly())
//			em.getTransaction().commit();
////		session.flush();
////		session.close();
////		
//		em.close();
//	}

	@Test
	public void getAllWhenNoStudents() {
		helper.assertStudentNumber
				(0, sqlDbWrapper.getAll());
	}

	@Test
	public void getAllWhenManyStudents() {
	
		helper.persistNewStudent(1, "stud1@mail.com");
		helper.persistNewStudent(2, "stud2@mail.com");
	
		helper.assertStudentNumber(2, sqlDbWrapper.
					getAll());
	}

	@Test
	public void successfulFindStudentById() {
	
		helper.persistNewStudent(1, "stud1@mail.com");
	
		assertEquals(1, sqlDbWrapper.findById(1).getId());
	}

	@Test(expected = NoResultException.class)
	public void unsuccessfulFindStudentById() {
	
		helper.persistNewStudent(1, "stud1@mail.com");
	
		sqlDbWrapper.findById(2);
	}

	@Test
	public void successfulAddStudent() {
		sqlDbWrapper.add(helper.createNewStudent(1, "stud1@mail.com"));
		
		Student foundStudent = em.find(Student.class, 1);
		assertNotNull(foundStudent);
	}

	@Test(expected = EntityExistsException.class)
	public void unsuccessfulAddStudent() {
		helper.persistNewStudent(1, "stud@mail.com");
	
		sqlDbWrapper.add(helper.createNewStudent(1, "stud1@mail.com"));	
		
	}

	@Test
	public void successfulGetEmailById() {
		helper.persistNewStudent(1, "stud@mail.com");
	
		String actualEmail = sqlDbWrapper.getEmailById(1);
		assertEquals("stud@mail.com", actualEmail);
	}

	@Test(expected = NoResultException.class)
	public void unsuccessfulGetEmailById() {
		helper.persistNewStudent(1, "stud@mail.com");
	
		String actualEmail = sqlDbWrapper.getEmailById(2);
		assertEquals("stud@mail.com", actualEmail);
	}

}