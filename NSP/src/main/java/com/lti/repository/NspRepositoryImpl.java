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
	@Transactional
	public long saveAnInstitute(Institute institute) {
		Institute ins = em.merge(institute);
		return ins.getInstituteId();
	}

	@Override
	public Institute findAnInstituteById(long instituteId) {
		return em.find(Institute.class, instituteId);
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
	@Transactional
	public void saveANodal(Nodal nodal) {
		
		em.merge(nodal);

	}

	@Override
	public Nodal findANodalById(int nodalUid) {
		// TODO Auto-generated method stub
		return em.find(Nodal.class, nodalUid);
	}

	/*
	 * @Override public boolean nodalLogin(int userId, String password) { // TODO
	 * Auto-generated method stub return false; }
	 */

	@Override
	@Transactional
	public long saveAScholarshipForm(ScholarshipForm form) {
		ScholarshipForm f = em.merge(form);
		return f.getFormId();
	}

	@Override
	public ScholarshipForm findAScholarshipFormById(long formId) {
		// TODO Auto-generated method stub
		return em.find(ScholarshipForm.class, formId);
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
	public boolean isInstitutePresent(String email) {
		return (Long) em.createQuery("select count(i.id) from Institute i where i.instituteEmail = :em")
				.setParameter("em", email).getSingleResult() == 1 ? true : false;
	}

	@Override
	public boolean isStudentPresent(String email) {
		return (Long) em.createQuery("select count(s.id) from Student s where s.studentEmail = :id")
				.setParameter("id", email).getSingleResult() == 1 ? true : false;
	}

	@Override
	public boolean isNodalPresent(int nodalId) {
		return (Long) em.createQuery("select count(n.id) from Nodal n where n.nodalUid = :id")
				.setParameter("id", nodalId).getSingleResult() == 1 ? true : false;
	}

	@Override
	public boolean isMinistryPresent(int ministryId) {
		return (Integer) em.createQuery("select count(m.id) from Ministry m where m.ministryUid = :id")
				.setParameter("id", ministryId).getSingleResult() == 1 ? true : false;
	}

	@Override
	public Long findInstituteByIdAndPassword(long instituteId, String password) {

		return (Long) em
				.createQuery(
						"select i.instituteId from Institute i where i.institudeId=:id and i.institutePassword=:psw")
				.setParameter("id", instituteId).setParameter("psw", password).getSingleResult();
	}

	@Override
	public Student findStudentByIdAndPassword(long studentId, String password) {
		String jpql = "select s from Student s where s.studentAadharNumber=:id and i.studentPassword=:psw";
		Query query = em.createQuery(jpql, Institute.class);
		query.setParameter("id", studentId);
		query.setParameter("psw", password);
		return (Student) query.getSingleResult();
	}

	@Override
	public Nodal findNodalByIdAndPassword(long nodalId, String password) {
		String jpql = "select n from Nodal n where n.nodalUid=:id and i.nodalPassword=:psw";
		Query query = em.createQuery(jpql, Institute.class);
		query.setParameter("id", nodalId);
		query.setParameter("psw", password);
		return (Nodal) query.getSingleResult();
	}
	@Override
	public List<ScholarshipForm> fetchFormsOfAnInstituteWithInstituteStatus(long instituteId, String status){
		String jpql = "select s from ScholarshipForm s where institute_id=:id and inst_verification_status=:ivs";
		Query query = em.createQuery(jpql, ScholarshipForm.class);
		query.setParameter("id", instituteId);
		query.setParameter("ivs", status);
		return query.getResultList();
	}

	@Override
	public List<ScholarshipForm> fetchFormsUsingNodalStatus(String status) {
		String st = "Approved";
		String jpql="select s from ScholarshipForm s where s.instituteVerificationStatus=:ivs and s.nodalVerificationStatus=:nvs";
		Query query = em.createQuery(jpql, ScholarshipForm.class);
		query.setParameter("ivs", st);
		query.setParameter("nvs", status);
		return query.getResultList();		
	}

	@Override
	public List<ScholarshipForm> fetchFormsUsingMinistryStatus(String status) {
		String st = "Approved";
		String jpql="select s from ScholarshipForm s where s.nodalVerificationStatus=:nvs and s.ministryVerificationStatus=:mvs";
		Query query = em.createQuery(jpql, ScholarshipForm.class);
		query.setParameter("nvs", st);
		query.setParameter("mvs", status);
		return query.getResultList();
	}
	
	@Override
	public boolean hasStudentAppliedForAForm(long studentId) {
		return (Long)
                em
                .createQuery("select count(s.formId) from ScholarshipForm s where s.student.studentAadharNumber = :id")
                .setParameter("id", studentId)
                .getSingleResult() == 1 ? true : false;
	}
}
