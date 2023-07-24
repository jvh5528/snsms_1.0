package com.application.snsms.follow.service;

import java.util.List;
import java.util.Map;

import com.application.snsms.follow.dto.FollowerDTO;
import com.application.snsms.follow.dto.FollowingDTO;

public interface FollowService {

	public int getFollowersCnt(String username) throws Exception;
	public int getFollowingCnt(String username) throws Exception;
	
	public List<FollowingDTO> getFollowingList(String username) throws Exception;
	public List<FollowerDTO> getfollowersList(String followUsername) throws Exception;
	
	public List<String> getFollowingUsernameList(String username) throws Exception;
	
	public void insertFollowing(FollowingDTO followingDTO) throws Exception;
	public void insertFollower(FollowerDTO followerDTO) throws Exception;
	public String checkDupleFollow(FollowingDTO followingDTO) throws Exception;
	public String followYn(Map<String,String> followYnMap) throws Exception;
	
	public void removeFollowing(FollowingDTO followingDTO) throws Exception;
	public void removeFollower(FollowerDTO followerDTO) throws Exception;
	
}
