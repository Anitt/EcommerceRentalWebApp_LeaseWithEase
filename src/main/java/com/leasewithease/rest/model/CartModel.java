package com.leasewithease.rest.model;

public class CartModel
{
	private String userEmail;
	private String productId;
	private String productName;
	private int quantity;
	private long cost;
	private long days;

	public long getCost() {
		return cost;
	}

	/**
	 * @return the days
	 */
	public long getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(long days) {
		this.days = days;
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setCost(long cost) {
		this.cost = cost;
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

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}