package com.application.snsms.profile.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.snsms.profile.dao.ProfileDAO;
import com.application.snsms.profile.dto.CartDTO;
import com.application.snsms.profile.dto.ProfileDTO;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileDAO profileDAO;
	
	private final String FILE_REPO_PATH = "/Users/jessicahong/Documents/FILE_REPO_PATH/";
	//private final String FILE_REPO_PATH = "/var/lib/tomcat9/file_repo/";	// linux
	
	
	@Override
	public void addProfile(ProfileDTO profileDTO) throws Exception {
		profileDAO.insertProfile(profileDTO);
	}


	@Override
	public ProfileDTO getProfileDetail(String username) throws Exception {
		return profileDAO.selectOneProfile(username);
	}


	@Override
	public void editProfile(ProfileDTO profileDTO) throws Exception {
		profileDAO.updateProfile(profileDTO);
	}
	
	@Override
	public void removeMember(String username) throws Exception {
		
		ProfileDTO profileDTO = profileDAO.selectOneMember(username);
		new File(FILE_REPO_PATH + profileDTO.getProfilePic()).delete();
		
		profileDAO.deleteMember(username);
		profileDAO.deleteMemberProfile(username);
		profileDAO.deleteCartListByRemoveMember(username);
		profileDAO.deleteOrderListByRemoveMember(username);
		profileDAO.deleteLikeByRemoveMember(username);
		profileDAO.deleteCommentByRemoveMember(username);
		profileDAO.deletePostByRemoveMember(username);
		profileDAO.deleteFollowingByRemoveMember(username);
		profileDAO.deleteFollowerByRemoveMember(username);
	}

	@Override
	public List<ProfileDTO> getSearchMemberList(Map<String, Object> searchMap) throws Exception {
		return profileDAO.selectListSearchMember(searchMap);
	}


	@Override
	public List<ProfileDTO> getMemberList() throws Exception {
		return profileDAO.selectListMember();
	}


	@Override
	public ProfileDTO getMemberDetail(String username) throws Exception {
		return profileDAO.selectOneMember(username);
	}


	@Override
	public List<ProfileDTO> getRelatedMemberList(ProfileDTO profileDTO) throws Exception {
		return profileDAO.selectRelatedMemberList(profileDTO);
	}

	@Override
	public boolean checkDuplicateCart(Map<String, Object>cartDupleMap) throws Exception {
		
		boolean isDuple = true;
		
		if (profileDAO.selectOneDuplicatedCart(cartDupleMap) == null) {
			isDuple = false;
		}
		return isDuple;
	}


	@Override
	public void addCart(CartDTO cartDTO) throws Exception {
		profileDAO.insertCart(cartDTO);
	}


	@Override
	public List<Map<String, Object>> getMyCartList(String username) throws Exception {
		return profileDAO.selectListMyCart(username);
	}

	@Override
	public void removeCart(int[] deleteCartCdList) throws Exception {
		profileDAO.deleteCart(deleteCartCdList);
	}

	
	@Override
	public void modifyCartGoodsQty(Map<String, Object> updateMap) throws Exception {
		profileDAO.updateCartGoodsQty(updateMap);
	}
	
	@Override
	public int countCartList(String username) throws Exception {
		return profileDAO.selectCountMyCart(username);
	}


	@Override
	public List<Map<String, Object>> getMyOrderList(String username) throws Exception {
		return profileDAO.selectListMyOrder(username);
	}


	@Override
	public Map<String, Object> getMyOrderDetail(Map<String, Object> orderDetailMap) throws Exception {
		return profileDAO.selectOneMyOrder(orderDetailMap);
	}

	@Override
	public List<Map<String, Object>> getIncomingOrderList(String username) throws Exception {
		List<Integer> myMediaCdList = profileDAO.selectMyMediaCdList(username);
		return profileDAO.selectListIncomingOrder(myMediaCdList);
	}

}
