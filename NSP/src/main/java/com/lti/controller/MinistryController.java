package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entity.Institute;
import com.lti.entity.Nodal;
import com.lti.exception.NspServiceException;
import com.lti.service.NspService;

@RestController
@CrossOrigin
public class MinistryController {
	
	@Autowired
	private NspService nspService;
	
	@PostMapping(path = "/registerNodal")
	public Status addNodal(@RequestBody Nodal nodal) {
		try {
			nspService.registerANodal(nodal);
			
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Nodal registered successfully");
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
