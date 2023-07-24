package com.application.snsms.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.snsms.member.dto.MemberDTO;
import com.application.snsms.order.dao.OrderDAO;
import com.application.snsms.order.dto.OrderDTO;
import com.application.snsms.post.dto.PostDTO;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;

	@Override
	public MemberDTO getOrdererDetail(String username) throws Exception {
		return orderDAO.selectOneOrderer(username);
	}

	@Override
	public List<PostDTO> getMediaListByCart(int[] mediaCdList , String username) throws Exception {
		
		Map<String, Object> myCartMediaMap = new HashMap<String, Object>();
		myCartMediaMap.put("username", username);
		myCartMediaMap.put("mediaCdList", mediaCdList);
		
		return orderDAO.selectListCartGoods(myCartMediaMap);
	}

	@Override
	@Transactional
	public void addOrderByCart(Map<String, String> orderListMap) throws Exception {
		
		List<OrderDTO> orderList = new ArrayList<OrderDTO>();
		
		String[] temp1 = orderListMap.get("mediaCdList").split(",");
		int[] mediaCdList = new int[temp1.length];
		for (int i = 0; i < temp1.length; i++) {
			mediaCdList[i] = Integer.parseInt(temp1[i]);
		}
		
		String[] temp2 = orderListMap.get("orderMediaQtyList").split(",");
		int[] orderMediaQtyList = new int[temp2.length];
		for (int i = 0; i < temp2.length; i++) {
			orderMediaQtyList[i] = Integer.parseInt(temp2[i]);
		}
		
		String[] temp3 = orderListMap.get("paymentAmtList").split(",");
		int[] paymentAmtList = new int[temp3.length];
		for (int i = 0; i < temp3.length; i++) {
			paymentAmtList[i] = Integer.parseInt(temp3[i]);
		}
		
		for (int i = 0; i < mediaCdList.length; i++) {
			
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setMediaCd(mediaCdList[i]);
			orderDTO.setUsername(orderListMap.get("username"));
			orderDTO.setOrderMediaQty(orderMediaQtyList[i]);
			orderDTO.setPaymentAmt(paymentAmtList[i]);
			orderDTO.setOrdererNm(orderListMap.get("ordererNm"));
			orderDTO.setOrdererHp(orderListMap.get("ordererHp"));
			orderDTO.setReceiverNm(orderListMap.get("receiverNm"));
			orderDTO.setReceiverHp(orderListMap.get("receiverHp"));
			orderDTO.setZipcode(orderListMap.get("zipcode"));
			orderDTO.setRoadAddress(orderListMap.get("roadAddress"));
			orderDTO.setJibunAddress(orderListMap.get("jibunAddress"));
			orderDTO.setNamujiAddress(orderListMap.get("namujiAddress"));
			orderDTO.setDeliveryMethod(orderListMap.get("deliveryMethod"));
			orderDTO.setDeliveryMessage(orderListMap.get("deliveryMessage"));
			orderDTO.setGiftWrapping(orderListMap.get("giftWrapping"));
			orderDTO.setPayMethod(orderListMap.get("payMethod"));
			orderDTO.setPayOrdererHp(orderListMap.get("payOrdererHp"));
			orderDTO.setCardCompanyNm(orderListMap.get("cardCompanyNm"));
			orderDTO.setCardPayMonth(orderListMap.get("cardPayMonth"));
			orderList.add(orderDTO);
		}
		
		List<Map<String, Integer>> mediaMapList = new ArrayList<Map<String,Integer>>();
		for (int i = 0; i < mediaCdList.length; i++) {
			Map<String, Integer> mediaMap = new HashMap<String, Integer>();
			mediaMap.put("mediaCd", mediaCdList[i]);
			mediaMap.put("orderMediaQty", orderMediaQtyList[i]);
			mediaMapList.add(mediaMap);
		}
		
		orderDAO.updateMediaStockCntByCart(mediaMapList);
		orderDAO.insertOrderByCart(orderList);
		
		String[] temp4 = orderListMap.get("cartCdList").split(",");
		int[] cartCdList = new int[temp4.length];
		for (int i = 0; i < temp4.length; i++) {
			cartCdList[i] = Integer.parseInt(temp4[i]);
		}
		orderDAO.deleteCartByOrder(cartCdList);
	}

	@Override
	public PostDTO getMediaDetail(int mediaCd) throws Exception {
		return orderDAO.selectOneCartMedia(mediaCd);
	}

	@Override
	@Transactional
	public void addOrder(OrderDTO orderDTO) throws Exception {
		
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("orderMediaQty", orderDTO.getOrderMediaQty());
		orderMap.put("mediaCd"		, orderDTO.getMediaCd());
		orderMap.put("username"		, orderDTO.getUsername());
		
		orderDAO.updateMediaStockCnt(orderMap);
		orderDAO.insertOrder(orderDTO);
	}
	
}
