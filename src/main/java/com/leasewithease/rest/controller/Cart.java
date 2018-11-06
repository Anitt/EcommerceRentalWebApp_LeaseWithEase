package com.leasewithease.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leasewithease.rest.dao.CartDAO;
import com.leasewithease.rest.model.CartModel;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/cart")
public class Cart {

	HashMap<String, String> resultstatus;

	HttpServletResponse response;
	HttpServletRequest request;

	@ResponseBody
	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	public HashMap<String, String> addToCart(@RequestBody CartModel cartmodel) throws Exception {
		HashMap<String, String> response = new HashMap<String, String>();
		try {
			CartDAO cartDAO = new CartDAO(cartmodel);
			Boolean successVal = cartDAO.updateCartDetails();
			response.put("status", "success");
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "failure");
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public HashMap<String, ArrayList<HashMap<String, Object>>> onLoadCart() throws Exception {
		resultstatus = new HashMap<String, String>();
		CartModel cartModel = new CartModel();
		CartDAO cartDAO = new CartDAO(cartModel);
		HashMap<String, ArrayList<HashMap<String, Object>>> finaloutput = new HashMap<String, ArrayList<HashMap<String, Object>>>();
		ArrayList<HashMap<String, Object>> output = new ArrayList<HashMap<String, Object>>();

		output = cartDAO.loadCartDetails();
		if (output == null) {
			finaloutput.put("status", output);
		} else {
			finaloutput.put("data", output);
		}

		return finaloutput;
	}

	@RequestMapping(value = "/removeitem", method = RequestMethod.POST)
	public HashMap<String, String> removeCartItem(@RequestParam(value = "cartID", required = true) Integer cartID) {
		HashMap<String, String> response = new HashMap<String, String>();
		try {
			resultstatus = new HashMap<String, String>();

			CartModel cartModel = new CartModel();
			CartDAO cartDAO = new CartDAO(cartModel);
			Boolean isSuccess = cartDAO.deleteItemFromCart(cartID);
			if (isSuccess) {
				response.put("status", "success");
			} else {
				response.put("status", "failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "failure");
		}
		return response;
	}

	
	@RequestMapping(value = "/emptycart", method = RequestMethod.POST)
	public HashMap<String, String> emptyCartItems() {
		HashMap<String, String> response = new HashMap<String, String>();
		try {
			resultstatus = new HashMap<String, String>();

			CartModel cartModel = new CartModel();
			CartDAO cartDAO = new CartDAO(cartModel);
			Boolean isSuccess = cartDAO.deleteCart();
			if (isSuccess) {
				response.put("status", "success");
			} else {
				response.put("status", "failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "failure");
		}
		return response;
	}
}