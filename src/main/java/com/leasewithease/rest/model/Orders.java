package com.leasewithease.rest.model;

public class Orders {
	private String userEmail;
	private String productId;
	private int quantity;
	private long cost;
	private boolean paymentMade;

	public long getCost() {
		return cost;
	}

	public String getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public boolean isPaymentMade() {
		return paymentMade;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}

	public void setPaymentMade(boolean paymentMade) {
		this.paymentMade = paymentMade;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}