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
	MultipartFile bankPassbook;
	MultipartFile instituteIdCard;
	MultipartFile casteIncomeCertificate;
	MultipartFile feeReceiptOfCurrentYear;
	public MultipartFile getBankPassbook() {
		return bankPassbook;
	}
	public void setBankPassbook(MultipartFile bankPassbook) {
		this.bankPassbook = bankPassbook;
	}
	public MultipartFile getInstituteIdCard() {
		return instituteIdCard;
	}
	public void setInstituteIdCard(MultipartFile instituteIdCard) {
		this.instituteIdCard = instituteIdCard;
	}
	public MultipartFile getCasteIncomeCertificate() {
		return casteIncomeCertificate;
	}
	public void setCasteIncomeCertificate(MultipartFile casteIncomeCertificate) {
		this.casteIncomeCertificate = casteIncomeCertificate;
	}
	public MultipartFile getFeeReceiptOfCurrentYear() {
		return feeReceiptOfCurrentYear;
	}
	public void setFeeReceiptOfCurrentYear(MultipartFile feeReceiptOfCurrentYear) {
		this.feeReceiptOfCurrentYear = feeReceiptOfCurrentYear;
	}
	
	
	
	
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
