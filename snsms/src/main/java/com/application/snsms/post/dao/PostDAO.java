package com.application.snsms.post.dao;

import java.util.List;
import java.util.Map;

import com.application.snsms.post.dto.PostDTO;

public interface PostDAO {

	public List<PostDTO> selectPostList(String username) throws Exception;
	public List<PostDTO> selectListHome(Map<String, Object> followinMgMap) throws Exception;
	public int selectMyFollowingPostCnt(Map<String, Object> followinMgMap) throws Exception;
	public List<PostDTO> selectListExploreMediaList() throws Exception;
	public void insertPost(PostDTO postDTO) throws Exception;
	public void updatePost(PostDTO postDTO) throws Exception;
	public void deletePost(int mediaCd) throws Exception;
	public PostDTO selectPostDetail(int mediaCd) throws Exception;
	public PostDTO selectOneEditPost(int mediaCd) throws Exception;
	public String selectOneDeletePost(int mediaCd) throws Exception;
	public String selectProductCheck(int mediaCd) throws Exception;
	public int selectOneMemberPostCnt(String username) throws Exception;
}
