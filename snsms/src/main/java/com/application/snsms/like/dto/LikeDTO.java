package com.application.snsms.like.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class LikeDTO {

	private int mediaCd;
	private String likedbyUsername;
	private String likedbyProfilepic;
	private Date likeDt;
	
	public int getMediaCd() {
		return mediaCd;
	}
	public void setMediaCd(int mediaCd) {
		this.mediaCd = mediaCd;
	}
	public String getLikedbyUsername() {
		return likedbyUsername;
	}
	public void setLikedbyUsername(String likedbyUsername) {
		this.likedbyUsername = likedbyUsername;
	}
	public String getLikedbyProfilepic() {
		return likedbyProfilepic;
	}
	public void setLikedbyProfilepic(String likedbyProfilepic) {
		this.likedbyProfilepic = likedbyProfilepic;
	}
	public Date getLikeDt() {
		return likeDt;
	}
	public void setLikeDt(Date likeDt) {
		this.likeDt = likeDt;
	}
	@Override
	public String toString() {
		return "LikeDTO [mediaCd=" + mediaCd + ", likedbyUsername=" + likedbyUsername + ", likedbyProfilepic="
				+ likedbyProfilepic + ", likeDt=" + likeDt + "]";
	}
	
	
}
