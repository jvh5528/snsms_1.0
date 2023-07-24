package com.application.snsms.profile.service;

import java.util.List;
import java.util.Map;

import com.application.snsms.profile.dto.ProfileDTO;
import com.application.snsms.profile.dto.CartDTO;

public interface ProfileService {

	public void addProfile(ProfileDTO profileDTO) throws Exception;
	public ProfileDTO getProfileDetail(String username) throws Exception;
	public void editProfile(ProfileDTO profileDTO) throws Exception;
	public void removeMember(String username) throws Exception;
	
	public List<ProfileDTO> getMemberList() throws Exception;
	public ProfileDTO getMemberDetail(String username) throws Exception;
	public List<ProfileDTO> getRelatedMemberList(ProfileDTO profileDTO) throws Exception;
	public List<ProfileDTO> getSearchMemberList(Map<String, Object> searchMap) throws Exception;
	
	
	public boolean checkDuplicateCart(Map<String, Object>cartDupleMap) throws Exception;
	public void addCart(CartDTO cartDTO) throws Exception;
	public List<Map<String,Object>> getMyCartList(String username) throws Exception;
	public void removeCart(int[] deleteCartCdList) throws Exception;
	public void modifyCartGoodsQty(Map<String, Object>updateMap) throws Exception;
	public int countCartList(String username) throws Exception;
	public List<Map<String, Object>> getMyOrderList(String username) throws Exception;
	public Map<String, Object> getMyOrderDetail(Map<String, Object> orderDetailMap) throws Exception;
	public List<Map<String, Object>> getIncomingOrderList(String username) throws Exception;
	
}
