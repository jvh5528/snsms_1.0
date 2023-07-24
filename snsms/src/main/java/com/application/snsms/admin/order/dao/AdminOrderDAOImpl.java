package com.application.snsms.admin.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.snsms.order.dto.OrderDTO;

@Repository
public class AdminOrderDAOImpl implements AdminOrderDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Map<String, Object>> selectFullOrderList() throws Exception {
		return sqlSession.selectList("adminOrderMapper.selectFullOrderList");
	}

	@Override
	public List<Map<String, Object>> selectMostOrderedList() throws Exception {
		return sqlSession.selectList("adminOrderMapper.selectMostOrderedList");
	}

	@Override
	public List<Map<String, Object>> selectOrderAmountList() throws Exception {
		return sqlSession.selectList("adminOrderMapper.selectOrderAmountList");
	}
}
