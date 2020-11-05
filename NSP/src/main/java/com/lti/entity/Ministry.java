package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_ministry")
public class Ministry {
	
	@Id
	@GeneratedValue
	@Column(name="MinistryUID")
	int ministryUid;
	
	@Column(name="MinistryPassword")
	String ministryPassword;
	
	@Column(name="MinistryState")
	String ministryState;
	
	@Column(name="MinistryStatus")
	Boolean ministryStatus = true;

	public int getMinistryUid() {
		return ministryUid;
	}

	public void setMinistryUid(int ministryUid) {
		this.ministryUid = ministryUid;
	}

	public String getMinistryPassword() {
		return ministryPassword;
	}

	public void setMinistryPassword(String ministryPassword) {
		this.ministryPassword = ministryPassword;
	}

	public String getMinistryState() {
		return ministryState;
	}

	public void setMinistryState(String ministryState) {
		this.ministryState = ministryState;
	}

	public Boolean getMinistryStatus() {
		return ministryStatus;
	}

	public void setMinistryStatus(Boolean ministryStatus) {
		this.ministryStatus = ministryStatus;
	}
	
	
	
}
