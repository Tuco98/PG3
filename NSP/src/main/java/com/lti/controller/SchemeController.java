package com.lti.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entity.Scheme;
import com.lti.exception.NspServiceException;
import com.lti.service.NspService;

@RestController
@CrossOrigin
public class SchemeController {
	
	@Autowired
	private NspService nspService;
	
	@PostMapping(path = "/addScheme")
	public Status addScheme(@RequestBody Scheme scheme) {
		try {
			nspService.addAScheme(scheme);
			
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Scheme added successfully");
			return status;
		}
		catch(NspServiceException e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
	}

}
