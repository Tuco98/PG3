package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.InsLoginStatus;
import com.lti.dto.InstituteLoginDto;
import com.lti.dto.NodalLoginDto;
import com.lti.dto.NodalLoginStatus;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entity.Institute;
import com.lti.entity.Nodal;
import com.lti.entity.ScholarshipForm;
import com.lti.exception.NspServiceException;
import com.lti.service.NspService;

@RestController
@CrossOrigin
public class NodalController {
	
	@Autowired
	private NspService nspService;
	
	@PostMapping("/nodalLogin")
	public NodalLoginStatus login(@RequestBody NodalLoginDto loginDto) {
		try {
			Nodal nodal= nspService.nodalLogin(loginDto.getNodalId(), loginDto.getPassword());
			//Institute ins = nspService.getAnInstituteById(id);
			NodalLoginStatus loginStatus = new NodalLoginStatus();
			loginStatus.setStatus(StatusType.SUCCESS);
			loginStatus.setMessage("Login Successful!");
			loginStatus.setNodalId(nodal.getNodalUid());
			loginStatus.setNodalName(nodal.getNodalName());
			return loginStatus;
		}
		catch(NspServiceException e) {
			NodalLoginStatus loginStatus = new NodalLoginStatus();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}
	}
	
	@PostMapping("/nodalUpdateInstitute")
	public Status nodalUpdatesInstituteStatus(@RequestParam("instituteId") long instituteId, @RequestParam("status") String status) {
		try {
			Institute ins = nspService.getAnInstituteById(instituteId);
			nspService.nodalUpdatesAnInstituteStatus(ins, status);
			//Institute ins = nspService.getAnInstituteById(id);
			Status status1 = new NodalLoginStatus();
			status1.setStatus(StatusType.SUCCESS);
			status1.setMessage("Updation Successful!");
			
			return status1;
		}
		catch(NspServiceException e) {
			NodalLoginStatus loginStatus = new NodalLoginStatus();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}
	}
	
	@PostMapping("/nodalUpdateForm")
	public Status nodalUpdatesFormStatus(@RequestParam("formId") long formId, @RequestParam("status") String status) {
		try {
			ScholarshipForm form = nspService.getAScholarshipFormById(formId);
			nspService.nodalUpdatesAForm(form, status);
			//Institute ins = nspService.getAnInstituteById(id);
			Status status1 = new NodalLoginStatus();
			status1.setStatus(StatusType.SUCCESS);
			status1.setMessage("Updation Successful!");
			
			return status1;
		}
		catch(NspServiceException e) {
			NodalLoginStatus loginStatus = new NodalLoginStatus();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}
	}
}
