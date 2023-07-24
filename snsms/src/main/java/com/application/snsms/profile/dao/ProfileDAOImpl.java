package com.application.snsms.profile.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.snsms.profile.dto.CartDTO;
import com.application.snsms.profile.dto.ProfileDTO;

@Repository
public class ProfileDAOImpl implements ProfileDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertProfile(ProfileDTO profileDTO) throws Exception {
		sqlSession.insert("profileMapper.insertProfile", profileDTO);
	}

	@Override
	public ProfileDTO selectOneProfile(String username) throws Exception {
		return sqlSession.selectOne("profileMapper.selectOneProfile", username);
	}

	@Override
	public void updateProfile(ProfileDTO profileDTO) throws Exception {
		sqlSession.update("profileMapper.updateProfile", profileDTO);
	}
	
	@Override
	public void deleteMember(String username) throws Exception {
		sqlSession.delete("profileMapper.deleteMember", username);
	}
	
	@Override
	public void deleteMemberProfile(String username) throws Exception {
		sqlSession.delete("profileMapper.deleteMemberProfile", username);
	}
	
	@Override
	public void deleteCartListByRemoveMember(String username) throws Exception {
		sqlSession.delete("profileMapper.deleteCartListByRemoveMember", username);
	}

	@Override
	public void deleteOrderListByRemoveMember(String username) throws Exception {
		sqlSession.delete("profileMapper.deleteOrderListByRemoveMember", username);
	}

	@Override
	public void deleteLikeByRemoveMember(String username) throws Exception {
		sqlSession.delete("profileMapper.deleteLikeByRemoveMember", username);
	}

	@Override
	public void deleteCommentByRemoveMember(String username) throws Exception {
		sqlSession.delete("profileMapper.deleteCommentByRemoveMember", username);
	}

	@Override
	public void deletePostByRemoveMember(String username) throws Exception {
		sqlSession.delete("profileMapper.deletePostByRemoveMember", username);
	}
	
	@Override
	public void deleteFollowingByRemoveMember(String username) throws Exception {
		sqlSession.delete("profileMapper.deleteFollowingByRemoveMember", username);
	}

	@Override
	public void deleteFollowerByRemoveMember(String username) throws Exception {
		sqlSession.delete("profileMapper.deleteFollowerByRemoveMember", username);
	}
	
	@Override
	public List<ProfileDTO> selectListSearchMember(Map<String, Object> searchMap) throws Exception {
		return sqlSession.selectList("profileMapper.selectListSearchMember", searchMap);
	}

	@Override
	public List<ProfileDTO> selectListMember() throws Exception {
		return sqlSession.selectList("profileMapper.selectListMember");
	}

	@Override
	public ProfileDTO selectOneMember(String username) throws Exception {
		return sqlSession.selectOne("profileMapper.selectOneMember", username);
	}

	@Override
	public List<ProfileDTO> selectRelatedMemberList(ProfileDTO profileDTO) throws Exception {
		return sqlSession.selectList("profileMapper.selectRelatedMemberList", profileDTO);
	}


	@Override
	public CartDTO selectOneDuplicatedCart(Map<String, Object> cartDupleMap) throws Exception {
		return sqlSession.selectOne("profileMapper.selectOneDuplicatedCart", cartDupleMap);
	}

	@Override
	public void insertCart(CartDTO cartDTO) throws Exception {
		sqlSession.insert("profileMapper.insertCart", cartDTO);
	}

	@Override
	public List<Map<String, Object>> selectListMyCart(String username) throws Exception {
		return sqlSession.selectList("profileMapper.selectListMyCart", username);
	}

	@Override
	public void deleteCart(int[] deleteCartCdList) throws Exception {
		sqlSession.delete("profileMapper.deleteCart", deleteCartCdList);
	}
	
	@Override
	public void updateCartGoodsQty(Map<String, Object> updateMap) throws Exception {
		sqlSession.update("profileMapper.updateCartGoodsQty", updateMap);
	}
	
	@Override
	public int selectCountMyCart(String username) throws Exception {
		return sqlSession.selectOne("profileMapper.selectCountMyCart", username);
	}

	@Override
	public List<Map<String, Object>> selectListMyOrder(String username) throws Exception {
		return sqlSession.selectList("profileMapper.selectListMyOrder", username);
	}

	@Override
	public Map<String, Object> selectOneMyOrder(Map<String, Object> orderDetailMap) throws Exception {
		return sqlSession.selectOne("profileMapper.selectOneMyOrder", orderDetailMap);
	}

	@Override
	public List<Integer> selectMyMediaCdList(String username) throws Exception {
		return sqlSession.selectList("profileMapper.selectMyMediaCdList", username);
	}
	
	@Override
	public List<Map<String, Object>> selectListIncomingOrder(List<Integer>myMediaCdList) throws Exception {
		return sqlSession.selectList("profileMapper.selectListIncomingOrder" , myMediaCdList);
	}

}
