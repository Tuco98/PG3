package com.lti.controller;

import java.io.File;
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
import com.lti.dto.NodalLoginStatus;
import com.lti.dto.Status;
import com.lti.dto.StudentLoginDto;
import com.lti.dto.StudentLoginStatus;
import com.lti.dto.Status.StatusType;

import com.lti.entity.Institute;
import com.lti.entity.Ministry;
import com.lti.entity.Nodal;
import com.lti.entity.ScholarshipForm;
import com.lti.entity.Student;
import com.lti.exception.NspServiceException;
import com.lti.service.NspService;

@RestController
@CrossOrigin
public class MinistryController {
	
	private String ministryUid = "Admin";
	private String ministryPassword = "AdminPassword";

	@Autowired
	private NspService nspService;

	@PostMapping("/ministryLogin")
	public Status login(@RequestBody Ministry ministry) {
		try {
			if (ministry.getMinistryUid().equals(ministryUid) && ministry.getMinistryPassword().equals(ministryPassword)) {
				Status status = new Status();
				status.setStatus(StatusType.SUCCESS);
				status.setMessage("Login Successful");
				return status;

			} else {
				throw new NspServiceException("Wrong credentials");
			}
		} catch (NspServiceException e) {
			InsLoginStatus loginStatus = new InsLoginStatus();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}
	}

	@PostMapping(path = "/registerNodal")
	public Status addNodal(@RequestBody Nodal nodal) {
		try {
			nspService.registerANodal(nodal);

			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Nodal registered successfully");
			return status;
		} catch (NspServiceException e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@PostMapping("/ministryUpdateInstitute")
	public Status ministryUpdatesInstituteStatus(@RequestParam("instituteId") long instituteId,
			@RequestParam("status") String status) {
		try {
			Institute ins = nspService.getAnInstituteById(instituteId);
			nspService.ministryUpdatesAnInstituteStatus(ins, status);
			// Institute ins = nspService.getAnInstituteById(id);
			Status status1 = new Status();
			status1.setStatus(StatusType.SUCCESS);
			status1.setMessage("Updation Successful!");

			return status1;
		} catch (NspServiceException e) {
			Status loginStatus = new Status();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}
	}

	@PostMapping("/ministryUpdateForm")
	public Status ministryUpdatesFormStatus(@RequestParam("formId") long formId,
			@RequestParam("status") String status) {
		try {
			ScholarshipForm form = nspService.getAScholarshipFormById(formId);
			nspService.ministryUpdatesAFormStatus(form, status);
			// Institute ins = nspService.getAnInstituteById(id);
			Status status1 = new Status();
			status1.setStatus(StatusType.SUCCESS);
			status1.setMessage("Updation Successful!");

			return status1;
		} catch (NspServiceException e) {
			Status loginStatus = new Status();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}
	}

	@GetMapping("/viewUnapprovedFormsByMinistry")
	public List<ScholarshipForm> viewUnapprovedFormsByIMinistry() {
		try {

			List<ScholarshipForm> list = nspService.fetchFormsUsingMinistryStatus("Not Approved");

			return list;
		} catch (NspServiceException e) {
			System.out.println(e.getMessage());
			// asdajas
			return null;
		}
	}

	@GetMapping("/viewUnapprovedInstitutesByMinistry")
	public List<Institute> viewUnapprovedInstitutesByMinistry() {
		try {

			List<Institute> list = nspService.fetchAllInstitutesByMinistryStatus("Not Approved");

			return list;
		} catch (NspServiceException e) {
			System.out.println(e.getMessage());
			// asdajas
			return null;
		}
	}

	@GetMapping("/fetchFormByFormId")
	public ScholarshipForm fetchFormByFormId(@RequestParam("formId") int id, HttpServletRequest request) {
		// fetching customer data from the database
		ScholarshipForm form = nspService.getAScholarshipFormById(id);

		// reading the project's deployed folder location
		String projPath = request.getServletContext().getRealPath("/");
		String tempDownloadPath = projPath + "/downloads/";
		System.out.println(tempDownloadPath);
		// creating a folder within the project where we will place the profile pic of
		// the customer getting fetched
		File f = new File(tempDownloadPath);
		if (!f.exists())
			f.mkdir();
		String targetFile1 = tempDownloadPath + form.getClass10Marksheet();
		String targetFile2 = tempDownloadPath + form.getClass12Marksheet();
		String targetFile3 = tempDownloadPath + form.getAadharCard();
		String targetFile4 = tempDownloadPath + form.getDomicileCertificate();
		String targetFile5 = tempDownloadPath + form.getPrevYearMarksheet();
		String targetFile6 = tempDownloadPath + form.getStudentPic();
		String targetFile7 = tempDownloadPath + form.getBankPassbook();
		String targetFile8 = tempDownloadPath + form.getCasteIncomeCertificate();
		String targetFile9 = tempDownloadPath + form.getFeeReceiptOfCurrentYear();
		String targetFile10 = tempDownloadPath + form.getInstituteIdCard();

		// the original location where the uploaded images are present
		String uploadedImagesPath = "D:/uploads/";
		String sourceFile1 = uploadedImagesPath + form.getClass10Marksheet();
		String sourceFile2 = uploadedImagesPath + form.getClass12Marksheet();
		String sourceFile3 = uploadedImagesPath + form.getAadharCard();
		String sourceFile4 = uploadedImagesPath + form.getDomicileCertificate();
		String sourceFile5 = uploadedImagesPath + form.getPrevYearMarksheet();
		String sourceFile6 = uploadedImagesPath + form.getStudentPic();
		String sourceFile7 = uploadedImagesPath + form.getBankPassbook();
		String sourceFile8 = uploadedImagesPath + form.getCasteIncomeCertificate();
		String sourceFile9 = uploadedImagesPath + form.getFeeReceiptOfCurrentYear();
		String sourceFile10 = uploadedImagesPath + form.getInstituteIdCard();

		try {
			FileCopyUtils.copy(new File(sourceFile1), new File(targetFile1));
			FileCopyUtils.copy(new File(sourceFile2), new File(targetFile2));
			FileCopyUtils.copy(new File(sourceFile3), new File(targetFile3));
			FileCopyUtils.copy(new File(sourceFile4), new File(targetFile4));
			FileCopyUtils.copy(new File(sourceFile5), new File(targetFile5));
			FileCopyUtils.copy(new File(sourceFile6), new File(targetFile6));
			FileCopyUtils.copy(new File(sourceFile7), new File(targetFile7));
			FileCopyUtils.copy(new File(sourceFile8), new File(targetFile8));
			FileCopyUtils.copy(new File(sourceFile9), new File(targetFile9));
			FileCopyUtils.copy(new File(sourceFile10), new File(targetFile10));
		} catch (IOException e) {
			e.printStackTrace();
			// maybe for this customer there is no profile pic
		}

		return form;
	}
	
	@PostMapping("/passwordRecovery")
	public String passwordRecovery(@RequestParam("ministryId") String ministryId, @RequestParam("email") String email) {
		try {
			return nspService.ministryForgotPassword(ministryId, email);
		}
		catch(NspServiceException e){
			return e.getMessage();
		}
	}

	@GetMapping("/viewNodalList")
	public List<Nodal> viewNodalList() {
		try {
			
			List<Nodal> list = nspService.fetchAllNodals();
			
			return list;
		}
		catch(NspServiceException e) {
			System.out.println(e.getMessage());
			//asdajas
			return null;
		}
	}
	
	
	
}
