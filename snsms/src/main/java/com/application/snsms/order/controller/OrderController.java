package com.application.snsms.order.controller;

import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.application.snsms.member.service.MemberService;
import com.application.snsms.order.dto.OrderDTO;
import com.application.snsms.order.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/orderGoods")
	public ModelAndView orderGoods(@RequestParam("mediaCd") int mediaCd , @RequestParam("orderMediaQty") int orderMediaQty , HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();  			
		mv.setViewName("orderGoods");
		
		HttpSession session = request.getSession();
		
		mv.addObject("orderer"       	, orderService.getOrdererDetail((String)session.getAttribute("username")));
		mv.addObject("postDTO"      	, orderService.getMediaDetail(mediaCd));
		mv.addObject("orderMediaQty" 	, orderMediaQty);
		
		return mv;
		
	}
	
	@PostMapping("/orderGoods")
	public ResponseEntity<Object> orderGoods(OrderDTO orderDTO ,  HttpServletRequest request) throws Exception{
		
		orderService.addOrder(orderDTO);
		
		HttpSession session = request.getSession();
		session.setAttribute("myOrderCnt" , memberService.getMyOrderCnt(orderDTO.getUsername()));
		
		String jsScript= "<script>";
				jsScript += " alert('Your order has been placed.');";
				jsScript +=" location.href='" + request.getContextPath() + "/post/postDetailView?mediaCd=" + orderDTO.getMediaCd()+"';";
				jsScript +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
			
		return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
		
	}
	
	@GetMapping("/orderCartGoods")
	public ModelAndView orderCartGoods(@RequestParam("mediaCdList") String mediaCds , 
									   @RequestParam("cartGoodsQtyList") String cartGoodsQtyList , 
									   @RequestParam("cartCdList") String cartCdList ,
									   HttpServletRequest request) throws Exception{

	String[] temp = mediaCds.split(",");
	int[] mediaCdList = new int[temp.length];
	
	for (int i = 0; i < mediaCdList.length; i++) {
		mediaCdList[i] = Integer.parseInt(temp[i]);
	}
	
	ModelAndView mv = new ModelAndView();  			
	mv.setViewName("orderCartGoods");
	
	HttpSession session = request.getSession();
	session.setAttribute("myOrderCnt", memberService.getMyOrderCnt((String)session.getAttribute("username")));
	session.setAttribute("myCartCnt" , memberService.getMyCartCnt((String)session.getAttribute("username")));
	
	
	mv.addObject("orderer"           , orderService.getOrdererDetail((String)session.getAttribute("username")));
	mv.addObject("mediaList"         , orderService.getMediaListByCart(mediaCdList , (String)session.getAttribute("username")));
	mv.addObject("orderMediaQtyList" , cartGoodsQtyList);
	mv.addObject("mediaCdList"       , mediaCds);
	mv.addObject("cartCdList"        , cartCdList);
	
	return mv;
	
	}
	
	@PostMapping("/orderCartGoods")
	public ResponseEntity<Object> orderCartGoods(@RequestParam Map<String, String> orderListMap , HttpServletRequest request) throws Exception{
		
		orderService.addOrderByCart(orderListMap);
		
		HttpSession session = request.getSession();
		session.setAttribute("myOrderCnt", memberService.getMyOrderCnt(orderListMap.get("username")));
		session.setAttribute("myCartCnt", memberService.getMyCartCnt(orderListMap.get("username")));
		
		String jsScript = "<script>";
		   jsScript += "alert('Your order has been placed.');";
		   jsScript += "location.href='" + request.getContextPath() + "/profile/myOrderList'";
		   jsScript +="</script>";
	
		 HttpHeaders responseHeaders = new HttpHeaders();
		 responseHeaders.add("Content-Type", "text/html; charset=utf-8");
			
		 return new ResponseEntity<Object>(jsScript, responseHeaders, HttpStatus.OK);
	}
}
