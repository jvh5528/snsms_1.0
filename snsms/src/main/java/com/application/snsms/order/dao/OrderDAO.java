package com.application.snsms.order.dao;

import java.util.List;
import java.util.Map;

import com.application.snsms.member.dto.MemberDTO;
import com.application.snsms.order.dto.OrderDTO;
import com.application.snsms.post.dto.PostDTO;

public interface OrderDAO {

	public void insertOrder(OrderDTO orderDTO) throws Exception;
	public MemberDTO selectOneOrderer(String username) throws Exception;
	public List<PostDTO> selectListCartGoods(Map<String, Object>myCartMediaMap) throws Exception;
	public void insertOrderByCart(List<OrderDTO> orderList) throws Exception;
	public void updateMediaStockCntByCart(List<Map<String,Integer>> mediaMapList) throws Exception;
	public void updateMediaStockCnt(Map<String, Object> orderMap) throws Exception;
	public void deleteCartByOrder(int[] cartCdList) throws Exception;
	public PostDTO selectOneCartMedia(int mediaCd) throws Exception;
}
