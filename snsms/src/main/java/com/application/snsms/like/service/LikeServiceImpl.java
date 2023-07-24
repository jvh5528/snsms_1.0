package com.application.snsms.like.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.snsms.like.dao.LikeDAO;
import com.application.snsms.like.dto.LikeDTO;
import com.application.snsms.profile.dao.ProfileDAO;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeDAO likeDAO;
	
	@Autowired
	private ProfileDAO profileDAO;

	@Override
	public String checkDuplicatedLike(LikeDTO likeDTO) throws Exception {
		
		String isDuple = "Y";
		if(likeDAO.selectOneDuplicatedLike(likeDTO) == null) {
			return "N";
		}
		return isDuple;												
	}
	
	@Override
	public String checkLikeYn(Map<String, Object>likeYnMap) throws Exception {
		return likeDAO.likeYn(likeYnMap);
	}

	@Override
	public void addLike(LikeDTO likeDTO) throws Exception {
		likeDAO.insertLike(likeDTO);
	}

	@Override
	public int getAllLikesCnt(int mediaCd) throws Exception {
		return likeDAO.selectOnePostLikesCnt(mediaCd);
	}

	@Override
	public List<LikeDTO> getLikesList(int mediaCd) throws Exception {
		return likeDAO.selectLikesList(mediaCd);
	}

	@Override
	public void removeLike(LikeDTO likeDTO) throws Exception {
		likeDAO.deleteLike(likeDTO);
	}

	@Override
	public List<LikeDTO> getMyLikesList(String username) throws Exception {
		
		List<Integer> myMediaCdList = profileDAO.selectMyMediaCdList(username);
		
		Map<String, Object> myLikesMap = new HashMap<String, Object>();
		myLikesMap.put("username"	  , username);
		myLikesMap.put("myMediaCdList", myMediaCdList);
		
		return likeDAO.selectMyLikesList(myLikesMap);
	}


}
