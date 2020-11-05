package com.lti.service;

import java.util.List;

import javax.persistence.Query;

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
		if(!nspRepo.isInstitutePresent(institute.getInstituteId())) {
			return nspRepo.saveAnInstitute(institute);
			
		}
		return (Long) null;
	}

	@Override
	public Institute getAnInstituteById(long instituteId) {
		return nspRepo.findAnInstituteById(instituteId);
	}

	@Override
	public Institute getAnInstituteByInstituteCode(String instituteCode) {
		return nspRepo.findAnInstituteByInstituteCode(instituteCode);
	}

	@Override
	public List<Institute> fetchAllInstitutes() {
		return nspRepo.fetchAllInstitutes();
	}

	@Override
	public Institute instituteLogin(long userId, String password) {
		if(!nspRepo.isInstitutePresent(userId)) {
			return null;
		}
		else {
			return nspRepo.findInstituteByIdAndPassword(userId, password);
		}
	}

	@Override
	public void instituteUpdatesAStudentStatus(Student student, String status) {
		student.setStudentStatus(status);
		nspRepo.saveAStudent(student);
	}

	@Override
	public void instituteUpdatesAForm(ScholarshipForm form, String status) {
		form.setInstituteVerificationStatus(status);
		nspRepo.saveAScholarshipForm(form);

	}

	@Override
	public List<Institute> fetchAllInstitutesByStatus(String status) {
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
	public Student studentLogin(long aadhar, String password) {
		if(!nspRepo.isStudentPresent(aadhar)) {
			return null;
		}
		else {
			return nspRepo.findStudentByIdAndPassword(aadhar, password);
		}
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
	public Nodal nodalLogin(int userId, String password) {
		if(nspRepo.isNodalPresent(userId)) {
			return null;
		}
		else {
			return nspRepo.findNodalByIdAndPassword(userId, password);
		}
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
