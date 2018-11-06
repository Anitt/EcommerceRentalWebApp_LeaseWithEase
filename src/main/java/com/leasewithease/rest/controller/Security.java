package com.leasewithease.rest.controller;

import java.util.HashMap;

import com.leasewithease.rest.AppContext;
import com.leasewithease.rest.authentication.Authenticator;
import com.leasewithease.rest.authentication.InvalidCredentialsException;
import com.leasewithease.rest.session.SessionHandler;
import com.leasewithease.rest.util.Constants;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/security")
public class Security {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public HashMap<String, String> login(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {
		HashMap<String, String> response = new HashMap<String, String>();
		SessionHandler sessionHandler = AppContext.getSessionHandler();
		try {
			Authenticator authenticator = new Authenticator(username, password);
			authenticator.authenticateUser();
			
			response.put(Constants.STATUS, Constants.SUCCESS);
		} catch (InvalidCredentialsException e) {
			e.printStackTrace();
			response.put(Constants.STATUS, Constants.FAILURE);
			response.put(Constants.DATA, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			response.put(Constants.STATUS, Constants.FAILURE);
			response.put(Constants.DATA, "Something went wrong please try again later.");
			sessionHandler.headerRedirect(Constants.ERROR_PAGE);
		}
		return response;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logout() {
		SessionHandler sessionHandler = AppContext.getSessionHandler();
		sessionHandler.userLogout();
	}
}