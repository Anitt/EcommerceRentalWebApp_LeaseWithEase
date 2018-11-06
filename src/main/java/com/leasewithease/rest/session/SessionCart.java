package com.leasewithease.rest.session;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class SessionCart {
	private HashMap<String,HashMap<String, Integer>> products;

	public SessionCart() {
		products = new HashMap<>();
	}

	public void addToCart(String productId, int quantity, int days) {
		HashMap<String, Integer> item = new HashMap<String, Integer>();
		item.put("days", days);
		item.put("quantity", quantity);
		products.put(productId, item);
	}

	public void removeFromCart(String productId) {
		products.remove(productId);
	}

	public void updateQuantity(String productId, int newQuantity) {
		HashMap<String, Integer> item = products.get(productId);
		item.replace("quantity",newQuantity );
		//products.replace(productId, newQuantity);
	}
	public void updateDays(String productId, int newdays) {
		HashMap<String, Integer> item = products.get(productId);
		item.replace("days",newdays );
		//products.replace(productId, newQuantity);
	}
	public boolean isProductInCart(String productId) {
		return products.containsKey(productId);
	}

	public void clearCart() {
		products.clear();
	}

	public HashMap<String, 	HashMap<String, Integer>> getCartDetails() {
		return new HashMap<String, 	HashMap<String, Integer>>(products);
	}
}