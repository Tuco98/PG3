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
	
	public Institute findAnInstituteByInstituteCode(String instituteCode);
	
	public List<Institute> fetchAllInstitutes();
	
	public boolean instituteLogin(long userId, String password);
	
	//These methods will be written in service
	//public void instituteUpdatesAStudentStatus(long studentId, String status); //doubtful status="Approves/Reject"
	//public void instituteUpdatesAForm(long formId, String status);
	//public List<Institute> viewAllInstitutesByStatus(String status);
	
	
	// Students methods 
	
	public long saveAStudent(Student student);
	
	public Student findAStudentById(long studentId);
	
	public boolean studentLogin(long aadhar, String password);
	
	public List<Student> fetchAllStudents();
	
	//service->public List<Student> fetchStudentsOfParticularInstituteByStatus(long instituteId, String status);
	
	
	// Nodal methods
	
	public void saveANodal(Nodal nodal);

	public Nodal findANodalById(int nodalUid);
	
	public boolean nodalLogin(int userId, String password);
	
	//Needs to be written in service
	//public void nodalApproveAForm(long formId);
	
	
	//Scholarship form methods
	
	public long saveAScholarshipForm(ScholarshipForm form);

	public ScholarshipForm findAScholarshipFormById(long form_id);

	public List<ScholarshipForm> fetchAllScholarshipForms();
	
	
	// Ministry methods
	
	public boolean ministryLogin(int userId, String password);
	
	
	
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

	

}
