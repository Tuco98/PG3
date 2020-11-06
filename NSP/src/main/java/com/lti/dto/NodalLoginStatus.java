package com.lti.dto;

public class NodalLoginStatus extends Status {
	int nodalId;
	String nodalName;
	public int getNodalId() {
		return nodalId;
	}
	public void setNodalId(int nodalId) {
		this.nodalId = nodalId;
	}
	public String getNodalName() {
		return nodalName;
	}
	public void setNodalName(String nodalName) {
		this.nodalName = nodalName;
	}
}
