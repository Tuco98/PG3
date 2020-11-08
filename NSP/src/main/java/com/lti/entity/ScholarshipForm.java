package com.lti.entity;

//added the dependency form student to form

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
//@Table( name = "form_demo")
@Table( name = "tbl_scholarship_form")
@NamedQuery(name = "fetchAllForms", query="select sf from ScholarshipForm sf")
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","student"})
public class ScholarshipForm {
	
	@Id
	@SequenceGenerator(name = "formSeq", initialValue = 1001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "formSeq")
	@Column(name = "form_id")
	long formId;
	
	@Column(name = "aadhar_number")
	long aadharNumber;
	
	@Column(name = "religion")
	String religion;
	
	@Column(name = "category")
	String category;
	
	@Column(name = "father_name")
	String fatherName;
	
	@Column(name = "mother_name")
	String motherName;
	
	@Column(name = "family_annual_income")
	String familyAnnualIncome;
	
	@Column(name = "inst_verification_status")
	String instituteVerificationStatus; //Not Approved/Approved/Rejected
	
	@Column(name = "nodal_verification_status")
	String nodalVerificationStatus; //Not Approved/Approved/Rejected
	
	@Column(name = "ministry_verification_status")
	String ministryVerificationStatus;//Not Approved/Approved/Rejected
	
	@Column( name = "date_of_approval")
	LocalDate dateOfApproval;
	
	@Column( name = "status")
	String status;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn( name = "student_aadhar_number")
	Student student;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( name = "institute_id")
	Institute instituteObj;
	
//	@OneToOne
//	@JoinColumn( name = "nodal_uid")
//	Nodal nodal;
	
	@Column(name = "scheme_uid")
	long schemeUid;

	String class10RollNumber;
	String class10BoardName;
	String class10PassingYear;
	String class10Percentage;
	
	String class12RollNumber;
	String class12BoardName;
	String class12PassingYear;
	String class12Percentage;
	
	String admissionFee;
	String tutionFee;
	String otherFee;
	
	String isDisabled;
	String typeOfDisabilty;
	String percentageOfDisability;
	String maritalStatus;
	String parentProfession;
	
	long instituteCode;
	
	

	public long getInstituteCode() {
		return instituteCode;
	}

	public void setInstituteCode(long instituteCode) {
		this.instituteCode = instituteCode;
	}

	//Documents
	String domicileCertificate;
	String studentPic;
	String prevYearMarksheet;
	String class10Marksheet;
	String class12Marksheet;
	String aadharCard;
	

	public long getFormId() {
		return formId;
	}

	public void setFormId(long formId) {
		this.formId = formId;
	}

	public long getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	

	public String getFamilyAnnualIncome() {
		return familyAnnualIncome;
	}

	public void setFamilyAnnualIncome(String familyAnnualIncome) {
		this.familyAnnualIncome = familyAnnualIncome;
	}

	public String getInstituteVerificationStatus() {
		return instituteVerificationStatus;
	}

	public void setInstituteVerificationStatus(String instituteVerificationStatus) {
		this.instituteVerificationStatus = instituteVerificationStatus;
	}

	public String getNodalVerificationStatus() {
		return nodalVerificationStatus;
	}

	public void setNodalVerificationStatus(String nodalVerificationStatus) {
		this.nodalVerificationStatus = nodalVerificationStatus;
	}

	public String getMinistryVerificationStatus() {
		return ministryVerificationStatus;
	}

	public void setMinistryVerificationStatus(String ministryVerificationStatus) {
		this.ministryVerificationStatus = ministryVerificationStatus;
	}

	public LocalDate getDateOfApproval() {
		return dateOfApproval;
	}

	public void setDateOfApproval(LocalDate dateOfApproval) {
		this.dateOfApproval = dateOfApproval;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonIgnore
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@JsonIgnore
	public Institute getInstituteObj() {
		return instituteObj;
	}

	public void setInstituteObj(Institute instituteObj) {
		this.instituteObj = instituteObj;
	}

	

	public String getClass10RollNumber() {
		return class10RollNumber;
	}

	public void setClass10RollNumber(String class10RollNumber) {
		this.class10RollNumber = class10RollNumber;
	}

	public String getClass10BoardName() {
		return class10BoardName;
	}

	public void setClass10BoardName(String class10BoardName) {
		this.class10BoardName = class10BoardName;
	}

	public String getClass10PassingYear() {
		return class10PassingYear;
	}

	public void setClass10PassingYear(String class10PassingYear) {
		this.class10PassingYear = class10PassingYear;
	}

	public String getClass10Percentage() {
		return class10Percentage;
	}

	public void setClass10Percentage(String class10Percentage) {
		this.class10Percentage = class10Percentage;
	}

	public String getClass12RollNumber() {
		return class12RollNumber;
	}

	public void setClass12RollNumber(String class12RollNumber) {
		this.class12RollNumber = class12RollNumber;
	}

	public String getClass12BoardName() {
		return class12BoardName;
	}

	public void setClass12BoardName(String class12BoardName) {
		this.class12BoardName = class12BoardName;
	}

	public String getClass12PassingYear() {
		return class12PassingYear;
	}

	public void setClass12PassingYear(String class12PassingYear) {
		this.class12PassingYear = class12PassingYear;
	}

	public String getClass12Percentage() {
		return class12Percentage;
	}

	public void setClass12Percentage(String class12Percentage) {
		this.class12Percentage = class12Percentage;
	}

	public String getAdmissionFee() {
		return admissionFee;
	}

	public void setAdmissionFee(String admissionFee) {
		this.admissionFee = admissionFee;
	}

	public String getTutionFee() {
		return tutionFee;
	}

	public void setTutionFee(String tutionFee) {
		this.tutionFee = tutionFee;
	}

	public String getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
	}

	public String getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(String isDisabled) {
		this.isDisabled = isDisabled;
	}

	public String getTypeOfDisabilty() {
		return typeOfDisabilty;
	}

	public void setTypeOfDisabilty(String typeOfDisabilty) {
		this.typeOfDisabilty = typeOfDisabilty;
	}

	public String getPercentageOfDisability() {
		return percentageOfDisability;
	}

	public void setPercentageOfDisability(String percentageOfDisability) {
		this.percentageOfDisability = percentageOfDisability;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getParentProfession() {
		return parentProfession;
	}

	public void setParentProfession(String parentProfession) {
		this.parentProfession = parentProfession;
	}

	public String getDomicileCertificate() {
		return domicileCertificate;
	}

	public void setDomicileCertificate(String domicileCertificate) {
		this.domicileCertificate = domicileCertificate;
	}

	public String getStudentPic() {
		return studentPic;
	}

	public void setStudentPic(String studentPic) {
		this.studentPic = studentPic;
	}

	public String getPrevYearMarksheet() {
		return prevYearMarksheet;
	}

	public void setPrevYearMarksheet(String prevYearMarksheet) {
		this.prevYearMarksheet = prevYearMarksheet;
	}

	public String getClass10Marksheet() {
		return class10Marksheet;
	}

	public void setClass10Marksheet(String class10Marksheet) {
		this.class10Marksheet = class10Marksheet;
	}

	public String getClass12Marksheet() {
		return class12Marksheet;
	}

	public void setClass12Marksheet(String class12Marksheet) {
		this.class12Marksheet = class12Marksheet;
	}

	public String getAadharCard() {
		return aadharCard;
	}

	public void setAadharCard(String aadharCard) {
		this.aadharCard = aadharCard;
	}

	public long getSchemeUid() {
		return schemeUid;
	}

	public void setSchemeUid(long schemeUid) {
		this.schemeUid = schemeUid;
	}
	
	
	
	
	

}
