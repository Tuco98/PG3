package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_schemes")
@NamedQuery(name = "fetchAllSchemes", query="select s from Scheme s")
public class Scheme {
	
	@Id
	@SequenceGenerator(name="schemeSeq",initialValue = 1001,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "schemeSeq")
	@Column(name = "scheme_uid")
	long schemeUId;
	
	@Column(name = "scheme_name")
	String schemeName;
	
	@Column(name = "scheme_ministry")
	String schemeMinistry;
	
	@Column(name = "scheme_course_level")
	String schemeCourseLevel;
	
	@Column(name = "scheme_10ec")
	double scheme10EC;
	
	@Column(name = "scheme_12ec")
	double scheme12EC;
	
	@Column(name = "scheme_caste")
	String schemeCaste;
	
	@Column(name = "scheme_age")
	int schemeAge;
	
	@Column(name = "scheme_gender")
	String schemeGender;
	
	@Column(name = "scheme_family_income")
	double schemeFamilyIncome;
	
	@Column(name = "scheme_disabled")
	String schemeForDisabled;
	
	@Column(name = "scheme_amount")
	long schemeAmount;
	
	@Column(name = "scheme_status")
	String schemeStatus;
	
	

	public long getSchemeUId() {
		return schemeUId;
	}

	public void setSchemeUId(long schemeUId) {
		this.schemeUId = schemeUId;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getSchemeMinistry() {
		return schemeMinistry;
	}

	public void setSchemeMinistry(String schemeMinistry) {
		this.schemeMinistry = schemeMinistry;
	}

	public String getSchemeCourseLevel() {
		return schemeCourseLevel;
	}

	public void setSchemeCourseLevel(String schemeCourseLevel) {
		this.schemeCourseLevel = schemeCourseLevel;
	}

	public double getScheme10EC() {
		return scheme10EC;
	}

	public void setScheme10EC(double scheme10ec) {
		scheme10EC = scheme10ec;
	}

	public double getScheme12EC() {
		return scheme12EC;
	}

	public void setScheme12EC(double scheme12ec) {
		scheme12EC = scheme12ec;
	}

	public String getSchemeCaste() {
		return schemeCaste;
	}

	public void setSchemeCaste(String schemeCaste) {
		this.schemeCaste = schemeCaste;
	}

	public int getSchemeAge() {
		return schemeAge;
	}

	public void setSchemeAge(int schemeAge) {
		this.schemeAge = schemeAge;
	}

	public String getSchemeGender() {
		return schemeGender;
	}

	public void setSchemeGender(String schemeGender) {
		this.schemeGender = schemeGender;
	}

	public double getSchemeFamilyIncome() {
		return schemeFamilyIncome;
	}

	public void setSchemeFamilyIncome(double schemeFamilyIncome) {
		this.schemeFamilyIncome = schemeFamilyIncome;
	}


	public long getSchemeAmount() {
		return schemeAmount;
	}

	public void setSchemeAmount(long schemeAmount) {
		this.schemeAmount = schemeAmount;
	}

	public String getSchemeForDisabled() {
		return schemeForDisabled;
	}

	public void setSchemeForDisabled(String schemeForDisabled) {
		this.schemeForDisabled = schemeForDisabled;
	}

	public String getSchemeStatus() {
		return schemeStatus;
	}

	public void setSchemeStatus(String schemeStatus) {
		this.schemeStatus = schemeStatus;
	}
	
	
}