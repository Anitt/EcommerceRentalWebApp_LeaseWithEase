package com.leasewithease.rest.controller;

import java.util.HashMap;

import com.leasewithease.rest.AppContext;
import com.leasewithease.rest.dao.LesseeDAO;
import com.leasewithease.rest.dao.LessorDAO;
import com.leasewithease.rest.model.Lessee;
import com.leasewithease.rest.model.Lessor;
import com.leasewithease.rest.session.SessionHandler;
import com.leasewithease.rest.util.Constants;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/profile")
public class Profile {

	@RequestMapping(value = "/updateprofile", method = RequestMethod.POST)
	public HashMap<String, Object> updateProfile(@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "streetAddress", required = true) String streetAddress,
			@RequestParam(value = "postalCode", required = true) String postalCode) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		try {
			SessionHandler sessionHandler = AppContext.getSessionHandler();
			String userId = sessionHandler.getLoggedInUserId();
			if(sessionHandler.isLesseeLoggedIn()){
				Lessee lessee = new Lessee();
				lessee.setEmail(userId);
				lessee.setPhone(phone);
				lessee.setStreetAddress(streetAddress);
				lessee.setPostalCode(postalCode);

				LesseeDAO lesseeDao = new LesseeDAO(lessee);
				lesseeDao.updateLessee();
				response.put(Constants.DATA, lessee);
			} else {
				Lessor lessor = new Lessor();
				lessor.setEmail(userId);
				lessor.setPhone(phone);
				lessor.setStreetAddress(streetAddress);
				lessor.setPostalCode(postalCode);
				LessorDAO lessorDao = new LessorDAO(lessor);
				lessorDao.updateLessor();
				response.put(Constants.DATA, lessor);
			}
			response.put(Constants.STATUS, Constants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.put(Constants.STATUS, Constants.FAILURE);
			response.put(Constants.DATA, e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/getprofile", method = RequestMethod.POST)
	public HashMap<String, Object> getProfile() {
		HashMap<String, Object> response = new HashMap<String, Object>();
		try {
			SessionHandler sessionHandler = AppContext.getSessionHandler();
			String userId = sessionHandler.getLoggedInUserId();
			if(sessionHandler.isLesseeLoggedIn()){
				Lessee lessee = new Lessee();
				lessee.setEmail(userId);
				LesseeDAO lesseeDao = new LesseeDAO(lessee);
				lesseeDao.getLessee();
				response.put(Constants.DATA, lessee);
			} else {
				Lessor lessor = new Lessor();
				lessor.setEmail(userId);
				LessorDAO lessorDao = new LessorDAO(lessor);
				lessorDao.getLessor();
				response.put(Constants.DATA, lessor);
			}
			
			response.put(Constants.STATUS, Constants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.put(Constants.STATUS, Constants.FAILURE);
			response.put(Constants.DATA, e.getMessage());
		}
		return response;
	}
}