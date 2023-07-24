package com.application.snsms.like.service;

import java.util.List;
import java.util.Map;

import com.application.snsms.like.dto.LikeDTO;

public interface LikeService {

	public String checkDuplicatedLike(LikeDTO likeDTO) throws Exception;
	public String checkLikeYn(Map<String, Object>likeYnMap) throws Exception;
	public void addLike(LikeDTO likeDTO) throws Exception;
	public void removeLike(LikeDTO likeDTO) throws Exception;
	public int getAllLikesCnt(int mediaCd) throws Exception;
	public List<LikeDTO> getLikesList(int mediaCd) throws Exception;
	public List<LikeDTO> getMyLikesList(String username) throws Exception;
	
}
