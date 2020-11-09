package com.lti.repository;

import java.util.List;

import com.lti.entity.Institute;
import com.lti.entity.Nodal;
import com.lti.entity.Scheme;
import com.lti.entity.ScholarshipForm;
import com.lti.entity.Student;

public interface NspRepository {
	
	//Scheme methods
	public void saveAScheme(Scheme scheme);
	public Scheme findAScheme(long schemeUID);
	public List<Scheme> fetchAllSchemes();
	
	
	// Institute methods
	public long saveAnInstitute(Institute institute);
	
	public Institute findAnInstituteById(long instituteId);
	
	public List<Institute> fetchAllInstitutes();
	
	public Long findInstituteByIdAndPassword(long instituteId, String password);
	
	public Long findInstituteOfStudent(long studentId);
	
//	public boolean instituteLogin(long userId, String password);
	
	//These methods will be written in service
	//public void instituteUpdatesAStudentStatus(long studentId, String status); //doubtful status="Approves/Reject"
	//public void instituteUpdatesAForm(long formId, String status);
	//public List<Institute> viewAllInstitutesByStatus(String status);
	
	
	// Students methods 
	
	public long saveAStudent(Student student);
	
	public Student findAStudentById(long studentId);
	
	//public boolean studentLogin(long aadhar, String password);
	
	public boolean isStudentPresent(String email);
	
	public List<Student> fetchAllStudents();
	
	public Student findStudentByIdAndPassword(long studentId, String password);
	
	public boolean hasStudentAppliedForAForm(long studentId);
	
	//service->public List<Student> fetchStudentsOfParticularInstituteByStatus(long instituteId, String status);
	
	
	// Nodal methods
	
	public void saveANodal(Nodal nodal);

	public Nodal findANodalById(int nodalUid);
	
	//public boolean nodalLogin(int userId, String password);
	
	public boolean isNodalPresent(int nodalId);
	
	public Nodal findNodalByIdAndPassword(long nodalId, String password);
	
	public List<Nodal> fetchAllNodals();
	
	//Needs to be written in service
	//public void nodalApproveAForm(long formId);
	
	
	//Scholarship form methods
	
	public long saveAScholarshipForm(ScholarshipForm form);

	public ScholarshipForm findAScholarshipFormById(long form_id);

	public List<ScholarshipForm> fetchAllScholarshipForms();
	
	
	// Ministry methods
	
	//public boolean ministryLogin(int userId, String password);
	
	public boolean isMinistryPresent(int ministryId);
	public boolean isInstitutePresent(String email);
	
	
	
	//service-->public List<ScholarshipForm> viewUnapprovedFormsOfParticularInstitute(long instituteId);


	/*

	All to be written in service

	public List<ScholarshipForm> viewAllInstituteApprovedForms();

	

	public List<ScholarshipForm> viewAllNodalApprovedForms();

	public List<Institute> viewAllNodalApprovedInstitutes();

	

	

	//service-->public void nodalUpdatesAnInstituteStatus(String code);

	public void ministryApprovesAForm(long id);

	public void ministryRejectsAForm(long id);

	//service-->public void ministryApprovesAnInstitute(String code);

	*/

	public List<ScholarshipForm> fetchFormsOfAnInstituteWithInstituteStatus(long instituteId, String status);
	public List<ScholarshipForm> fetchFormsUsingNodalStatus(String status);
	public List<ScholarshipForm> fetchFormsUsingMinistryStatus(String status);
	public long fetchFormByStudentId(long studentId);
}
