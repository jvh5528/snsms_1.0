package com.application.snsms.like.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.application.snsms.comment.service.CommentService;
import com.application.snsms.follow.service.FollowService;
import com.application.snsms.like.dto.LikeDTO;
import com.application.snsms.like.service.LikeService;

@Controller
@RequestMapping("/like")
public class LikeController {

	@Autowired
	private LikeService likeService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private FollowService followService;

	
	@PostMapping("/addLike")
	public @ResponseBody String addLike(@RequestParam("mediaCd") int mediaCd , 
										@RequestParam("likedbyUsername") String likedbyUsername,
										@RequestParam("likedbyProfilepic") String likedbyProfilepic, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		LikeDTO likeDTO = new LikeDTO();
		likeDTO.setMediaCd(mediaCd);
		likeDTO.setLikedbyUsername(likedbyUsername);
		likeDTO.setLikedbyProfilepic(likedbyProfilepic);
		
		Map<String,Object> likeYnMap = new HashMap<String, Object>();
		likeYnMap.put("username", username);
		likeYnMap.put("mediaCd"	, mediaCd);
		
		String result = "";
		if (likeService.checkLikeYn(likeYnMap) == null) {
			likeService.addLike(likeDTO);
			
			result = "<script>";
			result += "location.href='"+request.getContextPath()+"/like/likesList?mediaCd="+mediaCd+"';";
			result += "</script>";
		}
		else {
			likeService.removeLike(likeDTO);
		}
		
		return result;
	}
	
	@GetMapping("/likesList")
	public ModelAndView likesList(@RequestParam("mediaCd") int mediaCd , HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("likesList");
		
		int onePageViewCnt = 10;
	    if (request.getParameter("onePageViewCnt") != null) {
	        onePageViewCnt = Integer.parseInt(request.getParameter("onePageViewCnt"));
	    }

	    String temp = request.getParameter("currentPageNumber");
	    int currentPageNumber = 1; 
	    if (temp != null) {
	        currentPageNumber = Integer.parseInt(temp);
	    }
	    
	    List<LikeDTO> allLikes = likeService.getLikesList(mediaCd);
		int allLikesCnt = allLikes.size();
		int allPageCnt = (int) Math.ceil((double) allLikesCnt / onePageViewCnt);
		
		int startIdx = (currentPageNumber - 1) * onePageViewCnt;
	    int endIdx = Math.min(startIdx + onePageViewCnt, allLikesCnt);

	    List<LikeDTO> likesList = allLikes.subList(startIdx, endIdx);

	    mv.addObject("startPage"		, 1);
	    mv.addObject("endPage"			, allPageCnt);
	    mv.addObject("allLikesCnt"		, allLikesCnt);
	    mv.addObject("allPageCnt"		, allPageCnt);
	    mv.addObject("onePageViewCnt"	, onePageViewCnt);
	    mv.addObject("currentPageNumber", currentPageNumber);
	    mv.addObject("likesList"		, likesList);
		
		return mv;
	}
	
	@GetMapping("/notifications")
	public ModelAndView myLikesList(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("notifications");
		mv.addObject("myLikesList"		, likeService.getMyLikesList(username));
		mv.addObject("myCommentsList"	, commentService.getMyCommentList(username));
		mv.addObject("myFollowersList"	, followService.getfollowersList(username));
		
		return mv;
		
	}
}
