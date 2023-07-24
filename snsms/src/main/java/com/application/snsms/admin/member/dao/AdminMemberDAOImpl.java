package com.application.snsms.admin.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.snsms.admin.member.dto.AdminDTO;
import com.application.snsms.member.dto.MemberDTO;

@Repository
public class AdminMemberDAOImpl implements AdminMemberDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public AdminDTO selectAdminLogin(AdminDTO adminDTO) throws Exception {
		return sqlSession.selectOne("adminMemberMapper.selectAdminLogin", adminDTO);
	}

	@Override
	public List<MemberDTO> selectListMember() throws Exception {
		return sqlSession.selectList("adminMemberMapper.selectListMember");
	}

	@Override
	public List<MemberDTO> selectListDailyNewMember(String today) throws Exception {
		return sqlSession.selectList("adminMemberMapper.selectListDailyNewMember", today);
	}

	@Override
	public List<MemberDTO> selectMembersPasswdDtBefore(String format) {
		return sqlSession.selectList("adminMemberMapper.selectMembersPasswdDtBefore", format);
	}
	
	
}
