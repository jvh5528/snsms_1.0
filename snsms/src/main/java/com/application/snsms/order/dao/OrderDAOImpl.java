package com.application.snsms.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.snsms.member.dto.MemberDTO;
import com.application.snsms.order.dto.OrderDTO;
import com.application.snsms.post.dto.PostDTO;

@Repository
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberDTO selectOneOrderer(String username) throws Exception {
		return sqlSession.selectOne("orderMapper.selectOneOrderer", username);
	}

	@Override
	public List<PostDTO> selectListCartGoods(Map<String, Object>myCartMediaMap) throws Exception {
		return sqlSession.selectList("orderMapper.selectListCartGoods", myCartMediaMap);
	}

	@Override
	public void insertOrderByCart(List<OrderDTO> orderList) throws Exception {
		sqlSession.insert("orderMapper.insertOrderByCart", orderList);
	}

	@Override
	public void updateMediaStockCntByCart(List<Map<String, Integer>> mediaMapList) throws Exception {
		sqlSession.update("orderMapper.updateMediaStockCntByCart", mediaMapList);
	}

	@Override
	public void deleteCartByOrder(int[] cartCdList) throws Exception {
		sqlSession.delete("orderMapper.deleteCartByOrder", cartCdList);
	}

	@Override
	public PostDTO selectOneCartMedia(int mediaCd) throws Exception {
		return sqlSession.selectOne("orderMapper.selectOneCartMedia", mediaCd);
	}

	@Override
	public void insertOrder(OrderDTO orderDTO) throws Exception {
		sqlSession.insert("orderMapper.insertOrder", orderDTO);
	}

	@Override
	public void updateMediaStockCnt(Map<String, Object> orderMap) throws Exception {
		sqlSession.update("orderMapper.updateMediaStockCnt", orderMap);
	}

}
