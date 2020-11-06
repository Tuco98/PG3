package com.lti.controller;

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
import com.lti.entity.Student;
import com.lti.exception.NspServiceException;
import com.lti.service.NspService;

@RestController
@CrossOrigin

public class StudentController {
	
	@Autowired
	private NspService nspService;

	@PostMapping(path = "/registerStudent")
	public Status addScheme(@RequestBody Student student) {
		try {
			nspService.registerAStudent(student);
			//Student stu = nspService.getAStudentById(studentId);
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Student registered successfully");
			return status;
		}
		catch(NspServiceException e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/studentLogin")
	public Status login(@RequestParam("studentId") long studentId, @RequestParam("psw") String password) {
		try {
			Student student = nspService.studentLogin(studentId, password);
			//Institute ins = nspService.getAnInstituteById(id);
			Status loginStatus = new Status();
			loginStatus.setStatus(StatusType.SUCCESS);
			loginStatus.setMessage("Login Successful!");
			//loginStatus.setInstituteId(ins.getInstituteId());
			//loginStatus.setInsName(ins.getInstituteName());
			return loginStatus;
		}
		catch(NspServiceException e) {
			InsLoginStatus loginStatus = new InsLoginStatus();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}
	}

}
