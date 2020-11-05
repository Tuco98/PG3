package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Institute;
import com.lti.entity.Nodal;
import com.lti.entity.Scheme;
import com.lti.entity.ScholarshipForm;
import com.lti.entity.Student;

@Repository
public class NspRepositoryImpl implements NspRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public void saveAScheme(Scheme scheme) {
		// TODO Auto-generated method stub
		em.merge(scheme);

	}

	@Override
	public Scheme findAScheme(long schemeUID) {
		return em.find(Scheme.class, schemeUID);
	}

	@Override
	public List<Scheme> fetchAllSchemes() {
		return em.createNamedQuery("fetchAllSchemes").getResultList();

	}

	@Override
	public long saveAnInstitute(Institute institute) {
		Institute ins = em.merge(institute);
		return ins.getInstituteId();
	}

	@Override
	public Institute findAnInstituteById(long instituteId) {
		return em.find(Institute.class, instituteId);
	}

	@Override
	public Institute findAnInstituteByInstituteCode(String instituteCode) {

		String jpql = "select i from Institute i where i.instituteCode=:ic";
		Query query = em.createQuery(jpql, Institute.class);
		query.setParameter("ic", instituteCode);
		Institute ins = (Institute) query.getResultList().stream().findFirst().orElse(null);
		return ins;
	}

	@Override
	public List<Institute> fetchAllInstitutes() {
		return em.createNamedQuery("fetchAllInstitutes").getResultList();
	}

	/*
	 * @Override public boolean instituteLogin(long userId, String password) { //
	 * TODO Auto-generated method stub return false; }
	 */

	@Override
	@Transactional
	public long saveAStudent(Student student) {
		Student s = em.merge(student);
		return s.getStudentAadharNumber();
	}

	@Override
	public Student findAStudentById(long studentId) {
		return em.find(Student.class, studentId);

	}

	/*
	 * @Override public boolean studentLogin(long aadhar, String password) { // TODO
	 * Auto-generated method stub return false; }
	 */

	@Override
	public List<Student> fetchAllStudents() {
		return em.createNamedQuery("fetchAllStudents").getResultList();
	}

	@Override
	public void saveANodal(Nodal nodal) {
		// TODO Auto-generated method stub

	}

	@Override
	public Nodal findANodalById(int nodalUid) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @Override public boolean nodalLogin(int userId, String password) { // TODO
	 * Auto-generated method stub return false; }
	 */

	@Override
	public long saveAScholarshipForm(ScholarshipForm form) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ScholarshipForm findAScholarshipFormById(long form_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScholarshipForm> fetchAllScholarshipForms() {
		return em.createNamedQuery("fetchAllForms").getResultList();
	}

	/*
	 * @Override public boolean ministryLogin(int userId, String password) { // TODO
	 * Auto-generated method stub return false; }
	 */
	@Override
	public List<Nodal> fetchAllNodals() {
		return em.createNamedQuery("fetchAllNodals").getResultList();
	}

	@Override
	public boolean isInstitutePresent(long instituteId) {
		return (Long) em.createQuery("select count(i.id) from Institute i where i.instituteId = :id").setParameter("id", instituteId)
				.getSingleResult() == 1 ? true : false;
	}

	@Override
	public boolean isStudentPresent(long studentId) {
		return (Long) em.createQuery("select count(s.id) from Student s where s.studentAadharNumber = :id").setParameter("id", studentId)
				.getSingleResult() == 1 ? true : false;
	}

	@Override
	public boolean isNodalPresent(int nodalId) {
		return (Integer) em.createQuery("select count(n.id) from Nodal n where n.nodalUid = :id").setParameter("id", nodalId)
				.getSingleResult() == 1 ? true : false;
	}

	@Override
	public boolean isMinistryPresent(int ministryId) {
		return (Integer) em.createQuery("select count(m.id) from Ministry m where m.ministryUid = :id").setParameter("id", ministryId)
				.getSingleResult() == 1 ? true : false;
	}

	@Override
	public Institute findInstituteByIdAndPassword(long instituteId, String password) {
		String jpql="select i from Institute i where i.institudeId=:id and i.institutePassword=:psw";
		Query query=em.createQuery(jpql, Institute.class);
		query.setParameter("id", instituteId);
		query.setParameter("psw", password);
		return (Institute) query.getSingleResult();
	}

	@Override
	public Student findStudentByIdAndPassword(long studentId, String password) {
		String jpql="select s from Student s where s.studentAadharNumber=:id and i.studentPassword=:psw";
		Query query=em.createQuery(jpql, Institute.class);
		query.setParameter("id", studentId);
		query.setParameter("psw", password);
		return (Student) query.getSingleResult();
	}

	@Override
	public Nodal findNodalByIdAndPassword(long nodalId, String password) {
		String jpql="select n from Nodal n where n.nodalUid=:id and i.nodalPassword=:psw";
		Query query=em.createQuery(jpql, Institute.class);
		query.setParameter("id", nodalId);
		query.setParameter("psw", password);
		return (Nodal) query.getSingleResult();
	}

}
