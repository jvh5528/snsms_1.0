package com.application.snsms.comment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.snsms.comment.dao.CommentDAO;
import com.application.snsms.comment.dto.CommentDTO;
import com.application.snsms.profile.dao.ProfileDAO;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private ProfileDAO profileDAO;

	@Override
	public void addComment(CommentDTO commentDTO) throws Exception {
		commentDAO.insertComment(commentDTO);
	}

	@Override
	public List<CommentDTO> getCommentList(int mediaCd) throws Exception {
		return commentDAO.selectCommentList(mediaCd);
	}
	@Override
	public List<CommentDTO> getCommentListRecent(int mediaCd) throws Exception {
		return commentDAO.selectCommentListRecent(mediaCd);
	}

	@Override
	public int getAllCommentCnt(int mediaCd) throws Exception {
		return commentDAO.selectOnePostCommentCnt(mediaCd);
	}

	@Override
	public List<CommentDTO> getMyCommentList(String username) throws Exception {

		List<Integer> myMediaCdList = profileDAO.selectMyMediaCdList(username);
		
		Map<String, Object> myCommentsMap = new HashMap<String, Object>();
		myCommentsMap.put("username"	 , username);
		myCommentsMap.put("myMediaCdList", myMediaCdList);
		
		return commentDAO.selectMyCommentList(myCommentsMap);
	}

}
