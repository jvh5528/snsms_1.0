package com.application.snsms.follow.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class FollowingDTO {

	private int followingCd;
	private String username;
	private String FollowingUsername;
	private String FollowingName;
	private String FollowingPic;
	private Date followDt;
	
	public int getFollowingCd() {
		return followingCd;
	}
	public void setFollowingCd(int followingCd) {
		this.followingCd = followingCd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFollowingUsername() {
		return FollowingUsername;
	}
	public void setFollowingUsername(String followingUsername) {
		FollowingUsername = followingUsername;
	}
	public String getFollowingName() {
		return FollowingName;
	}
	public void setFollowingName(String followingName) {
		FollowingName = followingName;
	}
	public String getFollowingPic() {
		return FollowingPic;
	}
	public void setFollowingPic(String followingPic) {
		FollowingPic = followingPic;
	}
	public Date getFollowDt() {
		return followDt;
	}
	public void setFollowDt(Date followDt) {
		this.followDt = followDt;
	}
	@Override
	public String toString() {
		return "FollowingDTO [followingCd=" + followingCd + ", username=" + username + ", FollowingUsername="
				+ FollowingUsername + ", FollowingName=" + FollowingName + ", FollowingPic=" + FollowingPic
				+ ", followDt=" + followDt + "]";
	}
	
	
}
