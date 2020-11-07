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


}
