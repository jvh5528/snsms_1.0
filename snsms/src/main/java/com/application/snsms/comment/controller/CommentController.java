package com.application.snsms.comment.controller;

import java.util.List;

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

import com.application.snsms.comment.dto.CommentDTO;
import com.application.snsms.comment.service.CommentService;
import com.application.snsms.post.service.PostService;
import com.application.snsms.profile.service.ProfileService;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private ProfileService profileService;
	
	@GetMapping("/newComment")
	public ModelAndView newComment(@RequestParam("mediaCd") int mediaCd, HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String myUsername = (String)session.getAttribute("username");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("newComment");
		
		mv.addObject("postDTO"		 , postService.getEditPost(mediaCd));
		mv.addObject("profileDTO"	 , profileService.getMemberDetail(myUsername));
		mv.addObject("recentComments", commentService.getCommentListRecent(mediaCd));
		return mv;
	}
	
	@PostMapping("/newComment")
	public @ResponseBody String newComment(CommentDTO commentDTO , HttpServletRequest request) throws Exception{
		
		commentService.addComment(commentDTO);
		
		String jsScript = "<script>";
		   	   jsScript += "alert('Posted.');";
		       jsScript += "location.href='"+request.getContextPath()+"/post/postDetailView?mediaCd="+commentDTO.getMediaCd()+"';"; 
		       jsScript += "</script>";
		       
		return jsScript;
	}
	
	
	@GetMapping("/commentList")
	public ModelAndView commentList(@RequestParam("mediaCd") int mediaCd , HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("commentList");
		
		int onePageViewCnt = 10;
	    if (request.getParameter("onePageViewCnt") != null) {
	        onePageViewCnt = Integer.parseInt(request.getParameter("onePageViewCnt"));
	    }

	    String temp = request.getParameter("currentPageNumber");
	    int currentPageNumber = 1; 
	    if (temp != null) {
	        currentPageNumber = Integer.parseInt(temp);
	    }
	    
	    List<CommentDTO> allComments = commentService.getCommentList(mediaCd);
	    int allCommentsCnt = allComments.size();
	    int allPageCnt = (int) Math.ceil((double) allCommentsCnt / onePageViewCnt);

	    int startIdx = (currentPageNumber - 1) * onePageViewCnt;
	    int endIdx = Math.min(startIdx + onePageViewCnt, allCommentsCnt);

	    List<CommentDTO> commentList = allComments.subList(startIdx, endIdx);
	    
	    mv.addObject("startPage"		, 1);
	    mv.addObject("endPage"			, allPageCnt);
	    mv.addObject("allPostCnt"		, allCommentsCnt);
	    mv.addObject("allPageCnt"		, allPageCnt);
	    mv.addObject("onePageViewCnt"	, onePageViewCnt);
	    mv.addObject("currentPageNumber", currentPageNumber);
	    mv.addObject("commentList"		, commentList);
		mv.addObject("postDTO"			, postService.getEditPost(mediaCd));
		
		return mv;
	}
	
}
