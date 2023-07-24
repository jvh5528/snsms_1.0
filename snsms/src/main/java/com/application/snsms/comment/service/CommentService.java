package com.application.snsms.comment.service;

import java.util.List;

import com.application.snsms.comment.dto.CommentDTO;

public interface CommentService {

	public int getAllCommentCnt(int mediaCd) throws Exception;
	public void addComment(CommentDTO commentDTO) throws Exception;
	
	public List<CommentDTO> getCommentList(int mediaCd) throws Exception;
	public List<CommentDTO> getCommentListRecent(int mediaCd) throws Exception;
	public List<CommentDTO> getMyCommentList(String username) throws Exception;
}
