package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.InsLoginStatus;
import com.lti.dto.InstituteLoginDto;
import com.lti.dto.NodalLoginDto;
import com.lti.dto.NodalLoginStatus;
import com.lti.dto.Status.StatusType;
import com.lti.entity.Institute;
import com.lti.entity.Nodal;
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
}
