package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.InsLoginStatus;
import com.lti.dto.InstituteLoginDto;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;

import com.lti.entity.Institute;
import com.lti.entity.Scheme;

import com.lti.exception.NspServiceException;
import com.lti.service.NspService;

@RestController
@CrossOrigin

public class InstituteController {
	
	@Autowired
	private NspService nspService;

	@PostMapping(path = "/registerInstitute")
	public Status addScheme(@RequestBody Institute institute) {
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
			Institute ins = nspService.instituteLogin(loginDto.getInstituteId(), loginDto.getPassword());
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

}
