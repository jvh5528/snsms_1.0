package com.application.snsms.like.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.snsms.like.dto.LikeDTO;

@Repository
public class LikeDAOImpl implements LikeDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public LikeDTO selectOneDuplicatedLike(LikeDTO likeDTO) throws Exception {
		return sqlSession.selectOne("likeMapper.selectOneDuplicatedLike", likeDTO);
	}
	
	@Override
	public String likeYn(Map<String, Object>likeYnMap) throws Exception {
		return sqlSession.selectOne("likeMapper.likeYn", likeYnMap);
	}
	
	@Override
	public void insertLike(LikeDTO likeDTO) throws Exception {
		sqlSession.insert("likeMapper.insertLike", likeDTO);
	}

	@Override
	public int selectOnePostLikesCnt(int mediaCd) throws Exception {
		return sqlSession.selectOne("likeMapper.selectOnePostLikesCnt", mediaCd);
	}

	@Override
	public List<LikeDTO> selectLikesList(int mediaCd) throws Exception {
		return sqlSession.selectList("likeMapper.selectLikesList", mediaCd);
	}

	@Override
	public void deleteLike(LikeDTO likeDTO) throws Exception {
		sqlSession.delete("likeMapper.deleteLike", likeDTO);
	}

	@Override
	public List<LikeDTO> selectMyLikesList(Map<String, Object> myLikesMap) throws Exception {
		return sqlSession.selectList("likeMapper.selectMyLikesList", myLikesMap);
	}

	
}
