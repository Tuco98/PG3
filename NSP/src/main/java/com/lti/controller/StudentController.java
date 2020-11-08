package com.lti.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.DocumentUploadDto;
import com.lti.dto.InsLoginStatus;
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
			// Student stu = nspService.getAStudentById(studentId);
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Student registered successfully");
			return status;
		} catch (NspServiceException e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@PostMapping("/studentLogin")
	public Status login(@RequestBody StudentLoginDto loginDto) {
		try {
			Student student = nspService.studentLogin(loginDto.getStudentId(), loginDto.getPassword());
			// Institute ins = nspService.getAnInstituteById(id);
			StudentLoginStatus loginStatus = new StudentLoginStatus();
			loginStatus.setStatus(StatusType.SUCCESS);
			loginStatus.setMessage("Login Successful!");
			// loginStatus.setInstituteId(ins.getInstituteId());
			// loginStatus.setInsName(ins.getInstituteName());
			loginStatus.setStudentId(student.getStudentAadharNumber());
			loginStatus.setStudentName(student.getStudentName());
			return loginStatus;
		} catch (NspServiceException e) {
			InsLoginStatus loginStatus = new InsLoginStatus();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}
	}

	@PostMapping("/applyForScheme")
	public Status applyForAScholarship(@RequestParam("schemeId") long schemeId,
			@RequestParam("studentId") long studentId, @RequestParam("instituteId") long instituteId,
			@RequestBody ScholarshipForm form) {
		try {
			Institute ins = nspService.getAnInstituteById(instituteId);
			Student stu = nspService.getAStudentById(studentId);

			form.setSchemeUid(schemeId);

			form.setInstituteObj(ins);
			form.setStudent(stu);

			nspService.applyAScholarshipForm(form);

			Status status = new Status();

			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Login Successful!");
			// loginStatus.setInstituteId(ins.getInstituteId());
			// loginStatus.setInsName(ins.getInstituteName());
			return status;

		} catch (NspServiceException e) {
			Status loginStatus = new Status();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}
	}

	@PostMapping("/docs-upload")
	public Status upload(DocumentUploadDto docDto) {
		String docUploadLocation = "d:/uploads/";
		String domicileCertificate = "domicileCertificate" + docDto.getFormId();
		String targetFile1 = docUploadLocation + domicileCertificate;
		String studentPic = "studentPic" + docDto.getFormId();
		String targetFile2 = docUploadLocation + studentPic;
		String prevYearMarksheet = "prevYearMarksheet" + docDto.getFormId();
		String targetFile3 = docUploadLocation + prevYearMarksheet;
		String class10Marksheet = "class10Marksheet" + docDto.getFormId();
		String targetFile4 = docUploadLocation + class10Marksheet;
		String class12Marksheet = "class12Marksheet" + docDto.getFormId();
		String targetFile5 = docUploadLocation + class12Marksheet;
		String aadharCard = "aadharCard" + docDto.getFormId();
		String targetFile6 = docUploadLocation + aadharCard;

		try {
			FileCopyUtils.copy(docDto.getDomicileCertificate().getInputStream(), new FileOutputStream(targetFile1));
			FileCopyUtils.copy(docDto.getStudentPic().getInputStream(), new FileOutputStream(targetFile2));
			FileCopyUtils.copy(docDto.getPrevYearMarksheet().getInputStream(), new FileOutputStream(targetFile3));
			FileCopyUtils.copy(docDto.getClass10Marksheet().getInputStream(), new FileOutputStream(targetFile4));
			FileCopyUtils.copy(docDto.getClass12Marksheet().getInputStream(), new FileOutputStream(targetFile5));
			FileCopyUtils.copy(docDto.getAadharCard().getInputStream(), new FileOutputStream(targetFile6));
		} catch (IOException e) {
			e.printStackTrace();
			Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}

		ScholarshipForm form = nspService.getAScholarshipFormById(docDto.getFormId());
		form.setDomicileCertificate(domicileCertificate);
		form.setStudentPic(studentPic);
		form.setPrevYearMarksheet(prevYearMarksheet);
		form.setClass10Marksheet(class10Marksheet);
		form.setClass12Marksheet(class12Marksheet);
		form.setAadharCard(aadharCard);
		nspService.updateAScholarshipForm(form);

		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Uploaded!");
		return status;
	}

	// forgot Password
	// checkFormStatus

}
