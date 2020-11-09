package com.lti.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
			@RequestParam("studentId") long studentId, @RequestParam("instituteId") String instituteId,
			@RequestBody ScholarshipForm form) {
		try {
			Institute ins = nspService.getAnInstituteById(Long.parseLong(instituteId));
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
	@CrossOrigin
	public Status upload(DocumentUploadDto docDto) {
		String docUploadLocation = "D:/uploads/";
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
		String bankPassbook = "bankPassbook" +docDto.getBankPassbook();
		String targetFile7 = docUploadLocation + bankPassbook;
		String instituteIdCard = "instituteIdCard" + docDto.getInstituteIdCard();
		String targetFile8 = docUploadLocation +instituteIdCard;
		String casteIncomeCertificate = "casteIncomeCertificate" + docDto.getCasteIncomeCertificate();
		String targetFile9 = docUploadLocation + casteIncomeCertificate;
		String feeReceiptOfCurrentYear = "feeReceiptOfCurrentYear" + docDto.getFeeReceiptOfCurrentYear();
		String targetFile10 = docUploadLocation + feeReceiptOfCurrentYear;

		try {
			FileCopyUtils.copy(docDto.getDomicileCertificate().getInputStream(), new FileOutputStream(targetFile1));
			FileCopyUtils.copy(docDto.getStudentPic().getInputStream(), new FileOutputStream(targetFile2));
			FileCopyUtils.copy(docDto.getPrevYearMarksheet().getInputStream(), new FileOutputStream(targetFile3));
			FileCopyUtils.copy(docDto.getClass10Marksheet().getInputStream(), new FileOutputStream(targetFile4));
			FileCopyUtils.copy(docDto.getClass12Marksheet().getInputStream(), new FileOutputStream(targetFile5));
			FileCopyUtils.copy(docDto.getAadharCard().getInputStream(), new FileOutputStream(targetFile6));
			FileCopyUtils.copy(docDto.getBankPassbook().getInputStream(), new FileOutputStream(targetFile7));
			FileCopyUtils.copy(docDto.getInstituteIdCard().getInputStream(), new FileOutputStream(targetFile8));
			FileCopyUtils.copy(docDto.getCasteIncomeCertificate().getInputStream(), new FileOutputStream(targetFile9));
			FileCopyUtils.copy(docDto.getFeeReceiptOfCurrentYear().getInputStream(), new FileOutputStream(targetFile10));
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
		form.setBankPassbook(bankPassbook);
		form.setInstituteIdCard(instituteIdCard);
		form.setCasteIncomeCertificate(casteIncomeCertificate);
		form.setFeeReceiptOfCurrentYear(feeReceiptOfCurrentYear);
		nspService.updateAScholarshipForm(form);

		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Uploaded!");
		return status;
	}
	
	@GetMapping("/fetchStudentProfile")
    public Student fetchStudentProfile(@RequestParam("studentId") long studentId, HttpServletRequest request) {
        Student stu = nspService.getAStudentById(studentId);
        return stu;
    }
	
	@GetMapping("/getInstituteId")
	public long getInstituteIdofStudent(@RequestParam("studentId") long studentId, HttpServletRequest request) {
        
        return nspService.findInstituteOfStudent(studentId);
    }
	
	@GetMapping("/showStatus")
	public String showFormStatusOfStudent(@RequestParam("studentId") long studentId) {
		return nspService.getAStudentById(studentId).getForm().getStatus();
	}
	
	@PostMapping("/studentForgotPassword")
	public String studentForgotPassword(@RequestParam("studentId") long studentId,@RequestParam("email") String email) {
		try {
			return nspService.studentForgotPassword(studentId, email);
		}
		catch (NspServiceException e) {
			return e.getMessage();
		}
	}

	// forgot Password
	// checkFormStatus
	
	@GetMapping("/findFormByStudentId")
	public long findScholarshipFormByStudentId(@RequestParam("studentId") long studentId, HttpServletRequest request) {
		return nspService.findFormByStudentId(studentId);
	}
	
	

}
