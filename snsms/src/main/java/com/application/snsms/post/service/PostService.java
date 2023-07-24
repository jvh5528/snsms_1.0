package com.application.snsms.post.service;

import java.util.List;
import java.util.Map;

import com.application.snsms.post.dto.PostDTO;

public interface PostService {

	public List<PostDTO> getPostList(String username) throws Exception;
	public List<PostDTO> getHomeMediaList(Map<String, Object> followingMap) throws Exception;
	public int getAllFollowingPostCnt(Map<String, Object>followingMap) throws Exception;
	public List<PostDTO> getExploreMediaList() throws Exception;
	public void uploadPost(PostDTO postDTO) throws Exception;
	public void editPost(PostDTO postDTO) throws Exception;
	public void deletePost(int mediaCd) throws Exception;
	public PostDTO getPostDetail(int mediaCd) throws Exception;
	public PostDTO getEditPost(int mediaCd) throws Exception;
	public String getDeletePost(int mediaCd) throws Exception;
	public String checkIfProduct(int mediaCd) throws Exception;
	public int getPostCnt(String username) throws Exception;
}
