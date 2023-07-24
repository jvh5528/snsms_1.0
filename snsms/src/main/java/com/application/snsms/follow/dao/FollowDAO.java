package com.application.snsms.follow.dao;

import java.util.List;
import java.util.Map;

import com.application.snsms.follow.dto.FollowerDTO;
import com.application.snsms.follow.dto.FollowingDTO;

public interface FollowDAO {

	public int selectOneMemberFollowersCnt(String username) throws Exception;
	public int selectOneMemberFollowingCnt(String username) throws Exception;
	
	public List<FollowerDTO> selectListFollowers(String followUsername) throws Exception;
	public List<FollowingDTO> selectListFollowing(String username) throws Exception;
	
	public List<String> selectListFollowingUsername(String username) throws Exception;
	
	public void insertFollowing(FollowingDTO followingDTO) throws Exception;
	public void insertFollower(FollowerDTO followerDTO) throws Exception;
	public FollowingDTO selectOneDuplicateFollowId(FollowingDTO followingDTO) throws Exception;
	public String followYn(Map<String,String> followYnMap)throws Exception;
	
	public void deleteFollowing (FollowingDTO followingDTO) throws Exception;
	public void deleteFollower (FollowerDTO followerDTO) throws Exception;
}

