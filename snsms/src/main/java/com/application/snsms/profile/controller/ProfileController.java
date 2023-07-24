package com.application.snsms.profile.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.application.snsms.follow.service.FollowService;
import com.application.snsms.member.dto.MemberDTO;
import com.application.snsms.member.service.MemberService;
import com.application.snsms.order.dto.OrderDTO;
import com.application.snsms.post.dto.PostDTO;
import com.application.snsms.post.service.PostService;
import com.application.snsms.profile.dto.CartDTO;
import com.application.snsms.profile.dto.ProfileDTO;
import com.application.snsms.profile.service.ProfileService;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	private final String FILE_REPO_PATH = "/Users/jessicahong/Documents/FILE_REPO_PATH/";
	
	//private final String FILE_REPO_PATH = "/var/lib/tomcat9/file_repo/";	// linux
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private FollowService followService;
	
	@Autowired
	private PostService postService;
	
	
	@GetMapping("/newProfile")
	public ModelAndView newProfile(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("newProfile");
		mv.addObject("memberDTO", memberService.getMemberDetail((String)session.getAttribute("username"))); 
		
		return mv;
	}
	
	@PostMapping("/newProfile")
	public ResponseEntity<Object> newProfile(MultipartHttpServletRequest multipartRequest , HttpServletRequest request) throws Exception{
		
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
		
		ProfileDTO profileDTO = new ProfileDTO();
		profileDTO.setUsername(multipartRequest.getParameter("username"));
		profileDTO.setName(multipartRequest.getParameter("name"));
		profileDTO.setBio(multipartRequest.getParameter("bio"));
		profileDTO.setGender(multipartRequest.getParameter("gender"));
		profileDTO.setCustomGender(multipartRequest.getParameter("customGender"));
		profileDTO.setProfilePic(fileName);
		
		profileService.addProfile(profileDTO);
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUserStatus(multipartRequest.getParameter("userStatus"));
		memberDTO.setUsername(profileDTO.getUsername());
		memberService.changeMemberStatus(memberDTO);
		
		String jsScript = "<script>";
			   jsScript += "alert('Changes have been made to your profile.');";
			   jsScript += "location.href='"+request.getContextPath()+"/profile/myProfile';";
			   jsScript += "</script>";
		
	   HttpHeaders responseHeaders = new HttpHeaders();
	   responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
	   return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/passwordRenewal")
	public ModelAndView passwordRenewal(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("passwordRenewal");
		mv.addObject("username", (String)session.getAttribute("username"));
		
		return mv;
	}
	
	@PostMapping("/passwordRenewal")
	public @ResponseBody String passwordRenewal(@RequestParam("passwd") String passwd, HttpServletRequest request) throws Exception {
	    
		String username = (String) request.getSession().getAttribute("username");
	    
	    MemberDTO memberDTO = new MemberDTO();
	    memberDTO.setUsername(username);
	    memberDTO.setPasswd(passwd);

	    memberService.updatePassword(memberDTO);
	    String jsScript = "<script>";
	        jsScript += "alert('Your password has been successfully updated.');";
	        jsScript += "location.href='" + request.getContextPath() + "/profile/myProfile';";
	        jsScript += "</script>";

	    return jsScript;
	}


	@GetMapping("/myProfile")
	public ModelAndView myProfile(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String myUsername = (String)session.getAttribute("username");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("myProfile");
		
		int onePageViewCnt = 9;
	    if (request.getParameter("onePageViewCnt") != null) {
	        onePageViewCnt = Integer.parseInt(request.getParameter("onePageViewCnt"));
	    }

	    String temp = request.getParameter("currentPageNumber");
	    int currentPageNumber = 1; 
	    if (temp != null) {
	        currentPageNumber = Integer.parseInt(temp);
	    }
	    
	    List<PostDTO> allPosts = postService.getPostList(myUsername);    
	    int allPostCnt = allPosts.size();
	    int allPageCnt = (int) Math.ceil((double) allPostCnt / onePageViewCnt); 
	    
	    int startIdx = (currentPageNumber - 1) * onePageViewCnt;
	    int endIdx = Math.min(startIdx + onePageViewCnt, allPostCnt);
	    
	    List<PostDTO> postList = allPosts.subList(startIdx, endIdx);
		
	    mv.addObject("startPage"		, 1);
	    mv.addObject("endPage"			, allPageCnt);
	    mv.addObject("allPostCnt"		, allPostCnt);
	    mv.addObject("allPageCnt"		, allPageCnt);
	    mv.addObject("onePageViewCnt"	, onePageViewCnt);
	    mv.addObject("currentPageNumber", currentPageNumber);
	    mv.addObject("postList"			, postList);

		mv.addObject("memberDTO"		, memberService.getMemberDetail(myUsername)); 
		mv.addObject("profileDTO"		, profileService.getProfileDetail(myUsername));
		mv.addObject("followingList"	, followService.getFollowingList(myUsername));
		mv.addObject("followersList"	, followService.getfollowersList(myUsername));
		mv.addObject("myFollowingCnt"	, followService.getFollowingCnt(myUsername));
		mv.addObject("myFollowersCnt" 	, followService.getFollowersCnt(myUsername));
		mv.addObject("myCartCnt"		, profileService.countCartList(myUsername));
		
		return mv;
	}

	
	@GetMapping("/editProfile")
	public ModelAndView editProfile(HttpServletRequest request)throws Exception{
		
		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("editProfile");
		mv.addObject("memberDTO"	, memberService.getMemberDetail((String)session.getAttribute("username")));
		mv.addObject("profileDTO"	, profileService.getProfileDetail((String)session.getAttribute("username")));
		
		return mv;
	}
	
	@PostMapping("/editProfile")
	public @ResponseBody String editProfile(MultipartHttpServletRequest multipartRequest , HttpServletRequest request) throws Exception{
		Iterator<String> fileList = multipartRequest.getFileNames();
		String fileName = "";
		if (fileList.hasNext()) {
			MultipartFile uploadFile = multipartRequest.getFile(fileList.next());
			if (!uploadFile.getOriginalFilename().isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				fileName = sdf.format(new Date()) + "_" + UUID.randomUUID() + "_" + uploadFile.getOriginalFilename();
				uploadFile.transferTo(new File(FILE_REPO_PATH + fileName));
				new File(FILE_REPO_PATH + multipartRequest.getParameter("beforeFileName")).delete();
			}
		}
		
		ProfileDTO profileDTO = new ProfileDTO();
		profileDTO.setUsername(multipartRequest.getParameter("username"));
		profileDTO.setName(multipartRequest.getParameter("name"));
		profileDTO.setBio(multipartRequest.getParameter("bio"));
		profileDTO.setGender(multipartRequest.getParameter("gender"));
		profileDTO.setCustomGender(multipartRequest.getParameter("customGender"));
		profileDTO.setProfilePic(fileName);
		
		profileService.editProfile(profileDTO);
		
		String jsScript = "<script>";
			   jsScript += "alert('Changes have been made to your profile.');";
			   jsScript += "location.href='"+request.getContextPath()+"/profile/myProfile';";
			   jsScript += "</script>";
		
		return jsScript;
	}
	
	
	@GetMapping("/thumbnails")
	public void thumbnails(@RequestParam("fileName") String fileName , HttpServletResponse response) throws Exception{
		
		OutputStream out = response.getOutputStream();
		File image = new File(FILE_REPO_PATH + fileName);
		if (image.exists()) {
			Thumbnails.of(image).size(800,800).outputFormat("png").toOutputStream(out);
		}
		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
		out.close();
	}
	
	
	@GetMapping("/memberList")
	public ModelAndView memberList(@ModelAttribute ProfileDTO profileDTO) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("memberList");
		mv.addObject("memberList" , profileService.getMemberList());
		
		return mv;
		
	}
	
	
	@GetMapping("/searchMember")
	public ModelAndView memberDetail(@RequestParam Map<String, Object> searchMap) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("memberList");
		mv.addObject("memberList", profileService.getSearchMemberList(searchMap));
		
		return mv;
	}
	
	@GetMapping("/memberDetail")
	public ModelAndView memberDetail(@RequestParam("username") String username, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String myUsername = (String)session.getAttribute("username");
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("memberDetail");
		
		int onePageViewCnt = 9;
	    if (request.getParameter("onePageViewCnt") != null) {
	        onePageViewCnt = Integer.parseInt(request.getParameter("onePageViewCnt"));
	    }

	    String temp = request.getParameter("currentPageNumber");
	    int currentPageNumber = 1; 
	    if (temp != null) {
	        currentPageNumber = Integer.parseInt(temp);
	    }
	    
	    List<PostDTO> allPosts = postService.getPostList(username);    
	    int allPostCnt = allPosts.size();
	    int allPageCnt = (int) Math.ceil((double) allPostCnt / onePageViewCnt); 
	    
	    int startIdx = (currentPageNumber - 1) * onePageViewCnt;
	    int endIdx = Math.min(startIdx + onePageViewCnt, allPostCnt);
	    
	    List<PostDTO> postList = allPosts.subList(startIdx, endIdx);
	    
	    mv.addObject("startPage"		, 1);
	    mv.addObject("endPage"			, allPageCnt);
	    mv.addObject("allPostCnt"		, allPostCnt);
	    mv.addObject("allPageCnt"		, allPageCnt);
	    mv.addObject("onePageViewCnt"	, onePageViewCnt);
	    mv.addObject("currentPageNumber", currentPageNumber);
	    mv.addObject("postList"			, postList);
	    
		mv.addObject("memberDTO"	, memberService.getMemberDetail(myUsername));
		mv.addObject("profileDTO"	, profileService.getMemberDetail(username));
		mv.addObject("myProfileDTO"	, profileService.getMemberDetail(myUsername));
		mv.addObject("followersCnt"	, followService.getFollowersCnt(username));
		mv.addObject("followingCnt"	, followService.getFollowingCnt(username));
		mv.addObject("followingList", followService.getFollowingList(username));
		mv.addObject("followersList", followService.getfollowersList(username));
		
		Map<String,String> followYnMap = new HashMap<String, String>();
		followYnMap.put("username", username);
		followYnMap.put("myUsername", myUsername);
		
		mv.addObject("followCheck"	, followService.followYn(followYnMap));
		
		return mv;
	}
	
	@GetMapping("/addCart")
	public @ResponseBody String addCart(@RequestParam ("mediaCd") int mediaCd , @RequestParam ("cartGoodsQty") int cartGoodsQty , HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		CartDTO cartDTO = new CartDTO();
		cartDTO.setUsername(username);
		cartDTO.setMediaCd(mediaCd);
		cartDTO.setCartGoodsQty(cartGoodsQty);
		
		Map<String, Object> cartDupleMap = new HashMap<String, Object>();
		cartDupleMap.put("username"		, username);
		cartDupleMap.put("mediaCd"		, mediaCd);
		cartDupleMap.put("cartGoodsQty" , cartGoodsQty);
		
		String result = "duple";
		if (!profileService.checkDuplicateCart(cartDupleMap)) {
			profileService.addCart(cartDTO);
			session.setAttribute("myCartCnt", memberService.getMyCartCnt(username));
			result = "notDuple";
		}
		
		return result;
	}
	
	@GetMapping("/myOrderList")
	public ModelAndView myOrderList(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("myOrderList");
		
		int onePageViewCnt = 10;
	    if (request.getParameter("onePageViewCnt") != null) {
	        onePageViewCnt = Integer.parseInt(request.getParameter("onePageViewCnt"));
	    }

	    String temp = request.getParameter("currentPageNumber");
	    int currentPageNumber = 1;
	    if (temp != null) {
	        currentPageNumber = Integer.parseInt(temp);
	    }
	    
	    List<Map<String, Object>> allMyOrders = profileService.getMyOrderList((String)session.getAttribute("username"));
	    int allOrdersCnt = allMyOrders.size();
	    int allPageCnt = (int) Math.ceil((double) allOrdersCnt / onePageViewCnt);

	    int startIdx = (currentPageNumber - 1) * onePageViewCnt;
	    int endIdx = Math.min(startIdx + onePageViewCnt, allOrdersCnt);

	    List<Map<String, Object>> myOrderList = allMyOrders.subList(startIdx, endIdx);

	    mv.addObject("startPage"		, 1);
	    mv.addObject("endPage"			, allPageCnt);
	    mv.addObject("allOrdersCnt"		, allOrdersCnt);
	    mv.addObject("allPageCnt"		, allPageCnt);
	    mv.addObject("onePageViewCnt"	, onePageViewCnt);
	    mv.addObject("currentPageNumber", currentPageNumber);
	    mv.addObject("myOrderList"		, myOrderList);
	    
		return mv;
	}
	
	@GetMapping("/myOrderDetail")
	public ModelAndView myOrderDetail(@RequestParam Map<String, Object> orderDetailMap) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("myOrderDetail");
		mv.addObject("myOrder" , profileService.getMyOrderDetail(orderDetailMap));
		
		return mv;
		
	}
	
	@GetMapping("/myCartList")
	public ModelAndView myCartList(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("myCartList");
		
		mv.addObject("myCartList" 		, profileService.getMyCartList(username));
		mv.addObject("countCartList" 	, profileService.countCartList(username));
		return mv;
		
	}
	
	@GetMapping("/myIncomingOrderList")
	public ModelAndView myIncomingOrderList(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("myIncomingOrderList");
		
		int onePageViewCnt = 10;
	    if (request.getParameter("onePageViewCnt") != null) {
	        onePageViewCnt = Integer.parseInt(request.getParameter("onePageViewCnt"));
	    }

	    String temp = request.getParameter("currentPageNumber");
	    int currentPageNumber = 1; 
	    if (temp != null) {
	        currentPageNumber = Integer.parseInt(temp);
	    }
	    
	    List<Map<String, Object>> allIncomingOrders = profileService.getIncomingOrderList((String)session.getAttribute("username"));
	    int allIncomingOrderCnt = allIncomingOrders.size();
	    int allPageCnt = (int) Math.ceil((double) allIncomingOrderCnt / onePageViewCnt);

	    int startIdx = (currentPageNumber - 1) * onePageViewCnt;
	    int endIdx = Math.min(startIdx + onePageViewCnt, allIncomingOrderCnt);

	    List<Map<String, Object>> incomingOrderList = allIncomingOrders.subList(startIdx, endIdx);

	    mv.addObject("startPage"			, 1);
	    mv.addObject("endPage"				, allPageCnt);
	    mv.addObject("allIncomingOrderCnt"	, allIncomingOrderCnt);
	    mv.addObject("allPageCnt"			, allPageCnt);
	    mv.addObject("onePageViewCnt"		, onePageViewCnt);
	    mv.addObject("currentPageNumber"	, currentPageNumber);
	    mv.addObject("incomingOrderList"	, incomingOrderList);

		return mv;
		
	}
	
	@GetMapping("/incomingOrderDetail")
	public ModelAndView incomingOrderDetail(@RequestParam Map<String, Object> orderDetailMap) throws Exception {
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("incomingOrderDetail");
		mv.addObject("incomingOrder" , profileService.getMyOrderDetail(orderDetailMap));
		
		return mv;
		
	}
	
	@GetMapping("/removeCart")
	public ResponseEntity<Object> removeCart(@RequestParam("cartCdList") String cartCdList , HttpServletRequest request) throws Exception {
		
		String[] temp = cartCdList.split(",");
		int[] deleteCartCdList = new int[temp.length];

		for (int i = 0; i < temp.length; i++) {
			deleteCartCdList[i] = Integer.parseInt(temp[i]);
		}
		
		profileService.removeCart(deleteCartCdList);
		
		HttpSession session = request.getSession();
		session.setAttribute("myCartCnt" , memberService.getMyCartCnt((String)session.getAttribute("username")));
		
		String jsScript = "<script>";
			   jsScript += "alert('Selected items have been removed from your cart.');";
			   jsScript += "location.href='myCartList';";
			   jsScript += "</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/modfiyCartGoodsQty")
	public ResponseEntity<Object> modifyGoodsQty(@RequestParam Map<String,Object> updateMap) throws Exception {
		profileService.modifyCartGoodsQty(updateMap);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@GetMapping("/incomingOrderExcelExport")
	public void incomingOrderExcelExport(HttpServletResponse response , HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();

		SimpleDateFormat orderTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat fileSdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm");
		String makeFileTime = fileSdf.format(new Date());
		String makeFileName = makeFileTime + "_orderList.xls";
		
	    Workbook wb = new HSSFWorkbook();
	    Sheet sheet = wb.createSheet("order_list");
	    Row row = null;
	    Cell cell = null;

	    int rowNo = 0;

	    CellStyle headStyle = wb.createCellStyle();
	    headStyle.setBorderTop(BorderStyle.THIN);
	    headStyle.setBorderBottom(BorderStyle.THIN);
	    headStyle.setBorderLeft(BorderStyle.THIN);
	    headStyle.setBorderRight(BorderStyle.THIN);

	    headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
	    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    headStyle.setAlignment(HorizontalAlignment.CENTER);

	    CellStyle bodyStyle = wb.createCellStyle();
	    bodyStyle.setBorderTop(BorderStyle.THIN);
	    bodyStyle.setBorderBottom(BorderStyle.THIN);
	    bodyStyle.setBorderLeft(BorderStyle.THIN);
	    bodyStyle.setBorderRight(BorderStyle.THIN);

	    row = sheet.createRow(rowNo++);
	    cell = row.createCell(0);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("Media no.");
	    cell = row.createCell(1);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("Photo");
	    cell = row.createCell(2);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("Date");
	    cell = row.createCell(3);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("Username");
	    cell = row.createCell(4);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("Price");
	    cell = row.createCell(5);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("Quantity");
	    cell = row.createCell(6);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("Delivery status");

	    
		for (Map<String,Object> incomingOrderListMap :  profileService.getIncomingOrderList((String)session.getAttribute("username"))) {
			row = sheet.createRow(rowNo++);
	        cell = row.createCell(0);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue((Integer)incomingOrderListMap.get("mediaCd"));
	        cell = row.createCell(1);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue((String)incomingOrderListMap.get("photo"));
	        cell = row.createCell(2);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(orderTime.format(incomingOrderListMap.get("payOrderTime")));
	        cell = row.createCell(3);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue((String)incomingOrderListMap.get("username"));
	        cell = row.createCell(4);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue((Integer)incomingOrderListMap.get("price"));
	        cell = row.createCell(5);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue((Integer)incomingOrderListMap.get("orderMediaQty"));
	        cell = row.createCell(6);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue((String)incomingOrderListMap.get("deliveryStatus"));
	        
		} 

	    response.setContentType("ms-vnd/excel");
	    response.setHeader("Content-Disposition", "attachment;filename=" + makeFileName);

	    wb.write(response.getOutputStream());
	    wb.close();
		
		
	}
}


