package com.lti.dto;

import org.springframework.web.multipart.MultipartFile;

public class DocumentUploadDto {
	
	long formId;
	MultipartFile domicileCertificate;
	MultipartFile studentPic;
	MultipartFile prevYearMarksheet;
	MultipartFile class10Marksheet;
	MultipartFile class12Marksheet;
	MultipartFile aadharCard;
	public long getFormId() {
		return formId;
	}
	public void setFormId(long formId) {
		this.formId = formId;
	}
	public MultipartFile getDomicileCertificate() {
		return domicileCertificate;
	}
	public void setDomicileCertificate(MultipartFile domicileCertificate) {
		this.domicileCertificate = domicileCertificate;
	}
	public MultipartFile getStudentPic() {
		return studentPic;
	}
	public void setStudentPic(MultipartFile studentPic) {
		this.studentPic = studentPic;
	}
	public MultipartFile getPrevYearMarksheet() {
		return prevYearMarksheet;
	}
	public void setPrevYearMarksheet(MultipartFile prevYearMarksheet) {
		this.prevYearMarksheet = prevYearMarksheet;
	}
	public MultipartFile getClass10Marksheet() {
		return class10Marksheet;
	}
	public void setClass10Marksheet(MultipartFile class10Marksheet) {
		this.class10Marksheet = class10Marksheet;
	}
	public MultipartFile getClass12Marksheet() {
		return class12Marksheet;
	}
	public void setClass12Marksheet(MultipartFile class12Marksheet) {
		this.class12Marksheet = class12Marksheet;
	}
	public MultipartFile getAadharCard() {
		return aadharCard;
	}
	public void setAadharCard(MultipartFile aadharCard) {
		this.aadharCard = aadharCard;
	}
	
	

}
