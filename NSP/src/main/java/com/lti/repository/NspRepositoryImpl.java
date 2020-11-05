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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Institute findAnInstituteById(long instituteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Institute findAnInstituteByInstituteCode(String instituteCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Institute> fetchAllInstitutes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean instituteLogin(long userId, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public long saveAStudent(Student student) {
		Student s=em.merge(student);
		return s.getStudentAadharNumber();
	}

	@Override
	public Student findAStudentById(long studentId) {
		return em.find(Student.class, studentId);
		
	}

	@Override
	public boolean studentLogin(long aadhar, String password) {
		// TODO Auto-generated method stub
		return false;
	}

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

	@Override
	public boolean nodalLogin(int userId, String password) {
		// TODO Auto-generated method stub
		return false;
	}

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

	@Override
	public boolean ministryLogin(int userId, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
