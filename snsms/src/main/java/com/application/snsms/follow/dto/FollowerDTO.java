package com.application.snsms.follow.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class FollowerDTO {

	private int followerCd;
	private String followUsername;
	private String followerUsername;
	private String followerName;
	private String followerPic;
	private Date followDt;
	public int getFollowerCd() {
		return followerCd;
	}
	public void setFollowerCd(int followerCd) {
		this.followerCd = followerCd;
	}
	public String getFollowUsername() {
		return followUsername;
	}
	public void setFollowUsername(String followUsername) {
		this.followUsername = followUsername;
	}
	public String getFollowerUsername() {
		return followerUsername;
	}
	public void setFollowerUsername(String followerUsername) {
		this.followerUsername = followerUsername;
	}
	public String getFollowerName() {
		return followerName;
	}
	public void setFollowerName(String followerName) {
		this.followerName = followerName;
	}
	public String getFollowerPic() {
		return followerPic;
	}
	public void setFollowerPic(String followerPic) {
		this.followerPic = followerPic;
	}
	public Date getFollowDt() {
		return followDt;
	}
	public void setFollowDt(Date followDt) {
		this.followDt = followDt;
	}
	@Override
	public String toString() {
		return "FollowerDTO [followerCd=" + followerCd + ", followUsername=" + followUsername + ", followerUsername="
				+ followerUsername + ", followerName=" + followerName + ", followerPic=" + followerPic + ", followDt="
				+ followDt + "]";
	}
	
	
}
