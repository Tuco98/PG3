package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.InsLoginStatus;
import com.lti.dto.InstituteLoginDto;
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
			//Institute ins = nspService.getAnInstituteById(id);
			InsLoginStatus loginStatus = new InsLoginStatus();
			loginStatus.setStatus(StatusType.SUCCESS);
			loginStatus.setMessage("Login Successful!");
			loginStatus.setInstituteId(ins.getInstituteId());
			loginStatus.setInsName(ins.getInstituteName());
			return loginStatus;
		}
		catch(NspServiceException e) {
			InsLoginStatus loginStatus = new InsLoginStatus();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}
	}
	
	@PostMapping("/viewUnapprovedStudents")
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
	
	@PostMapping("/viewUnapprovedFormsByInstitute")
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
	
	//forgot Password
	//fetch profile

}
