package com.application.snsms.post.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class PostDTO {

	private int mediaCd;
	private String username;
	private String photo;
	private String caption;
	private String sellYn;
	private int price;
	private int stock;
	private int deliveryPrice;
	private Date uploadDt;
	
	public int getMediaCd() {
		return mediaCd;
	}
	public void setMediaCd(int mediaCd) {
		this.mediaCd = mediaCd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getSellYn() {
		return sellYn;
	}
	public void setSellYn(String sellYn) {
		this.sellYn = sellYn;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(int deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	public Date getUploadDt() {
		return uploadDt;
	}
	public void setUploadDt(Date uploadDt) {
		this.uploadDt = uploadDt;
	}
	@Override
	public String toString() {
		return "PostDTO [mediaCd=" + mediaCd + ", username=" + username + ", photo=" + photo + ", caption=" + caption
				+ ", sellYn=" + sellYn + ", price=" + price + ", stock=" + stock + ", deliveryPrice=" + deliveryPrice
				+ ", uploadDt=" + uploadDt + "]";
	}
	
	
	
}
