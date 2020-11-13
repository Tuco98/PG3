package com.lti.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.InsLoginStatus;
import com.lti.dto.InstituteDocUploadDto;
import com.lti.dto.InstituteLoginDto;
import com.lti.dto.InstituteRegisterDto;
import com.lti.dto.NodalLoginStatus;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;

import com.lti.entity.Institute;
import com.lti.entity.Scheme;
import com.lti.entity.ScholarshipForm;
import com.lti.entity.Student;
import com.lti.exception.NspServiceException;
import com.lti.service.NspService;

@RestController
@CrossOrigin

public class InstituteController {
	
	@Autowired
	private NspService nspService;

	@PostMapping(path = "/registerInstitute")
	public Status addInstitute(@RequestBody Institute institute) {
		try {
			nspService.registerAnInstitute(institute);
			
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Institute registered successfully");
			return status;
		}
		catch(NspServiceException e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/instituteLogin")
	public InsLoginStatus login(@RequestBody InstituteLoginDto loginDto) {
		try {
			Institute ins= nspService.instituteLogin(loginDto.getInstituteId(), loginDto.getPassword());
			if(ins == null) {
				throw new NspServiceException("Invalid credentials or login id not yet activated");
			}
			//Institute ins = nspService.getAnInstituteById(id);
			InsLoginStatus loginStatus = new InsLoginStatus();
			loginStatus.setStatus(StatusType.SUCCESS);
			loginStatus.setMessage("Login Successful!");
			loginStatus.setInstituteId(ins.getInstituteId());
			loginStatus.setInsName(ins.getInstituteName());
			return loginStatus;
		}
		catch(NspServiceException|NullPointerException e) {
			InsLoginStatus loginStatus = new InsLoginStatus();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage("Invalid credentials or Login ID not yet activated");
			return loginStatus;
		}
	}
	
	@GetMapping("/viewUnapprovedStudents")  //tested
	public List<Student> viewUnapprovedStudents(@RequestParam("instituteId") long instituteId) {
		try {
			
			List<Student> list = nspService.fetchStudentsOfParticularInstituteByStatus(instituteId, "Not Approved");
			
			return list;
		}
		catch(NspServiceException e) {
			System.out.println(e.getMessage());
			//asdajas
			return null;
		}
	}
	
	@GetMapping("/viewStudentsByStatus") //new one
	public List<Student> viewStudentsByStatus(@RequestParam("instituteId") long instituteId,@RequestParam("status") String status) {
		try {
			
			List<Student> list = nspService.fetchStudentsOfParticularInstituteByStatus(instituteId, status);
			
			return list;
		}
		catch(NspServiceException e) {
			System.out.println(e.getMessage());
			//asdajas
			return null;
		}
	}
	
	@GetMapping("/viewUnapprovedFormsByInstitute")
	public List<ScholarshipForm> viewUnapprovedFormsByInstitute(@RequestParam("instituteId") long instituteId) {
		try {
			
			List<ScholarshipForm> list = nspService.fetchFormsOfParticularInstituteByInstituteStatus(instituteId, "Not Approved");
			
			return list;
		}
		catch(NspServiceException e) {
			System.out.println(e.getMessage());
			//asdajas
			return null;
		}
	}
	
	@GetMapping("/viewFormsByInstituteStatus") //new one
	public List<ScholarshipForm> viewFormsByInstituteStatus(@RequestParam("instituteId") long instituteId, @RequestParam("status") String status ) {
		try {
			
			List<ScholarshipForm> list = nspService.fetchFormsOfParticularInstituteByInstituteStatus(instituteId, status);
			
			return list;
		}
		catch(NspServiceException e) {
			System.out.println(e.getMessage());
			//asdajas
			return null;
		}
	}
	//same method Tuco has implemented before 
	@PostMapping("/viewUnapprovedStudentsByInstitute")
	public List<Student> viewUnapprovesStudentsByInstitute(@RequestParam("instituteId") long instituteId) {
		try {
			
			List<Student> list = nspService.fetchStudentsOfParticularInstituteByStatus(instituteId, "Not Approved");
			
			return list;
		}
		catch(NspServiceException e) {
			System.out.println(e.getMessage());
			//asdajas
			return null;
		}
	}
	
//	@GetMapping("/fetchInstituteProfile")
//    public Institute fetchInstituteProfile(@RequestParam("instituteId") long instituteId, HttpServletRequest request) {
//        Institute ins=nspService.getAnInstituteById(instituteId);
//        return ins;
//    }
	
	@GetMapping("/fetchInstituteProfile")
	public Institute fetchInstituteProfile(@RequestParam("instituteId") long id, HttpServletRequest request) {
		// fetching customer data from the database
		Institute institute = nspService.getAnInstituteById(id);

		// reading the project's deployed folder location
		String projPath = request.getServletContext().getRealPath("/");
		String tempDownloadPath = projPath + "/downloads/";
		System.out.println(tempDownloadPath);
		// creating a folder within the project where we will place the profile pic of
		// the customer getting fetched
		File f = new File(tempDownloadPath);
		if (!f.exists())
			f.mkdir();
		String targetFile1 = tempDownloadPath + institute.getAffiliationCertificate();
		String targetFile2 = tempDownloadPath + institute.getRegistrationCertificate();

		// the original location where the uploaded images are present
		String uploadedImagesPath = "D:/uploads/";
		String sourceFile1 = uploadedImagesPath + institute.getAffiliationCertificate();
		String sourceFile2 = uploadedImagesPath + institute.getRegistrationCertificate();

		try {
			FileCopyUtils.copy(new File(sourceFile1), new File(targetFile1));
			FileCopyUtils.copy(new File(sourceFile2), new File(targetFile2));
		} catch (IOException e) {
			e.printStackTrace();
			// maybe for this customer there is no profile pic
		}

		return institute;
	}
	
	
	//forgot Password
	//fetch profile

	@GetMapping("/instituteForgotPassword")
    public Status instituteForgotPassword(@RequestParam("instituteId") long instituteId,@RequestParam("email") String email) {
		try {
			Status status=new Status();
			status.setStatus(StatusType.SUCCESS);
			nspService.instituteForgotPassword(instituteId, email);
			status.setMessage("Your password has been sent to your registered email.");
			return status;
		}
		catch (NspServiceException e) {
			Status status=new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
    }
	
	@PostMapping("/instituteUpdatesForm")
	public Status instituteUpdatesForm(@RequestParam("formId") long formId, @RequestParam("status") String status) {
		try {
			ScholarshipForm form = nspService.getAScholarshipFormById(formId);
			nspService.instituteUpdatesAForm(form, status);
			//Institute ins = nspService.getAnInstituteById(id);
			Status status1 = new Status();
			status1.setStatus(StatusType.SUCCESS);
			status1.setMessage("Updation Successful!");
			
			return status1;
		}
		catch(NspServiceException e) {
			Status status2 = new Status();
			status2.setStatus(StatusType.FAILURE);
			status2.setMessage(e.getMessage());
			return status2;
		}
	}
	
	@PostMapping("/instituteUpdatesStudent")
	public Status instituteUpdatesStudent(@RequestParam("studentId") long studentId, @RequestParam("status") String status) {
		try {
			Student student = nspService.getAStudentById(studentId);
			nspService.instituteUpdatesAStudentStatus(student, status);
			//Institute ins = nspService.getAnInstituteById(id);
			Status status1 = new Status();
			status1.setStatus(StatusType.SUCCESS);
			status1.setMessage("Updation Successful!");
			
			return status1;
		}
		catch(NspServiceException e) {
			Status status2 = new Status();
			status2.setStatus(StatusType.FAILURE);
			status2.setMessage(e.getMessage());
			return status2;
		}
	}
	
	@PostMapping("/insDocUpload")
	@CrossOrigin
	public Status upload(InstituteDocUploadDto docDto) {
		String docUploadLocation = "d:/uploads/";
		String registrationCertificate = "registrationCertificate" + docDto.getInstituteId()+docDto.getRegistrationCertificate().getOriginalFilename();
		String targetFile1 = docUploadLocation + registrationCertificate;
		String affiliationCertificate = "affiliationCertificate" + docDto.getInstituteId()+docDto.getAffiliationCertificate().getOriginalFilename();
		String targetFile2 = docUploadLocation + affiliationCertificate;
		
		try {
			FileCopyUtils.copy(docDto.getRegistrationCertificate().getInputStream(), new FileOutputStream(targetFile1));
			FileCopyUtils.copy(docDto.getAffiliationCertificate().getInputStream(), new FileOutputStream(targetFile2));
		}
		catch (IOException e) {
			e.printStackTrace();
			Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
		
		Institute institute=nspService.getAnInstituteById(docDto.getInstituteId());
		institute.setAffiliationCertificate(affiliationCertificate);
		institute.setRegistrationCertificate(registrationCertificate);
		nspService.updateAnInstitute(institute);
		
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Uploaded!");
		return status;
	
	}
	
	@PostMapping(path = "/registerInstitute2")
	public InstituteRegisterDto addInstitute2(@RequestBody Institute institute) {
		try {
			long id = nspService.registerAnInstitute(institute);
			
			InstituteRegisterDto status = new InstituteRegisterDto();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Institute registered successfully");
			status.setInstituteId(id);
			return status;
		}
		catch(NspServiceException e) {
			InstituteRegisterDto status = new InstituteRegisterDto();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
}
