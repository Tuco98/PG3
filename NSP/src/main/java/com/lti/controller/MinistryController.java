package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
	
	@PostMapping("/ministryUpdateInstitute")
	public Status ministryUpdatesInstituteStatus(@RequestParam("instituteId") long instituteId, @RequestParam("status") String status) {
		try {
			Institute ins = nspService.getAnInstituteById(instituteId);
			nspService.ministryUpdatesAnInstituteStatus(ins, status);
			//Institute ins = nspService.getAnInstituteById(id);
			Status status1 = new Status();
			status1.setStatus(StatusType.SUCCESS);
			status1.setMessage("Updation Successful!");
			
			return status1;
		}
		catch(NspServiceException e) {
			Status loginStatus = new Status();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}
	}
	
	@PostMapping("/ministryUpdateForm")
	public Status nodalUpdatesFormStatus(@RequestParam("formId") long formId, @RequestParam("status") String status) {
		try {
			ScholarshipForm form = nspService.getAScholarshipFormById(formId);
			nspService.ministryUpdatesAFormStatus(form, status);
			//Institute ins = nspService.getAnInstituteById(id);
			Status status1 = new Status();
			status1.setStatus(StatusType.SUCCESS);
			status1.setMessage("Updation Successful!");
			
			return status1;
		}
		catch(NspServiceException e) {
			Status loginStatus = new Status();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}
	}
}
