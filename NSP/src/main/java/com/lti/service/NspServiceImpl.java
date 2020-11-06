package com.lti.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Institute;
import com.lti.entity.Nodal;
import com.lti.entity.Scheme;
import com.lti.entity.ScholarshipForm;
import com.lti.entity.Student;
import com.lti.exception.NspServiceException;
import com.lti.repository.NspRepository;

@Service
public class NspServiceImpl implements NspService {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private NspRepository nspRepo;
	
	@Override
	public void addAScheme(Scheme scheme) {
		
		nspRepo.saveAScheme(scheme);

	}

	@Override
	public Scheme findAScheme(long schemeId) {
		
		return nspRepo.findAScheme(schemeId);
	}

	@Override
	public long registerAnInstitute(Institute institute) {
		if(!nspRepo.isInstitutePresent(institute.getInstituteId())) {
			long id=nspRepo.saveAnInstitute(institute);
            String text="Successfully registered. Your id is "+id +"\n You Will be notified after Verification.";
            String subject="Registration Confirmation";
            emailService.sendEmailForNewRegistration(institute.getInstituteEmail(), text, subject);
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
	public Institute instituteLogin(long instituteId, String password) {
//		if(!nspRepo.isInstitutePresent(userId)) {
//			return null;
//		}
//		else {
//			return nspRepo.findInstituteByIdAndPassword(userId, password);
//		}
		Institute ins = nspRepo.findAnInstituteById(instituteId);
		if(ins.getInstitutePassword().equals(password)) {
			return ins;
		}
		else {
			return null;
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
	public void ministryUpdatesAnInstituteStatus(Institute institute, String status) {
		institute.setInstituteMinistryApproval(status);
		nspRepo.saveAnInstitute(institute);

	}

	@Override
	public List<Institute> fetchAllInstitutesByMinistryStatus(String status) {
		List<Institute> institutes=nspRepo.fetchAllInstitutes();
		List<Institute> ins=new ArrayList<>();
		for (Institute institute : institutes) {
			if(institute.getInstituteMinistryApproval().equals(status)) {
				ins.add(institute);
			}
		}
		return ins;
	}

	@Override
	public void registerAStudent(Student student) {
		// TODO Auto-generated method stub
		if(!nspRepo.isStudentPresent(student.getStudentAadharNumber())) {
			student.setStudentStatus("Not Approved");
			long id = nspRepo.saveAStudent(student);
			
			String text="Successfully registered. Your id is "+id;
            String subject="Registration Confirmation";
            emailService.sendEmailForNewRegistration(student.getStudentEmail(), text, subject);
		}
		else {
			throw new NspServiceException("Student already registered");
		}
		
	}

	@Override
	public Student getAStudentById(long studentId) {
		// TODO Auto-generated method stub
		return nspRepo.findAStudentById(studentId);
	}

	@Override
	public Student studentLogin(long aadhar, String password) {
//		if(!nspRepo.isStudentPresent(aadhar)) {
//			return null;
//		}
//		else {
//			return nspRepo.findStudentByIdAndPassword(aadhar, password);
//		}
		
		Student student = nspRepo.findAStudentById(aadhar);
		
		if(student.getStudentPassword().equals(password)) {
			return student;
		}
		else {
			return null;
		}
		
		
	}

	@Override
	public List<Student> fetchAllStudents() {
		
		return nspRepo.fetchAllStudents();
	}

	@Override
	public List<Student> fetchStudentsOfParticularInstituteByStatus(long instituteId, String status) {
		
//		Institute ins = nspRepo.findAnInstituteById(instituteId);
//		
//		List<Student> st= ins.getStudents();
		
		List<Student> students=nspRepo.fetchAllStudents();
		List<Student> st=new ArrayList<>();
		
		for (Student student : students) {
			if(student.getStudentStatus().equals(status) && (student.getInstitute().getInstituteId()==instituteId)) {
				student.setInstitute(null);
				st.add(student);
			}
		}
		return st;
	}

	@Override
	public void registerANodal(Nodal nodal) {
		if(!nspRepo.isNodalPresent(nodal.getNodalUid())) {
			nspRepo.saveANodal(nodal);
			System.out.println("Nodal successfully registered");
		}
		else {
			System.out.println("Registration failed");
	}

	}

	@Override
	public Nodal getANodalById(int nodalUid) {
		
		return nspRepo.findANodalById(nodalUid);
	}

	@Override
	public Nodal nodalLogin(int userId, String password) {
		/*
		 * if(nspRepo.isNodalPresent(userId)) { return null; } else { return
		 * nspRepo.findNodalByIdAndPassword(userId, password); }
		 */
		
		Nodal nodal=nspRepo.findANodalById(userId);
		if(nodal.getNodalPassword().equals(password)) {
			return nodal;
		}
		else {
			return null;
		}
	}
	

	@Override
	public void nodalUpdatesAForm(ScholarshipForm form, String formStatus) {
		form.setNodalVerificationStatus(formStatus);
		nspRepo.saveAScholarshipForm(form);

	}

	@Override
	public void nodalUpdatesAnInstituteStatus(Institute institute, String status) {
		institute.setInstituteNodalOfficerApproval(status);
		nspRepo.saveAnInstitute(institute);

	}

	@Override
	public long applyAScholarshipForm(ScholarshipForm form) {
		
		return nspRepo.saveAScholarshipForm(form);
	}

	@Override
	public ScholarshipForm getAScholarshipFormById(long form_id) {
		
		return nspRepo.findAScholarshipFormById(form_id);
	}

	@Override
	public List<ScholarshipForm> fetchAllScholarshipForms() {
		
		return nspRepo.fetchAllScholarshipForms();
	}

	@Override
	public List<ScholarshipForm> fetchAllFormsByInstituteStatus(String status) {
		List<ScholarshipForm> forms=nspRepo.fetchAllScholarshipForms();
		List<ScholarshipForm> sf=new ArrayList<>();
		for (ScholarshipForm scholarshipForm : forms) {
			if(scholarshipForm.getInstituteVerificationStatus().equals(status)) {
				sf.add(scholarshipForm);
			}
		}
		return sf;
	}

	@Override
	public List<ScholarshipForm> fetchAllFormsByNodalStatus(String status) {
		List<ScholarshipForm> forms=nspRepo.fetchAllScholarshipForms();
		List<ScholarshipForm> sf=new ArrayList<>();
		for (ScholarshipForm scholarshipForm : forms) {
			if(scholarshipForm.getNodalVerificationStatus().equals(status)) {
				sf.add(scholarshipForm);
			}
		}
		return sf;
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
		form.setMinistryVerificationStatus(status);
		nspRepo.saveAScholarshipForm(form);

	}

	@Override
	public List<Institute> fetchAllInstitutesByNodalStatus(String status) {
		List<Institute> institutes=nspRepo.fetchAllInstitutes();
		List<Institute> ins=new ArrayList<>();
		for (Institute institute : institutes) {
			if(institute.getInstituteNodalOfficerApproval().equals(status)) {
				ins.add(institute);
			}
		}
		return ins;
	}

	@Override
	public List<Institute> fetchAllInstitutesByStatus(boolean status) {
		List<Institute> institutes=nspRepo.fetchAllInstitutes();
		List<Institute> ins=new ArrayList<>();
		for (Institute institute : institutes) {
			if(institute.isInstituteStatus()==status) {
				ins.add(institute);
			}
		}
		return ins;
	}

}
