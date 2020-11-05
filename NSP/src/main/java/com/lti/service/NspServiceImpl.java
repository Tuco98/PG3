package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Institute;
import com.lti.entity.Nodal;
import com.lti.entity.Scheme;
import com.lti.entity.ScholarshipForm;
import com.lti.entity.Student;
import com.lti.repository.NspRepository;

@Service
public class NspServiceImpl implements NspService {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private NspRepository nspRepo;
	
	@Override
	public void addAScheme(Scheme scheme) {
		// TODO Auto-generated method stub

	}

	@Override
	public Scheme findAScheme(long schemeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long registerAnInstitute(Institute institute) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Institute getAnInstituteById(long instituteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Institute getAnInstituteByInstituteCode(String instituteCode) {
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
	public void instituteUpdatesAStudentStatus(Student student, String status) {
		// TODO Auto-generated method stub

	}

	@Override
	public void instituteUpdatesAForm(ScholarshipForm form, String status) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Institute> fetchAllInstitutesByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Institute> fetchAllInstitutesByNodalStatis(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ministryUpdatesAnInstituteStatus(Institute institute, String status) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Institute> fetchAllInstitutesByMinistryStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long registerAStudent(Student student) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Student getAStudentById(long studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean studentLogin(long aadhar, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Student> fetchAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> fetchStudentsOfParticularInstituteByStatus(long instituteId, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerANodal(Nodal nodal) {
		// TODO Auto-generated method stub

	}

	@Override
	public Nodal getANodalById(int nodalUid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean nodalLogin(int userId, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void nodalUpdatesAForm(ScholarshipForm form, String formStatus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nodalUpdatesAnInstituteStatus(Institute institute, String status) {
		// TODO Auto-generated method stub

	}

	@Override
	public long applyAScholarshipForm(ScholarshipForm form) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ScholarshipForm getAScholarshipFormById(long form_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScholarshipForm> fetchAllScholarshipForms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScholarshipForm> fetchAllFormsByInstituteStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScholarshipForm> fetchAllFormsByNodalStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScholarshipForm> fetchFormsOfParticularInstituteByInstituteStatus(long instituteId, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean ministryLogin(int userId, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ministryUpdatesAFormStatus(ScholarshipForm form, String status) {
		// TODO Auto-generated method stub

	}

}
