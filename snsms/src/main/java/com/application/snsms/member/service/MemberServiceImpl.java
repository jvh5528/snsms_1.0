package com.application.snsms.member.service;


import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.snsms.member.dao.MemberDAO;
import com.application.snsms.member.dto.MemberDTO;
import com.application.snsms.post.dto.PostDTO;
import com.application.snsms.post.service.PostService;
import com.application.snsms.profile.dao.ProfileDAO;
import com.application.snsms.profile.dto.ProfileDTO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private ProfileDAO profileDAO;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private final String FILE_REPO_PATH = "/Users/jessicahong/Documents/FILE_REPO_PATH/";
	//private final String FILE_REPO_PATH = "/var/lib/tomcat9/file_repo/";	// linux

	@Override
	public void newMember(MemberDTO memberDTO) throws Exception {
		memberDTO.setPasswd(bCryptPasswordEncoder.encode(memberDTO.getPasswd()));
		memberDAO.insertMember(memberDTO);
	}

	@Override
	public String checkId(String username) throws Exception {
		
		String isDuple = "Y";
		
		if (memberDAO.selectDuplicateId(username) == null) {
			return "N";
		}
		return isDuple;
	}
	
	@Override
	public boolean checkStatus(MemberDTO memberDTO) throws Exception {
		if(memberDAO.selectMemberStatus(memberDTO.getUsername()) == null) return true;
		else															  return false;
	}

	@Override
	public boolean loginMember(MemberDTO memberDTO) throws Exception {
		
		MemberDTO checkExistId = memberDAO.selectLogin(memberDTO);
		if (checkExistId != null) {
			if (bCryptPasswordEncoder.matches(memberDTO.getPasswd(), checkExistId.getPasswd())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void updatePassword(MemberDTO memberDTO) throws Exception {
		String encodedPassword = bCryptPasswordEncoder.encode(memberDTO.getPasswd());
	    memberDTO.setPasswd(encodedPassword);
	    memberDAO.updatePassword(memberDTO);
	}

	@Override
	public MemberDTO getMemberDetail(String username) throws Exception {
		return memberDAO.selectOneMemberDetail(username);
	}

	@Override
	public void changeMemberStatus(MemberDTO memberDTO) throws Exception {
		memberDAO.updateStatus(memberDTO);
	}
	

	@Override
	public boolean deleteAccount(MemberDTO memberDTO) throws Exception {

		boolean isDelete = false;
		
		MemberDTO dbMemberDTO = memberDAO.selectOneMemberDetail(memberDTO.getUsername());
		
		if (bCryptPasswordEncoder.matches(memberDTO.getPasswd(), dbMemberDTO.getPasswd())) {
			profileDAO.deleteMember(dbMemberDTO.getUsername());
			profileDAO.deleteCartListByRemoveMember(dbMemberDTO.getUsername());
			profileDAO.deleteCommentByRemoveMember(dbMemberDTO.getUsername());
			profileDAO.deleteFollowerByRemoveMember(dbMemberDTO.getUsername());
			profileDAO.deleteFollowingByRemoveMember(dbMemberDTO.getUsername());
			profileDAO.deleteLikeByRemoveMember(dbMemberDTO.getUsername());
			profileDAO.deleteOrderListByRemoveMember(dbMemberDTO.getUsername());
			
			
			ProfileDTO profileDTO = profileDAO.selectOneMember(dbMemberDTO.getUsername());
			new File(FILE_REPO_PATH + profileDTO.getProfilePic()).delete();
			profileDAO.deleteMemberProfile(dbMemberDTO.getUsername());
			
			List<PostDTO> myPostList = postService.getPostList(dbMemberDTO.getUsername());
			for (PostDTO postDTO : myPostList) {
				new File(FILE_REPO_PATH + postDTO.getPhoto()).delete();
			}
			profileDAO.deletePostByRemoveMember(dbMemberDTO.getUsername());
			
			isDelete = true;
		}
		
		return isDelete;
	}
	
	@Override
	public int getMyCartCnt(String username) throws Exception {
		return memberDAO.selectMyCartCnt(username);
	}

	@Override
	public int getMyOrderCnt(String username) throws Exception {
		return memberDAO.selectMyOrderCnt(username);
	}
}
