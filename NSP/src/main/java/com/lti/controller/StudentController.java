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
import com.lti.dto.StudentLoginDto;
import com.lti.dto.StudentLoginStatus;
import com.lti.entity.Institute;
import com.lti.entity.Scheme;
import com.lti.entity.ScholarshipForm;
import com.lti.entity.Student;
import com.lti.exception.NspServiceException;
import com.lti.service.NspService;

@RestController
@CrossOrigin

public class StudentController {
	
	@Autowired
	private NspService nspService;

	@PostMapping(path = "/registerStudent")
	public Status addStudent(@RequestParam("institute_id") long insId, @RequestBody Student student) {
		try {
			Institute ins = nspService.getAnInstituteById(insId);
			student.setInstitute(ins);
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
	public Status login(@RequestBody StudentLoginDto loginDto) {
		try {
			Student student = nspService.studentLogin(loginDto.getStudentId(),loginDto.getPassword());
			//Institute ins = nspService.getAnInstituteById(id);
			StudentLoginStatus loginStatus = new StudentLoginStatus();
			loginStatus.setStatus(StatusType.SUCCESS);
			loginStatus.setMessage("Login Successful!");
			//loginStatus.setInstituteId(ins.getInstituteId());
			//loginStatus.setInsName(ins.getInstituteName());
			loginStatus.setStudentId(student.getStudentAadharNumber());
			loginStatus.setStudentName(student.getStudentName());
			return loginStatus;
		}
		catch(NspServiceException e) {
			InsLoginStatus loginStatus = new InsLoginStatus();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}
	}
	
	@PostMapping("/applyForScheme")
	public Status applyForAScholarship(@RequestParam("schemeId")long schemeId,@RequestParam("studentId")long studentId,@RequestParam("instituteId")long instituteId, @RequestBody ScholarshipForm form) {
		try {
			Institute ins = nspService.getAnInstituteById(instituteId);
			Student stu = nspService.getAStudentById(studentId);
			if(stu.getForm() != null) {
                throw new NspServiceException("Student already has a form");
            }
			Scheme scheme = nspService.findAScheme(schemeId);
			
			form.setInstituteObj(ins);
			form.setStudent(stu);
			
			nspService.applyAScholarshipForm(form);
			
			Status status = new Status();
			
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Login Successful!");
			//loginStatus.setInstituteId(ins.getInstituteId());
			//loginStatus.setInsName(ins.getInstituteName());
			return status;
		}
		catch(NspServiceException e) {
			Status loginStatus = new Status();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}
	}
	
	
	//forgot Password
	//checkSchemeStatus

}
