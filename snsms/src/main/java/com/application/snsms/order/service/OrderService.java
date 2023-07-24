package com.application.snsms.order.service;

import java.util.List;
import java.util.Map;

import com.application.snsms.member.dto.MemberDTO;
import com.application.snsms.order.dto.OrderDTO;
import com.application.snsms.post.dto.PostDTO;

public interface OrderService {

	public MemberDTO getOrdererDetail(String username) throws Exception;
	public List<PostDTO> getMediaListByCart(int[] mediaCdList , String username) throws Exception;
	public void addOrderByCart(Map<String, String> orderListMap) throws Exception;
	public PostDTO getMediaDetail(int mediaCd) throws Exception;
	public void addOrder(OrderDTO orderDTO) throws Exception;
}
