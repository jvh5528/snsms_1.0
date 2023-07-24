package com.application.snsms.follow.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.snsms.follow.dto.FollowerDTO;
import com.application.snsms.follow.dto.FollowingDTO;

@Repository
public class FollowDAOImpl implements FollowDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int selectOneMemberFollowersCnt(String username) throws Exception {
		return sqlSession.selectOne("followMapper.selectOneMemberFollowersCnt", username);
	}
	
	@Override
	public int selectOneMemberFollowingCnt(String username) throws Exception {
		return sqlSession.selectOne("followMapper.selectOneMemberFollowingCnt", username);
	}

	@Override
	public List<FollowerDTO> selectListFollowers(String followUsername) throws Exception {
		return sqlSession.selectList("followMapper.selectListFollowers", followUsername);
	}

	@Override
	public List<FollowingDTO> selectListFollowing(String username) throws Exception {
		return sqlSession.selectList("followMapper.selectListFollowing", username);
	}

	@Override
	public void insertFollowing(FollowingDTO followingDTO) throws Exception {
		sqlSession.insert("followMapper.insertFollowing", followingDTO);
	}

	@Override
	public void insertFollower(FollowerDTO followerDTO) throws Exception {
		sqlSession.insert("followMapper.insertFollower", followerDTO);
	}
	

	@Override
	public FollowingDTO selectOneDuplicateFollowId(FollowingDTO followingDTO) throws Exception {
		return sqlSession.selectOne("followMapper.selectOneDuplicateFollowId", followingDTO);
	}
		
	@Override
	public void deleteFollowing(FollowingDTO followingDTO) throws Exception {
		sqlSession.delete("followMapper.deleteFollowing", followingDTO);
	}

	@Override
	public void deleteFollower(FollowerDTO followerDTO) throws Exception {
		sqlSession.delete("followMapper.deleteFollower", followerDTO);
	}
	
	@Override
	public List<String> selectListFollowingUsername(String username) throws Exception {
		return sqlSession.selectList("followMapper.selectListFollowingUsername", username);
	}

	@Override
	public String followYn(Map<String,String> followYnMap) throws Exception {
		return sqlSession.selectOne("followMapper.followYn", followYnMap);
	}


}
