package com.application.snsms.admin.post.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.application.snsms.post.dto.PostDTO;
import com.application.snsms.post.service.PostService;

@Controller
@RequestMapping("/admin/posts")
public class AdminPostController {

	@Autowired
	private PostService postService;
	
	
	@GetMapping("/adminPostsList")
	public ModelAndView adminPostsList(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("adminPostsList");
		
		int onePageViewCnt = 10;
	    if (request.getParameter("onePageViewCnt") != null) {
	        onePageViewCnt = Integer.parseInt(request.getParameter("onePageViewCnt"));
	    }

	    String temp = request.getParameter("currentPageNumber");
	    int currentPageNumber = 1;
	    if (temp != null) {
	        currentPageNumber = Integer.parseInt(temp);
	    }
	    
	    List<PostDTO> allPosts = postService.getExploreMediaList();
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
	
	@GetMapping("/postsExcelExport")
	public void postsExcelExport(HttpServletResponse response) throws Exception {
		  
		SimpleDateFormat fileSdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm");
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		String makeFileTime = fileSdf.format(new Date());
		String makeFileName = makeFileTime + "_postsList.xls";
		
	    Workbook wb = new HSSFWorkbook();
	    Sheet sheet = wb.createSheet("user_posts_list");
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
	    cell.setCellValue("Post no.");
	    cell = row.createCell(1);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("Username");
	    cell = row.createCell(2);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("Price");
	    cell = row.createCell(3);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("Date posted");

		for (PostDTO postDTO :  postService.getExploreMediaList()) {
			row = sheet.createRow(rowNo++);
	        cell = row.createCell(0);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(postDTO.getMediaCd());
	        cell = row.createCell(1);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(postDTO.getUsername());
	        cell = row.createCell(2);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(postDTO.getPrice());
	        cell = row.createCell(3);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(dateSdf.format(postDTO.getUploadDt()));
		} 

	    response.setContentType("ms-vnd/excel");
	    response.setHeader("Content-Disposition", "attachment;filename=" + makeFileName);

	    wb.write(response.getOutputStream());
	    wb.close();
	}
}
