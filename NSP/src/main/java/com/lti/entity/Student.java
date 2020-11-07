package com.lti.entity;

//added the dependency from form to student

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table( name = "tbl_students")
@NamedQuery(name = "fetchAllStudents", query="select st from Student st")
public class Student {
	@Id
	@Column(name = "student_aadhar_number")
	long studentAadharNumber;
	
	@Column(name = "student_name")
	String studentName;
	
	@Column(name = "student_gender")
	String studentGender; 
	
	@Column(name = "student_date_of_birth")
	LocalDate studentDateOfBirth;
	
	@Column(name = "student_mobile_number")
	String studentMobileNumber; 
	
	@Column(name = "student_email")
	String studentEmail;
	
	@Column(name = "student_state_of_domicile")
	String studentStateOfDomicile;
	
	@Column(name = "student_district")
	String studentDistrict; 
	
	@Column(name = "student_bank_name")
	String studentBankName;
	
	@Column(name = "student_bank_account_number")
	String studentBankAccountNumber;
	
	@Column(name = "student_ifsc_code")
	String studentIfcsCode; 
	
	@Column(name = "student_password")
	String studentPassword;
	
	@Column(name = "student_status")
	String studentStatus; //Not Approved/Approved/Rejected
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="institute_id")
	Institute institute;
	
	@OneToOne( mappedBy = "student")
	ScholarshipForm form;

	public long getStudentAadharNumber() {
		return studentAadharNumber;
	}

	public void setStudentAadharNumber(long studentAadharNumber) {
		this.studentAadharNumber = studentAadharNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentGender() {
		return studentGender;
	}

	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}

	public LocalDate getStudentDateOfBirth() {
		return studentDateOfBirth;
	}

	public void setStudentDateOfBirth(LocalDate studentDateOfBirth) {
		this.studentDateOfBirth = studentDateOfBirth;
	}

	public String getStudentMobileNumber() {
		return studentMobileNumber;
	}

	public void setStudentMobileNumber(String studentMobileNumber) {
		this.studentMobileNumber = studentMobileNumber;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentStateOfDomicile() {
		return studentStateOfDomicile;
	}

	public void setStudentStateOfDomicile(String studentStateOfDomicile) {
		this.studentStateOfDomicile = studentStateOfDomicile;
	}

	public String getStudentDistrict() {
		return studentDistrict;
	}

	public void setStudentDistrict(String studentDistrict) {
		this.studentDistrict = studentDistrict;
	}

	public String getStudentBankName() {
		return studentBankName;
	}

	public void setStudentBankName(String studentBankName) {
		this.studentBankName = studentBankName;
	}

	

	public String getStudentBankAccountNumber() {
		return studentBankAccountNumber;
	}

	public void setStudentBankAccountNumber(String studentBankAccountNumber) {
		this.studentBankAccountNumber = studentBankAccountNumber;
	}

	public String getStudentIfcsCode() {
		return studentIfcsCode;
	}

	public void setStudentIfcsCode(String studentIfcsCode) {
		this.studentIfcsCode = studentIfcsCode;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public String getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}

	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public ScholarshipForm getForm() {
		return form;
	}

	public void setForm(ScholarshipForm form) {
		this.form = form;
	}
	
	
	
}

/*Students_Table{
	Student_UID(Adhar No. verify from external resource in front end) "PK", long
	Student_Name(As per Marksheet), String
	Student_Gender, String
	Student_DOB(As per Martsheet), LocalDate
	Student_number(to verify from External resource in front end), String
	Student_Email, String
	Student_StateOfDomicile, String
	Student_District, String
	Student_InstituteCode(to be checked from Institute Table in front end) "FK", long
	Student_BankName, String
	Student_BankAccountNumber, String
	Student_IFSCcode, String
	Student_Password, String
	Student_Status String
}*/