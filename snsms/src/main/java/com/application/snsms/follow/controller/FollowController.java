package com.application.snsms.follow.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.application.snsms.follow.dto.FollowerDTO;
import com.application.snsms.follow.dto.FollowingDTO;
import com.application.snsms.follow.service.FollowService;
import com.application.snsms.member.service.MemberService;

@Controller
@RequestMapping("/follow")
public class FollowController {

	@Autowired
	private  FollowService followService;
	
	@Autowired
	private MemberService memberService;
	
	
	
	@GetMapping("/followingList")
	public ModelAndView followingList(@ModelAttribute FollowingDTO followingDTO , @RequestParam("username") String username, HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String myUsername = (String)session.getAttribute("username");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("followingList");
		mv.addObject("memberDTO"	, memberService.getMemberDetail(myUsername));
		mv.addObject("followingList", followService.getFollowingList(username));
		
		return mv; 
	}
	
	@GetMapping("/followersList")
	public ModelAndView followingList(@RequestParam("followUsername") String followUsername, HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String myUsername = (String)session.getAttribute("username");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("followersList");
		mv.addObject("memberDTO"	, memberService.getMemberDetail(myUsername));
		mv.addObject("followersList", followService.getfollowersList(followUsername));
		return mv;
	}
	
	
	@PostMapping("/addtoFollowingList")
	public @ResponseBody String addtoFollowingList(@RequestParam("username") String username , 
												   @RequestParam("followingUsername") String followingUsername , 
												   @RequestParam("followingName") String followingName , 
												   @RequestParam("followingPic") String followingPic , 
												   @RequestParam("followUsername") String followUsername,  
												   @RequestParam("followerUsername") String followerUsername, 
												   @RequestParam("followerName") String followerName, 
												   @RequestParam("followerPic") String followerPic, HttpServletRequest request) throws Exception{
		
		FollowingDTO followingDTO = new FollowingDTO();
		followingDTO.setUsername(username);
		followingDTO.setFollowingUsername(followingUsername);
		followingDTO.setFollowingName(followingName);
		followingDTO.setFollowingPic(followingPic);
		
		FollowerDTO followerDTO = new FollowerDTO();
		followerDTO.setFollowUsername(followUsername);
		followerDTO.setFollowerUsername(followerUsername);
		followerDTO.setFollowerName(followerName);
		followerDTO.setFollowerPic(followerPic);
		
		String result = "";
		if (followService.checkDupleFollow(followingDTO) == "N") {
			followService.insertFollowing(followingDTO);
			followService.insertFollower(followerDTO);
			
			result = "<script>";
			result += "location.href='"+request.getContextPath()+"/profile/memberDetail?username="+followingUsername+"';";
			result += "</script>";
		}
		else {
			followService.removeFollowing(followingDTO);
			followService.removeFollower(followerDTO);
			result = "<script>";
			result += "location.href='"+request.getContextPath()+"/profile/memberDetail?username="+followingUsername+"';";
			result += "</script>";
		}
			
			
		return result;
	}
	
}
