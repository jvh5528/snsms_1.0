package com.application.snsms.like.dao;

import java.util.List;
import java.util.Map;

import com.application.snsms.like.dto.LikeDTO;

public interface LikeDAO {

	public LikeDTO selectOneDuplicatedLike(LikeDTO likeDTO) throws Exception;
	public String likeYn(Map<String, Object>likeYnMap) throws Exception;
	public void insertLike(LikeDTO likeDTO) throws Exception;
	public void deleteLike(LikeDTO likeDTO) throws Exception;
	public int selectOnePostLikesCnt(int mediaCd) throws Exception;
	public List<LikeDTO> selectLikesList(int mediaCd) throws Exception;
	public List<LikeDTO> selectMyLikesList(Map<String, Object>myLikesMap) throws Exception;
}
