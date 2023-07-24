package com.application.snsms.member.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.application.snsms.admin.member.service.AdminMemberService;
import com.application.snsms.member.dto.MemberDTO;
import com.application.snsms.member.service.MemberService;
import com.application.snsms.profile.service.ProfileService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private AdminMemberService adminMemberService;
	
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/register")
	@ResponseBody
	public String register(MemberDTO memberDTO, HttpServletRequest request) throws Exception{
		
		memberService.newMember(memberDTO);
		
		String jsScript = "<script>";
		jsScript += "alert('Welcome!');";
		jsScript += "location.href='"+request.getContextPath()+"/member/login';";
		jsScript += "</script>";
		
		return jsScript;
	}
	
	@PostMapping("/checkDuplicateId")
	public @ResponseBody String checkDuplicateId(@RequestParam("username") String username) throws Exception{
		return memberService.checkId(username);
	}
	
	@PostMapping("/checkMemberStatus")
	public @ResponseBody String checkMemberStatus(@RequestParam("username") String username , @RequestParam("userStatus") String userStatus) throws Exception {
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUsername(username);
		memberDTO.setUserStatus(userStatus);
		
		String result = "notNew";
		if (memberService.checkStatus(memberDTO)) {
			result = "new";
		}
		return result;
	}
	
	@GetMapping("/login")
	public ModelAndView login(MemberDTO memberDTO) throws Exception{
		return new ModelAndView("login");
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(MemberDTO memberDTO, HttpServletRequest request) throws Exception {

	    String jsScript = "";

	    if (memberService.loginMember(memberDTO)) {
	        HttpSession session = request.getSession();
	        session.setAttribute("username"	, memberDTO.getUsername());
	        session.setAttribute("role"		, "client");
	        session.setAttribute("myCartCnt", memberService.getMyCartCnt(memberDTO.getUsername()));
	        session.setMaxInactiveInterval(60 * 30);

	        if (memberService.checkStatus(memberDTO)) {
	            jsScript = "<script>";
	            jsScript += "location.href='" + request.getContextPath() + "/profile/newProfile';";
	            jsScript += "</script>";
	        } else {
	            List<MemberDTO> passwordRenewList = adminMemberService.sendPasswordRenewReminders();

	            if (passwordRenewList.contains(memberDTO)) {
	                jsScript = "<script>";
	                jsScript += "location.href='" + request.getContextPath() + "/profile/passwordRenewal';";
	                jsScript += "</script>";
	            } else {
	                jsScript = "<script>";
	                jsScript += "location.href='" + request.getContextPath() + "/profile/myProfile';";
	                jsScript += "</script>";
	            }
	        }
	    } else {
	        jsScript = "<script>";
	        jsScript += "alert('Please check your username and/or password.');";
	        jsScript += "history.go(-1);";
	        jsScript += "</script>";
	    }

	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=utf-8");

	    return new ResponseEntity<>(jsScript, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/memberInfo")
	public ModelAndView memberInfo(@RequestParam("username") String username) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("memberInfo");
		mv.addObject("memberDTO"  , memberService.getMemberDetail(username));
		mv.addObject("profileDTO" , profileService.getMemberDetail(username));
		
		return mv;
	}
	
	@GetMapping("/logout")
	public @ResponseBody String logout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		String jsScript = "<script>";
		jsScript += "alert('Logging out. You need to log back in.');";
		jsScript += "location.href='"+request.getContextPath()+"/member/login';";
		jsScript += "</script>";
		
		return jsScript;
	}
	
	@GetMapping("/deleteAccount")
	public ModelAndView deleteAccount(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("deleteAccount");
		mv.addObject("memberDTO", memberService.getMemberDetail((String)session.getAttribute("username")));
		
		return mv;
	}
	
	@PostMapping("/deleteAccount")
	public ResponseEntity<Object> deleteAccount(MemberDTO memberDTO , HttpServletRequest request) throws Exception{
		
		String jsScript = "";
		
		if (memberService.deleteAccount(memberDTO)) {
			HttpSession session = request.getSession();
			session.invalidate();
			
			jsScript = "<script>";
			jsScript += "alert('Your account has been permanently removed.');";
			jsScript += "location.href='"+request.getContextPath()+"/member/register';";
			jsScript += "</script>";
		}
		else {
			jsScript = "<script>";
			jsScript += "alert('Please check your password.');";
			jsScript += "history.go(-1);";
			jsScript += "</script>";
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
		
	}
}
