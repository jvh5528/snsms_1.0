package com.application.snsms.profile.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ProfileDTO {

	private String username;
	private String name;
	private String bio;
	private String profilePic;
	private String gender;
	private String customGender;
	private Date modifyDt;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCustomGender() {
		return customGender;
	}
	public void setCustomGender(String customGender) {
		this.customGender = customGender;
	}
	public Date getModifyDt() {
		return modifyDt;
	}
	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}
	@Override
	public String toString() {
		return "ProfileDTO [username=" + username + ", name=" + name + ", bio=" + bio + ", profilePic=" + profilePic
				+ ", gender=" + gender + ", customGender=" + customGender + ", modifyDt=" + modifyDt + "]";
	}
	
	
	
}
