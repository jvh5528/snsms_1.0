package com.application.snsms.member.dto;

import java.util.Date;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class MemberDTO {

	private String username;
	private String passwd;
	private String name;
	private String email;
	private String userStatus;
	private Date passwdDt;
	private Date enrollDt;
	
	
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getEnrollDt() {
		return enrollDt;
	}
	public void setEnrollDt(Date enrollDt) {
		this.enrollDt = enrollDt;
	}
	
	public Date getPasswdDt() {
		return passwdDt;
	}
	public void setPasswdDt(Date passwdDt) {
		this.passwdDt = passwdDt;
	}
	@Override
	public String toString() {
		return "MemberDTO [username=" + username + ", passwd=" + passwd + ", name=" + name + ", email=" + email
				+ ", userStatus=" + userStatus + ", passwdDt=" + passwdDt + ", enrollDt=" + enrollDt + "]";
	}
	


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDTO member = (MemberDTO) o;
        return Objects.equals(username, member.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }


}
