package com.application.snsms.admin.member.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class AdminDTO {

	private String adminId;
	private String passwd;
	private Date joinDt;
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Date getJoinDt() {
		return joinDt;
	}
	public void setJoinDt(Date joinDt) {
		this.joinDt = joinDt;
	}
}
