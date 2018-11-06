package com.leasewithease.rest.controller;

import java.util.HashMap;

public class Category {
	public static HashMap<String,String> categories = new HashMap<String,String>();

	public static void initializeCategories(){
		categories.put("1", "Camera");
		categories.put("2", "Cell Phone");
		categories.put("3", "Desktop");
		categories.put("4", "Headphone");
		categories.put("5", "Laptop");
		categories.put("6", "Printer");
		categories.put("7", "Speaker");
		categories.put("8", "Other");
	}

	public static HashMap<String,String> getCategories(){
		initializeCategories();
		return categories;
	}

	public String getCategoryNameById(String categoryId) {
		return categories.get(categoryId);
	}

	public String getCategoryIdByName(String categoryName) {
		for (String categoryId : categories.keySet()) {
			if (categories.get(categoryId).equals(categoryName)) {
				return categoryId;
			}
		}
		return null;
	}
}