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
	@Column(name="MinistryUID")
	String ministryUid;
	
	@Column(name="MinistryPassword")
	String ministryPassword;

	public String getMinistryUid() {
		return ministryUid;
	}

	public void setMinistryUid(String ministryUid) {
		this.ministryUid = ministryUid;
	}

	public String getMinistryPassword() {
		return ministryPassword;
	}

	public void setMinistryPassword(String ministryPassword) {
		this.ministryPassword = ministryPassword;
	}


}
