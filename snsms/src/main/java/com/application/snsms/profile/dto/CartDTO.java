package com.application.snsms.profile.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CartDTO {

	private long cartCd;
	private int mediaCd;
	private int cartGoodsQty;
	private String username;
	private Date enrollDt;
	
	public long getCartCd() {
		return cartCd;
	}
	public void setCartCd(long cartCd) {
		this.cartCd = cartCd;
	}
	public int getMediaCd() {
		return mediaCd;
	}
	public void setMediaCd(int mediaCd) {
		this.mediaCd = mediaCd;
	}
	public int getCartGoodsQty() {
		return cartGoodsQty;
	}
	public void setCartGoodsQty(int cartGoodsQty) {
		this.cartGoodsQty = cartGoodsQty;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getEnrollDt() {
		return enrollDt;
	}
	public void setEnrollDt(Date enrollDt) {
		this.enrollDt = enrollDt;
	}
	@Override
	public String toString() {
		return "CartDTO [cartCd=" + cartCd + ", mediaCd=" + mediaCd + ", cartGoodsQty=" + cartGoodsQty + ", username="
				+ username + ", enrollDt=" + enrollDt + "]";
	}
	
	
}
