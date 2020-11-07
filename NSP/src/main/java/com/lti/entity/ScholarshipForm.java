package com.lti.entity;

//added the dependency form student to form

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
//@Table( name = "form_demo")
@Table( name = "tbl_scholarship_form")
@NamedQuery(name = "fetchAllForms", query="select sf from ScholarshipForm sf")
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
	double familyAnnualIncome;
	
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
	
	
	@OneToOne
	@JoinColumn( name = "student_aadhar_number")
	Student student;
	
	@ManyToOne
	@JoinColumn( name = "institute_id")
	Institute instituteObj;
	
//	@OneToOne
//	@JoinColumn( name = "nodal_uid")
//	Nodal nodal;
	
	@OneToOne
	@JoinColumn( name = "scheme_uid")
	Scheme scheme;
	
	
	String instituteName;
	String presentCourse;
	LocalDate presentCourseYear;
	String modeOfStudy;
	LocalDate classStartDate;
	String universityName;
	String previousCourse;
	LocalDate previousPassingYear;
	double previousClassPercentage;
	
	String class10RollNumber;
	String class10BoardName;
	LocalDate class10PassingYear;
	double class10Percentage;
	
	String class12RollNumber;
	String class12BoardName;
	LocalDate class12PassingYear;
	double class12Percentage;
	
	double admissionFee;
	double tutionFee;
	double otherFee;
	
	boolean isDisabled;
	String typeOfDisabilty;
	double percentageOfDisability;
	String maritalStatus;
	String parentProfession;

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

	public double getFamilyAnnualIncome() {
		return familyAnnualIncome;
	}

	public void setFamilyAnnualIncome(double familyAnnualIncome) {
		this.familyAnnualIncome = familyAnnualIncome;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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

	public String getInstituteVerificationStatus() {
		return instituteVerificationStatus;
	}

	public void setInstituteVerificationStatus(String instituteVerificationStatus) {
		this.instituteVerificationStatus = instituteVerificationStatus;
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

	public Scheme getScheme() {
		return scheme;
	}

	public void setScheme(Scheme scheme) {
		this.scheme = scheme;
	}

	public Institute getInstitute() {
		return instituteObj;
	}

	public void setInstitute(Institute institute) {
		this.instituteObj = institute;
	}

	public Institute getInstituteObj() {
		return instituteObj;
	}

	public void setInstituteObj(Institute instituteObj) {
		this.instituteObj = instituteObj;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getPresentCourse() {
		return presentCourse;
	}

	public void setPresentCourse(String presentCourse) {
		this.presentCourse = presentCourse;
	}

	public LocalDate getPresentCourseYear() {
		return presentCourseYear;
	}

	public void setPresentCourseYear(LocalDate presentCourseYear) {
		this.presentCourseYear = presentCourseYear;
	}

	public String getModeOfStudy() {
		return modeOfStudy;
	}

	public void setModeOfStudy(String modeOfStudy) {
		this.modeOfStudy = modeOfStudy;
	}

	public LocalDate getClassStartDate() {
		return classStartDate;
	}

	public void setClassStartDate(LocalDate classStartDate) {
		this.classStartDate = classStartDate;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getPreviousCourse() {
		return previousCourse;
	}

	public void setPreviousCourse(String previousCourse) {
		this.previousCourse = previousCourse;
	}

	public LocalDate getPreviousPassingYear() {
		return previousPassingYear;
	}

	public void setPreviousPassingYear(LocalDate previousPassingYear) {
		this.previousPassingYear = previousPassingYear;
	}

	public double getPreviousClassPercentage() {
		return previousClassPercentage;
	}

	public void setPreviousClassPercentage(double previousClassPercentage) {
		this.previousClassPercentage = previousClassPercentage;
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

	public LocalDate getClass10PassingYear() {
		return class10PassingYear;
	}

	public void setClass10PassingYear(LocalDate class10PassingYear) {
		this.class10PassingYear = class10PassingYear;
	}

	public double getClass10Percentage() {
		return class10Percentage;
	}

	public void setClass10Percentage(double class10Percentage) {
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

	public LocalDate getClass12PassingYear() {
		return class12PassingYear;
	}

	public void setClass12PassingYear(LocalDate class12PassingYear) {
		this.class12PassingYear = class12PassingYear;
	}

	public double getClass12Percentage() {
		return class12Percentage;
	}

	public void setClass12Percentage(double class12Percentage) {
		this.class12Percentage = class12Percentage;
	}

	public double getAdmissionFee() {
		return admissionFee;
	}

	public void setAdmissionFee(double admissionFee) {
		this.admissionFee = admissionFee;
	}

	public double getTutionFee() {
		return tutionFee;
	}

	public void setTutionFee(double tutionFee) {
		this.tutionFee = tutionFee;
	}

	public double getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(double otherFee) {
		this.otherFee = otherFee;
	}

	public boolean isDisabled() {
		return isDisabled;
	}

	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	public String getTypeOfDisabilty() {
		return typeOfDisabilty;
	}

	public void setTypeOfDisabilty(String typeOfDisabilty) {
		this.typeOfDisabilty = typeOfDisabilty;
	}

	public double getPercentageOfDisability() {
		return percentageOfDisability;
	}

	public void setPercentageOfDisability(double percentageOfDisability) {
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
	
	
	
	
	

}
