package com.application.snsms.follow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.snsms.follow.dao.FollowDAO;
import com.application.snsms.follow.dto.FollowerDTO;
import com.application.snsms.follow.dto.FollowingDTO;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowDAO followDAO;

	@Override
	public int getFollowersCnt(String username) throws Exception{
		return followDAO.selectOneMemberFollowersCnt(username);
	}
	
	@Override
	public int getFollowingCnt(String username) throws Exception {
		return followDAO.selectOneMemberFollowingCnt(username);
	}


	@Override
	public List<FollowingDTO> getFollowingList(String username) throws Exception {
		return followDAO.selectListFollowing(username);
	}
	
	@Override
	public List<FollowerDTO> getfollowersList(String followUsername) throws Exception {
		return followDAO.selectListFollowers(followUsername);
	}

	@Override
	public void insertFollowing(FollowingDTO followingDTO) throws Exception {
		followDAO.insertFollowing(followingDTO);
	}
	
	@Override
	public void insertFollower(FollowerDTO followerDTO) throws Exception {
		followDAO.insertFollower(followerDTO);
	}

	@Override
	public String checkDupleFollow(FollowingDTO followingDTO) throws Exception {
		
		String isDuple = "Y";
		
		if (followDAO.selectOneDuplicateFollowId(followingDTO) == null) {
			return "N";
		}
		return isDuple;

	}

	@Override
	public List<String> getFollowingUsernameList(String username) throws Exception {
		return followDAO.selectListFollowingUsername(username);
	}

	@Override
	public void removeFollowing(FollowingDTO followingDTO) throws Exception {
		followDAO.deleteFollowing(followingDTO);
	}

	@Override
	public void removeFollower(FollowerDTO followerDTO) throws Exception {
		followDAO.deleteFollower(followerDTO);
	}

	@Override
	public String followYn(Map<String,String> followYnMap) throws Exception {
		return followDAO.followYn(followYnMap);
	}

}
