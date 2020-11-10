package com.lti.dto;

import org.springframework.web.multipart.MultipartFile;

public class InstituteDocUploadDto {

	long instituteId;
	MultipartFile registrationCertificate;
	MultipartFile affiliationCertificate;
	public long getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(long instituteId) {
		this.instituteId = instituteId;
	}
	public MultipartFile getRegistrationCertificate() {
		return registrationCertificate;
	}
	public void setRegistrationCertificate(MultipartFile registrationCertificate) {
		this.registrationCertificate = registrationCertificate;
	}
	public MultipartFile getAffiliationCertificate() {
		return affiliationCertificate;
	}
	public void setAffiliationCertificate(MultipartFile affiliationCertificate) {
		this.affiliationCertificate = affiliationCertificate;
	}
	
}
