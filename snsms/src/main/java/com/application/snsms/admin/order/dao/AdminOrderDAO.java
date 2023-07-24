package com.application.snsms.admin.order.dao;

import java.util.List;
import java.util.Map;

import com.application.snsms.order.dto.OrderDTO;

public interface AdminOrderDAO {

	public List<Map<String, Object>> selectFullOrderList() throws Exception;
	public List<Map<String, Object>> selectMostOrderedList() throws Exception;
	public List<Map<String, Object>> selectOrderAmountList() throws Exception;
}
