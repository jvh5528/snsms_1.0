package com.application.snsms.admin.member.service;

import java.util.List;

import com.application.snsms.admin.member.dto.AdminDTO;
import com.application.snsms.member.dto.MemberDTO;

public interface AdminMemberService {

	public boolean adminLogin(AdminDTO adminDTO) throws Exception;
	public List<MemberDTO> getMemberList() throws Exception;
	public List<MemberDTO> getDailyNewMemberList() throws Exception;
	public List<MemberDTO> sendPasswordRenewReminders() throws Exception;
}
