package com.application.snsms.comment.dao;

import java.util.List;
import java.util.Map;

import com.application.snsms.comment.dto.CommentDTO;

public interface CommentDAO {

	public int selectOnePostCommentCnt(int mediaCd) throws Exception;
	public void insertComment(CommentDTO commentDTO) throws Exception;
	public List<CommentDTO> selectCommentList(int mediaCd) throws Exception;
	public List<CommentDTO> selectCommentListRecent(int mediaCd) throws Exception;
	public List<CommentDTO> selectMyCommentList(Map<String, Object>myCommentsMap) throws Exception;
}
