package com.application.snsms.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.snsms.member.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertMember(MemberDTO memberDTO) throws Exception {
		sqlSession.insert("memberMapper.insertMember", memberDTO);
	}

	@Override
	public MemberDTO selectDuplicateId(String username) throws Exception {
		return sqlSession.selectOne("memberMapper.selectDuplicateId", username);
	}
	
	@Override
	public MemberDTO selectMemberStatus(String username) throws Exception {
		return sqlSession.selectOne("memberMapper.selectMemberStatus", username);
	}

	@Override
	public MemberDTO selectLogin(MemberDTO memberDTO) throws Exception {
		return sqlSession.selectOne("memberMapper.selectLogin", memberDTO);
	}

	@Override
	public MemberDTO selectOneMemberDetail(String username) throws Exception {
		return sqlSession.selectOne("memberMapper.selectOneMemberDetail", username);
	}

	@Override
	public void updateStatus(MemberDTO memberDTO) throws Exception {
		sqlSession.update("memberMapper.updateStatus", memberDTO);
	}

	@Override
	public String selectOnePasswd(String username) throws Exception {
		return sqlSession.selectOne("memberMapper.selectOnePasswd", username);
	}

	@Override
	public int selectMyCartCnt(String username) throws Exception {
		return sqlSession.selectOne("memberMapper.selectMyCartCnt", username);
	}

	@Override
	public int selectMyOrderCnt(String username) throws Exception {
		return sqlSession.selectOne("memberMapper.selectMyOrderCnt" , username);
	}

	@Override
	public void updatePassword(MemberDTO memberDTO) throws Exception {
		sqlSession.update("memberMapper.updatePassword", memberDTO);
	}
	
}
