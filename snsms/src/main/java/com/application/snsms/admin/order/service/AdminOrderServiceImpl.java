package com.application.snsms.admin.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.snsms.admin.order.dao.AdminOrderDAO;
import com.application.snsms.order.dto.OrderDTO;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {

	@Autowired
	private AdminOrderDAO adminOrderDAO;

	@Override
	public List<Map<String, Object>> getOrderList() throws Exception {
		return adminOrderDAO.selectFullOrderList();
	}

	@Override
	public List<Map<String, Object>> getMostOrderList() throws Exception {
		return adminOrderDAO.selectMostOrderedList();
	}

	@Override
	public List<Map<String, Object>> getOrderAmount() throws Exception {
		return adminOrderDAO.selectOrderAmountList();
	}
}
