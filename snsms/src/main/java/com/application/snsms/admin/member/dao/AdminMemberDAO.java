package com.application.snsms.admin.member.dao;

import java.util.List;

import com.application.snsms.admin.member.dto.AdminDTO;
import com.application.snsms.member.dto.MemberDTO;


public interface AdminMemberDAO {

	public AdminDTO selectAdminLogin(AdminDTO adminDTO) throws Exception;
	public List<MemberDTO> selectListMember() throws Exception;
	public List<MemberDTO> selectListDailyNewMember(String today) throws Exception;
	public List<MemberDTO> selectMembersPasswdDtBefore(String format);
}
