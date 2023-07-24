package com.application.snsms.profile.dao;

import java.util.List;
import java.util.Map;

import com.application.snsms.profile.dto.CartDTO;
import com.application.snsms.profile.dto.ProfileDTO;

public interface ProfileDAO {

	public void insertProfile(ProfileDTO profileDTO) throws Exception;
	public ProfileDTO selectOneProfile(String username) throws Exception;
	public void updateProfile(ProfileDTO profileDTO) throws Exception;
	public void deleteMember(String username ) throws Exception;
	public void deleteMemberProfile(String username) throws Exception;
	public void deleteCartListByRemoveMember(String username ) throws Exception;
	public void deleteOrderListByRemoveMember(String username ) throws Exception;
	public void deleteLikeByRemoveMember(String username ) throws Exception;
	public void deleteCommentByRemoveMember(String username ) throws Exception;
	public void deletePostByRemoveMember(String username ) throws Exception;
	public void deleteFollowingByRemoveMember(String username ) throws Exception;
	public void deleteFollowerByRemoveMember(String username ) throws Exception;
	
	public List<ProfileDTO> selectListMember() throws Exception;
	public ProfileDTO selectOneMember(String username) throws Exception;
	public List<ProfileDTO> selectRelatedMemberList(ProfileDTO profileDTO) throws Exception;
	public List<ProfileDTO> selectListSearchMember(Map<String, Object> searchMap) throws Exception;
	
	public CartDTO selectOneDuplicatedCart(Map<String, Object> cartDupleMap) throws Exception;
	public void insertCart(CartDTO cartDTO) throws Exception;
	public List<Map<String,Object>> selectListMyCart(String username) throws Exception;
	public void deleteCart(int[]deleteCartCdList) throws Exception;
	public void updateCartGoodsQty(Map<String, Object>updateMap) throws Exception;
	public int selectCountMyCart(String username) throws Exception;
	public List<Map<String, Object>> selectListMyOrder(String username) throws Exception;
	public Map<String, Object> selectOneMyOrder(Map<String, Object> orderDetailMap) throws Exception;
	public List<Integer> selectMyMediaCdList(String username) throws Exception;
	public List<Map<String, Object>> selectListIncomingOrder(List<Integer>myMediaCdList) throws Exception;
}
