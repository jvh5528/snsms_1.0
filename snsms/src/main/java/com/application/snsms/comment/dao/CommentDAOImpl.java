package com.application.snsms.comment.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.snsms.comment.dto.CommentDTO;

@Repository
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertComment(CommentDTO commentDTO) throws Exception {
		sqlSession.insert("commentMapper.insertComment", commentDTO);
	}

	@Override
	public List<CommentDTO> selectCommentList(int mediaCd) throws Exception {
		return sqlSession.selectList("commentMapper.selectCommentList", mediaCd);
	}

	@Override
	public int selectOnePostCommentCnt(int mediaCd) throws Exception {
		return sqlSession.selectOne("commentMapper.selectOnePostCommentCnt", mediaCd);
	}

	@Override
	public List<CommentDTO> selectCommentListRecent(int mediaCd) throws Exception {
		return sqlSession.selectList("commentMapper.selectCommentListRecent", mediaCd);
	}

	@Override
	public List<CommentDTO> selectMyCommentList(Map<String, Object> myCommentsMap) throws Exception {
		return sqlSession.selectList("commentMapper.selectMyCommentList", myCommentsMap);
	}
}
