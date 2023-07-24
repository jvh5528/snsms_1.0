package com.application.snsms.admin.member.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.application.snsms.admin.member.dto.AdminDTO;
import com.application.snsms.admin.member.service.AdminMemberService;
import com.application.snsms.member.dto.MemberDTO;
import com.application.snsms.post.service.PostService;

@Controller
@RequestMapping("/admin")
public class AdminMemberController {

	@Autowired
	private AdminMemberService adminMemberService;
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/adminLogin")
	public ModelAndView adminLogin() throws Exception {
		return new ModelAndView("adminLogin");
	}
	
	@PostMapping("/adminLogin")
	public ResponseEntity<Object> adminLogin(AdminDTO adminDTO, HttpServletRequest request) throws Exception {
		
		String jsScript = "";
		
		if (adminMemberService.adminLogin(adminDTO)) { 	
			
			HttpSession session = request.getSession();		
			session.setAttribute("username" , adminDTO.getAdminId());
			session.setAttribute("role" , "admin");
			session.setMaxInactiveInterval(60 * 30);
			jsScript  = "<script>";
			jsScript += " alert('Welcome, snsmsadmin.');";
			jsScript += " location.href='" + request.getContextPath() + "/admin/adminHome';";
			jsScript += " </script>";
			
		}
		else { 
			
			jsScript  = "<script>";
			jsScript += " alert('Please check your username and/or password.');";
			jsScript += " history.go(-1);";
			jsScript += " </script>";
			
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
		
	}
	
	@GetMapping("/adminHome")
	public ModelAndView getExploreMediaList() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("adminHome");
		
		mv.addObject("mediaList" , postService.getExploreMediaList());
		
		return mv;
	}
	
	@GetMapping("/adminDailyNewMemberList")
	public ModelAndView adminDailyNewMemberList (HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dailyNewMemberList");
		mv.addObject("newMember", adminMemberService.getDailyNewMemberList());
		
		return mv;
	}
	
	@GetMapping("/adminMemberList")
	public ModelAndView adminMemberList(HttpServletRequest request)  throws Exception{
	
	ModelAndView mv = new ModelAndView();
	mv.setViewName("adminMemberList");
	
	int onePageViewCnt = 10;
    if (request.getParameter("onePageViewCnt") != null) {
        onePageViewCnt = Integer.parseInt(request.getParameter("onePageViewCnt"));
    }

    String temp = request.getParameter("currentPageNumber");
    int currentPageNumber = 1; 
    if (temp != null) {
        currentPageNumber = Integer.parseInt(temp);
    }
    
    List<MemberDTO> allMembers = adminMemberService.getMemberList();
    
    int allMembersCnt = allMembers.size();
    int allPageCnt = (int) Math.ceil((double) allMembersCnt / onePageViewCnt);

    int startIdx = (currentPageNumber - 1) * onePageViewCnt;
    int endIdx = Math.min(startIdx + onePageViewCnt, allMembersCnt);

    List<MemberDTO> memberList = allMembers.subList(startIdx, endIdx);

    mv.addObject("startPage", 1);
    mv.addObject("endPage", allPageCnt);
    mv.addObject("allMembersCnt", allMembersCnt);
    mv.addObject("allPageCnt", allPageCnt);
    mv.addObject("onePageViewCnt", onePageViewCnt);
    mv.addObject("currentPageNumber", currentPageNumber);
    mv.addObject("memberList", memberList);
	
	return mv;
		
	}
	
	@GetMapping("/dailyNewMemberExcelExport")
	public void dailyNewMemberExcelExport(HttpServletResponse response) throws Exception {
		  
		SimpleDateFormat joinSdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat fileSdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm");
		String makeFileTime = fileSdf.format(new Date());
		String makeFileName = makeFileTime + "_dailyNewMemberList.xls";
		
	    Workbook wb = new HSSFWorkbook();
	    Sheet sheet = wb.createSheet("dailyNewMemberList");
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
	    cell.setCellValue("Username");
	    cell = row.createCell(1);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("Name");
	    cell = row.createCell(2);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("Email");
	    cell = row.createCell(3);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("Date Enrolled");
	    
		for (MemberDTO memberDTO :  adminMemberService.getDailyNewMemberList()) {
			row = sheet.createRow(rowNo++);
	        cell = row.createCell(0);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(memberDTO.getUsername());
	        cell = row.createCell(1);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(memberDTO.getName());
	        cell = row.createCell(2);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(memberDTO.getEmail());
	        cell = row.createCell(3);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(joinSdf.format(memberDTO.getEnrollDt()));

		} 

	    response.setContentType("ms-vnd/excel");
	    response.setHeader("Content-Disposition", "attachment;filename="+makeFileName);

	    wb.write(response.getOutputStream());
	    wb.close();
	}
	
	@GetMapping("/memberExcelExport")
	public void memberExcelExport(HttpServletResponse response) throws Exception {
		  
		SimpleDateFormat joinSdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat fileSdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm");
		String makeFileTime = fileSdf.format(new Date());
		String makeFileName = makeFileTime + "_memberList.xls";
		
	    Workbook wb = new HSSFWorkbook();
	    Sheet sheet = wb.createSheet("MemberList");
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
	    cell.setCellValue("Username");
	    cell = row.createCell(1);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("Name");
	    cell = row.createCell(2);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("Email");
	    cell = row.createCell(3);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("Date Enrolled");
	    
		for (MemberDTO memberDTO :  adminMemberService.getMemberList()) {
			row = sheet.createRow(rowNo++);
	        cell = row.createCell(0);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(memberDTO.getUsername());
	        cell = row.createCell(1);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(memberDTO.getName());
	        cell = row.createCell(2);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(memberDTO.getEmail());
	        cell = row.createCell(3);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(joinSdf.format(memberDTO.getEnrollDt()));

		} 

	    response.setContentType("ms-vnd/excel");
	    response.setHeader("Content-Disposition", "attachment;filename="+makeFileName);

	    wb.write(response.getOutputStream());
	    wb.close();

		
	}
	
}
