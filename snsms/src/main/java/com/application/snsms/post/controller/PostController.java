package com.application.snsms.post.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.application.snsms.comment.service.CommentService;
import com.application.snsms.follow.service.FollowService;
import com.application.snsms.like.service.LikeService;
import com.application.snsms.member.service.MemberService;
import com.application.snsms.post.dto.PostDTO;
import com.application.snsms.post.service.PostService;
import com.application.snsms.profile.service.ProfileService;

@Controller
@RequestMapping("/post")
public class PostController {

	private final String FILE_REPO_PATH = "/Users/jessicahong/Documents/FILE_REPO_PATH/";
	
	//private final String FILE_REPO_PATH = "/var/lib/tomcat9/file_repo/";	// linux
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private FollowService followService;
	
	@Autowired
	private LikeService likeService;
	
	
	@GetMapping("/newPost")
	public ModelAndView newPost(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("newPost");
		mv.addObject("memberDTO", memberService.getMemberDetail((String)session.getAttribute("username")));
		
		return mv;
	}
	
	@PostMapping("/newPost")
	public @ResponseBody String newPost(MultipartHttpServletRequest multipartRequest , HttpServletRequest request) throws Exception{
		
		Iterator<String> fileList = multipartRequest.getFileNames();
		String fileName = "";
		if (fileList.hasNext()) {
			MultipartFile uploadFile = multipartRequest.getFile(fileList.next());
			if (!uploadFile.getOriginalFilename().isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				fileName = sdf.format(new Date()) + "_" + UUID.randomUUID() + "_" + uploadFile.getOriginalFilename();
				uploadFile.transferTo(new File(FILE_REPO_PATH + fileName));
			}
		}
		PostDTO postDTO = new PostDTO();
		postDTO.setUsername(multipartRequest.getParameter("username"));
		postDTO.setCaption(multipartRequest.getParameter("caption"));
		postDTO.setPhoto(fileName);
		if (multipartRequest.getParameter("sellYn") == null)		postDTO.setSellYn("N");
		else													    postDTO.setSellYn("Y");
		String stock = multipartRequest.getParameter("stock");
			if(stock == null) {
				stock = "0";
			}
		int stockNum = Integer.parseInt(stock);
		postDTO.setStock(stockNum);
		String price = multipartRequest.getParameter("price");
			if(price == null) {
				price = "0";
			}
		int num = Integer.parseInt(price);
		postDTO.setPrice(num);
		String deliveryPrice = multipartRequest.getParameter("deliveryPrice");
			if (deliveryPrice == null) {
				deliveryPrice = "0";
			}
		int deliveryPriceNum = Integer.parseInt(deliveryPrice);
		postDTO.setDeliveryPrice(deliveryPriceNum);
		postService.uploadPost(postDTO);
		
		String jsScript = "<script>";
			   jsScript += "alert('Posted!');";
			   jsScript += "location.href='"+request.getContextPath()+"/profile/myProfile';";
			   jsScript += "</script>";
		
		return jsScript;
		
	}
	
	@GetMapping("/postDetailView")
	public ModelAndView postDetailView(@RequestParam("mediaCd") int mediaCd , HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("postDetailView");
		mv.addObject("postDTO"		  , postService.getPostDetail(mediaCd));
		mv.addObject("myProfile" 	  , profileService.getMemberDetail(username));
		mv.addObject("commentList"	  , commentService.getCommentList(mediaCd));
		mv.addObject("totalCommentCnt", commentService.getAllCommentCnt(mediaCd));
		mv.addObject("totalLikeCnt"	  , likeService.getAllLikesCnt(mediaCd));
		mv.addObject("sellYn"		  , postService.checkIfProduct(mediaCd));
		
		Map<String, Object> cartDupleMap = new HashMap<String, Object>();
		cartDupleMap.put("username"	, username);
		cartDupleMap.put("mediaCd"	, mediaCd);
		mv.addObject("cartYn" 		, profileService.checkDuplicateCart(cartDupleMap) );
		
		Map<String,Object> likeYnMap = new HashMap<String, Object>();
		likeYnMap.put("username", username);
		likeYnMap.put("mediaCd"	, mediaCd);
		
		mv.addObject("likeCheck", likeService.checkLikeYn(likeYnMap));
		
		return mv;
	}
	
	
	@GetMapping("/editPost")
	public ModelAndView editPost(@RequestParam("mediaCd") int mediaCd) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		PostDTO postDTO = postService.getEditPost(mediaCd);
		mv.setViewName("editPost");
		mv.addObject("postDTO"		  , postDTO);
		mv.addObject("totalCommentCnt", commentService.getAllCommentCnt(mediaCd));
		mv.addObject("commentList"	  , commentService.getCommentList(mediaCd));
		mv.addObject("likesCnt"		  , likeService.getAllLikesCnt(mediaCd));
		
		return mv;
	}
	
	@PostMapping("/editPost")
	public @ResponseBody String editPost(MultipartHttpServletRequest multipartRequest, HttpServletRequest request) throws Exception{
		
		PostDTO postDTO = new PostDTO();
		postDTO.setMediaCd(Integer.parseInt(multipartRequest.getParameter("mediaCd")));
		postDTO.setUsername(multipartRequest.getParameter("username"));
		postDTO.setCaption(multipartRequest.getParameter("caption"));
		postDTO.setPhoto(multipartRequest.getParameter("photo"));
		if (multipartRequest.getParameter("sellYn") == null)		postDTO.setSellYn("N");
		else													    postDTO.setSellYn("Y");
		String stock = multipartRequest.getParameter("stock");
		if(stock == null) {
			stock = "0";
		}
		int stockNum = Integer.parseInt(stock);
		postDTO.setStock(stockNum);
		String price = multipartRequest.getParameter("price");
			if(price == null) {
				price = "0";
			}
		int num = Integer.parseInt(price);
		postDTO.setPrice(num);
		String deliveryPrice = multipartRequest.getParameter("deliveryPrice");
			if (deliveryPrice == null) {
				deliveryPrice = "0";
			}
		int deliveryPriceNum = Integer.parseInt(deliveryPrice);
		postDTO.setDeliveryPrice(deliveryPriceNum);
		postService.editPost(postDTO);
		
		String jsScript = "<script>";
			   jsScript += "alert('Post has been edited.');";
			   jsScript += "location.href='"+request.getContextPath()+"/profile/myProfile';";
			   jsScript += "</script>";
		
		return jsScript;
	}
	
	@GetMapping("/deletePost")
	public ModelAndView deletePost(@RequestParam("mediaCd") int mediaCd)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("deletePost");
		mv.addObject("postDTO"			, postService.getEditPost(mediaCd));
		mv.addObject("totalCommentCnt"	, commentService.getAllCommentCnt(mediaCd));
		mv.addObject("totalLikesCnt"	, likeService.getAllLikesCnt(mediaCd));
		return mv;
	}
	
	@PostMapping("/deletePost")
	public @ResponseBody String deletePost(@RequestParam("mediaCd") int mediaCd , HttpServletRequest request) throws Exception{
		
		String jsScript = "";
		
		File file = new File(FILE_REPO_PATH + postService.getDeletePost(mediaCd));
		
		
		if(file.exists()) {
			postService.deletePost(mediaCd);
			file.delete();
			
			jsScript = "<script>";
			jsScript += "alert('Post has been deleted.');";
			jsScript += "location.href='"+request.getContextPath()+"/profile/myProfile';";
			jsScript += "</script>";
		}
		else {
			jsScript = "<script>";
			jsScript += "alert('Request failed. Please try again later.');";
			jsScript += "location.href='"+request.getContextPath()+"/profile/myProfile';";
			jsScript += "</script>";
		}
		
		return jsScript;
	}

	@GetMapping("/getHomeMediaList")
	public ModelAndView getHomeMediaList(HttpServletRequest request) throws Exception {
	    HttpSession session = request.getSession();
	    String username = (String) session.getAttribute("username");

	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("homeMediaList");

	    int onePageViewCnt = 9;
	    if (request.getParameter("onePageViewCnt") != null) {
	        onePageViewCnt = Integer.parseInt(request.getParameter("onePageViewCnt"));
	    }

	    String temp = request.getParameter("currentPageNumber");
	    int currentPageNumber = 1; 
	    if (temp != null) {
	        currentPageNumber = Integer.parseInt(temp);
	    }

	    List<String> followingList = followService.getFollowingUsernameList(username);

	    Map<String, Object> followingMap = new HashMap<>();
	    followingMap.put("username", username);
	    followingMap.put("followingList", followingList);

	    List<PostDTO> allPosts = postService.getHomeMediaList(followingMap);
	    int allPostCnt = allPosts.size();
	    int allPageCnt = (int) Math.ceil((double) allPostCnt / onePageViewCnt);

	    int startIdx = (currentPageNumber - 1) * onePageViewCnt;
	    int endIdx = Math.min(startIdx + onePageViewCnt, allPostCnt);

	    List<PostDTO> mediaList = allPosts.subList(startIdx, endIdx);

	    mv.addObject("startPage"		, 1);
	    mv.addObject("endPage"			, allPageCnt);
	    mv.addObject("allPostCnt"		, allPostCnt);
	    mv.addObject("allPageCnt"		, allPageCnt);
	    mv.addObject("onePageViewCnt"	, onePageViewCnt);
	    mv.addObject("currentPageNumber", currentPageNumber);
	    mv.addObject("mediaList"		, mediaList);

	    return mv;
	}

	
	@GetMapping("/getExploreMediaList")
	public ModelAndView getExploreMediaList() throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("exploreMediaList");
		
		mv.addObject("mediaList" , postService.getExploreMediaList());
		
		return mv;
	}
}
