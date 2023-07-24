package com.application.snsms.member.service;

import com.application.snsms.member.dto.MemberDTO;

public interface MemberService {

	public void newMember(MemberDTO memberDTO) throws Exception;
	public String checkId(String username) throws Exception;
	public boolean loginMember(MemberDTO memberDTO) throws Exception;
	public MemberDTO getMemberDetail(String username) throws Exception;
	public boolean checkStatus(MemberDTO memberDTO) throws Exception;
	public void changeMemberStatus(MemberDTO memberDTO) throws Exception;
	public boolean deleteAccount(MemberDTO memberDTO) throws Exception;
	public void updatePassword(MemberDTO memberDTO) throws Exception;
	public int getMyCartCnt(String username) throws Exception;
	public int getMyOrderCnt(String username) throws Exception;
}
