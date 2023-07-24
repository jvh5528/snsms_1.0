package com.application.snsms.comment.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CommentDTO {

	private int mediaCd;
	private String comment;
	private String commenter;
	private String commenterPic;
	private Date commentDt;
	
	public int getMediaCd() {
		return mediaCd;
	}
	public void setMediaCd(int mediaCd) {
		this.mediaCd = mediaCd;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCommenter() {
		return commenter;
	}
	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}
	public String getCommenterPic() {
		return commenterPic;
	}
	public void setCommenterPic(String commenterPic) {
		this.commenterPic = commenterPic;
	}
	public Date getCommentDt() {
		return commentDt;
	}
	public void setCommentDt(Date commentDt) {
		this.commentDt = commentDt;
	}
	@Override
	public String toString() {
		return "CommentDTO [mediaCd=" + mediaCd + ", comment=" + comment + ", commenter=" + commenter
				+ ", commenterPic=" + commenterPic + ", commentDt=" + commentDt + "]";
	}
	

	
	
}
