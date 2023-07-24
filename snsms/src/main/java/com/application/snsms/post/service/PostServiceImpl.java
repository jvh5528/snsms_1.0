package com.application.snsms.post.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.snsms.post.dao.PostDAO;
import com.application.snsms.post.dto.PostDTO;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDAO postDAO;
	
	@Override
	public void uploadPost(PostDTO postDTO) throws Exception {
		postDAO.insertPost(postDTO);
	}
	
	@Override
	public PostDTO getPostDetail(int mediaCd) throws Exception {
		return postDAO.selectPostDetail(mediaCd);
	}

	@Override
	public List<PostDTO> getPostList(String username) throws Exception {
		return postDAO.selectPostList(username);
	}

	@Override
	public PostDTO getEditPost(int mediaCd) throws Exception {
		return postDAO.selectOneEditPost(mediaCd);
	}

	@Override
	public void editPost(PostDTO postDTO) throws Exception {
		postDAO.updatePost(postDTO);
	}
	
	@Override
	public String getDeletePost(int mediaCd) throws Exception {
		return postDAO.selectOneDeletePost(mediaCd);
	}

	@Override
	public void deletePost(int mediaCd) throws Exception {
		postDAO.deletePost(mediaCd);
	}

	@Override
	public int getPostCnt(String username) throws Exception {
		return postDAO.selectOneMemberPostCnt(username);
	}

	@Override
	public List<PostDTO> getHomeMediaList(Map<String, Object> followingMap) throws Exception {		
		return postDAO.selectListHome(followingMap);
	}
	
	@Override
	public int getAllFollowingPostCnt(Map<String, Object> followingMap) throws Exception {
		return postDAO.selectMyFollowingPostCnt(followingMap);
	}

	@Override
	public List<PostDTO> getExploreMediaList() throws Exception {
		return postDAO.selectListExploreMediaList();
	}

	@Override
	public String checkIfProduct(int mediaCd) throws Exception {
		return postDAO.selectProductCheck(mediaCd);
	}

}
