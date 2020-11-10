package com.lti.service;

import java.util.List;

import com.lti.entity.Institute;
import com.lti.entity.Nodal;
import com.lti.entity.Scheme;
import com.lti.entity.ScholarshipForm;
import com.lti.entity.Student;

public interface NspService {

	// Scheme services
	public void addAScheme(Scheme scheme);

	public Scheme findAScheme(long schemeId);

	// Institute services
	public long registerAnInstitute(Institute institute);

	public Institute getAnInstituteById(long instituteId);

	public List<Institute> fetchAllInstitutes();

	public Institute instituteLogin(long userId, String password);

	public void instituteUpdatesAStudentStatus(Student student, String status); // doubtful status="Approves/Reject"

	public void instituteUpdatesAForm(ScholarshipForm form, String status);

	public List<Institute> fetchAllInstitutesByNodalStatus(String status);
	
	public void ministryUpdatesAnInstituteStatus(Institute institute, String status);
	
	public List<Institute> fetchAllInstitutesByMinistryStatus(String status);
	
	public Long findInstituteOfStudent(long studentId);

	// Students services

	public void registerAStudent(Student student);

	public Student getAStudentById(long studentId);

	public Student studentLogin(long aadhar, String password);

	public List<Student> fetchAllStudents();

	public List<Student> fetchStudentsOfParticularInstituteByStatus(long instituteId, String status);

	// Nodal methods

	public void registerANodal(Nodal nodal);

	public Nodal getANodalById(int nodalUid);

	public Nodal nodalLogin(int userId, String password);

	public void nodalUpdatesAForm(ScholarshipForm form, String formStatus);

	public void nodalUpdatesAnInstituteStatus(Institute institute, String status);
	
	public List<Nodal> fetchAllNodals();

	// Scholarship form methods

	public void applyAScholarshipForm(ScholarshipForm form);
	
	public void updateAScholarshipForm(ScholarshipForm form);

	public ScholarshipForm getAScholarshipFormById(long form_id);

	public List<ScholarshipForm> fetchAllScholarshipForms();

	public List<ScholarshipForm> fetchAllFormsByInstituteStatus(String status);

	public List<ScholarshipForm> fetchAllFormsByNodalStatus(String status);
	
	public List<ScholarshipForm> fetchFormsOfParticularInstituteByInstituteStatus(long instituteId, String status);

	// Ministry methods

	public boolean ministryLogin(int userId, String password);

	public void ministryUpdatesAFormStatus(ScholarshipForm form, String status);

	List<Institute> fetchAllInstitutesByStatus(String status);
	
	public List<ScholarshipForm> fetchFormsUsingNodalStatus(String status);

	public List<ScholarshipForm> fetchFormsUsingMinistryStatus(String status);
	
	public String studentForgotPassword(long studentId, String email);
	
	public String instituteForgotPassword(long instituteId,String email);
	
	public long fetchFormByStudentId(long studentId);
	public long findFormByStudentId(long studentId);
	
	public String nodalForgotPassword(int nodalId,String email);
	
	public String ministryForgotPassword(String ministryId, String email);
	
	public void updateAnInstitute(Institute institute);
	
}
