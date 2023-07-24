package com.application.snsms.member.dao;

import com.application.snsms.member.dto.MemberDTO;

public interface MemberDAO {

	public void insertMember(MemberDTO memberDTO) throws Exception;
	public MemberDTO selectDuplicateId(String username) throws Exception;
	public MemberDTO selectLogin(MemberDTO memberDTO) throws Exception;
	public MemberDTO selectOneMemberDetail(String username) throws Exception;
	public MemberDTO selectMemberStatus(String username) throws Exception;
	public String selectOnePasswd(String username) throws Exception;
	public void updateStatus(MemberDTO memberDTO) throws Exception;
	public void updatePassword(MemberDTO memberDTO) throws Exception;
	public int selectMyCartCnt(String username) throws Exception;
	public int selectMyOrderCnt(String username) throws Exception;
}
