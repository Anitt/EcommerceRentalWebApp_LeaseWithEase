package com.leasewithease.rest.model;

import java.sql.Blob;

public class Products {
	private String productId;
	private String productName;
	private String categoryName;
	private String description;
	private int rent;
	private int quantity;
	private Blob image;
	private String registrationNo;
	private String categoryId ;

	public String getCategoryName() {
		return categoryName;
	}

	public String getDescription() {
		return description;
	}

	public Blob getImage() {
		return image;
	}

	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public int getRent() {
		return rent;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}
	// to reterive products by category_Id
	public String getCategoryId(){

		return categoryId ;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}