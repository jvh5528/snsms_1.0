package com.application.snsms.admin.member.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.application.snsms.admin.member.dao.AdminMemberDAO;
import com.application.snsms.admin.member.dto.AdminDTO;
import com.application.snsms.member.dto.MemberDTO;

@Service
public class AdminMemberServiceImpl implements AdminMemberService {

	@Autowired
	private AdminMemberDAO adminMemberDAO;
	

	@Override
	public boolean adminLogin(AdminDTO adminDTO) throws Exception {
		
		if (adminMemberDAO.selectAdminLogin(adminDTO) != null) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public List<MemberDTO> getMemberList() throws Exception {
		return adminMemberDAO.selectListMember();
	}


	@Override
	@Scheduled(cron = "59 59 23 * * *")
	public List<MemberDTO> sendPasswordRenewReminders() throws Exception {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar sixMonthsAgo = Calendar.getInstance();
	    sixMonthsAgo.add(Calendar.MONTH, -6);
	    sixMonthsAgo.set(Calendar.HOUR_OF_DAY, 0);
	    sixMonthsAgo.set(Calendar.MINUTE, 0);
	    sixMonthsAgo.set(Calendar.SECOND, 0);
	    sixMonthsAgo.set(Calendar.MILLISECOND, 0);

	    List<MemberDTO> passwordRenewList = adminMemberDAO.selectMembersPasswdDtBefore(sdf.format(sixMonthsAgo.getTime()));
	    return passwordRenewList;
	}


	@Override
	@Scheduled(cron="59 59 23 * * *")
	public List<MemberDTO> getDailyNewMemberList() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return adminMemberDAO.selectListDailyNewMember(sdf.format(new Date()));		
	}
	
}
