package com.application.snsms.admin.order.service;

import java.util.List;
import java.util.Map;

import com.application.snsms.order.dto.OrderDTO;

public interface AdminOrderService {

	public List<Map<String, Object>> getOrderList() throws Exception;
	public List<Map<String, Object>> getMostOrderList() throws Exception;
	public List<Map<String, Object>> getOrderAmount() throws Exception;
}
