package com.application.snsms.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.snsms.post.dto.PostDTO;

@Repository
public class PostDAOImpl implements PostDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertPost(PostDTO postDTO) throws Exception {
		sqlSession.insert("postMapper.insertPost", postDTO);
	}
	
	@Override
	public PostDTO selectPostDetail(int mediaCd) throws Exception {
		return sqlSession.selectOne("postMapper.selectPostDetail", mediaCd);
	}

	@Override
	public List<PostDTO> selectPostList(String username) throws Exception {
		return sqlSession.selectList("postMapper.selectPostList", username);
	}

	@Override
	public PostDTO selectOneEditPost(int mediaCd) throws Exception {
		return sqlSession.selectOne("postMapper.selectOneEditPost", mediaCd);
	}

	@Override
	public void updatePost(PostDTO postDTO) throws Exception {
		sqlSession.update("postMapper.updatePost", postDTO);
	}

	@Override
	public String selectOneDeletePost(int mediaCd) throws Exception {
		return sqlSession.selectOne("postMapper.selectOneDeletePost", mediaCd);
	}
	
	@Override
	public void deletePost(int mediaCd) throws Exception {
		sqlSession.delete("postMapper.deletePost", mediaCd);
	}

	@Override
	public int selectOneMemberPostCnt(String username) throws Exception {
		return sqlSession.selectOne("postMapper.selectOneMemberPostCnt", username);
	}

	@Override
	public List<PostDTO> selectListHome(Map<String, Object> followingMap) throws Exception {
		return sqlSession.selectList("postMapper.selectListHome", followingMap);
	}
	
	@Override
	public int selectMyFollowingPostCnt(Map<String, Object> followinMgMap) throws Exception {
		return sqlSession.selectOne("postMapper.selectMyFollowingPostCnt" , followinMgMap);
	}

	@Override
	public List<PostDTO> selectListExploreMediaList() throws Exception {
		return sqlSession.selectList("postMapper.selectListExploreMediaList");
	}

	@Override
	public String selectProductCheck(int mediaCd) throws Exception {
		return sqlSession.selectOne("postMapper.selectProductCheck", mediaCd);
	}

}
