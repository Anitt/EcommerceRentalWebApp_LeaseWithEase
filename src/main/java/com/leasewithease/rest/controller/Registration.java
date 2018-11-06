package com.leasewithease.rest.controller;

import java.util.HashMap;

import com.leasewithease.rest.authentication.MD5;
import com.leasewithease.rest.dao.LesseeDAO;
import com.leasewithease.rest.dao.LessorDAO;
import com.leasewithease.rest.dao.UserLoginDAO;
import com.leasewithease.rest.model.Lessee;
import com.leasewithease.rest.model.Lessor;
import com.leasewithease.rest.model.UserLogin;
import com.leasewithease.rest.util.Constants;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/registration")
public class Registration {

	@RequestMapping(value = "/registerrentee", method = RequestMethod.POST)
	public HashMap<String, String> registerRentee(@RequestParam(value = "firstName", required = true) String firstName,
			@RequestParam(value = "lastName", required = true) String lastName,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "phoneNumber", required = true) String phone,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "streetAddress", required = true) String streetAddress,
			@RequestParam(value = "postalCode", required = true) String postalCode) {
		HashMap<String, String> response = new HashMap<String, String>();
		try {
			Lessee lessee = new Lessee();
			lessee.setFirstName(firstName);
			lessee.setLastName(lastName);
			lessee.setEmail(email);
			lessee.setPhone(phone);
			lessee.setStreetAddress(streetAddress);
			lessee.setPostalCode(postalCode);

			// Adding user into Lessee table.
			LesseeDAO lesseeDao = new LesseeDAO(lessee);
			lesseeDao.addLessee();

			// Adding user credentials into UserLogin table.
			UserLogin userLogin = new UserLogin();
			userLogin.setEmail(email);
			userLogin.setPasswordHash(MD5.hashString(password));
			userLogin.setIsLessor(false);
			UserLoginDAO userLoginDAO = new UserLoginDAO(userLogin);
			userLoginDAO.addUserLogin();

			response.put(Constants.STATUS, Constants.SUCCESS);
		} catch (MySQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			response.put(Constants.STATUS, Constants.FAILURE);
			response.put(Constants.DATA, "This email id is already registered.");
		} catch (Exception e) {
			e.printStackTrace();
			response.put(Constants.STATUS, Constants.FAILURE);
			response.put(Constants.DATA, e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/registerrenter", method = RequestMethod.POST)
	public HashMap<String, String> registerRenter(@RequestParam(value = "firstName", required = true) String firstName,
			@RequestParam(value = "lastName", required = true) String lastName,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "phoneNumber", required = true) String phone,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "streetAddress", required = true) String streetAddress,
			@RequestParam(value = "postalCode", required = true) String postalCode,
			@RequestParam(value = "registrationNo", required = true) String registrationNo) {
		HashMap<String, String> response = new HashMap<String, String>();
		try {
			Lessor lessor = new Lessor();
			lessor.setFirstName(firstName);
			lessor.setLastName(lastName);
			lessor.setEmail(email);
			lessor.setPhone(phone);
			lessor.setStreetAddress(streetAddress);
			lessor.setPostalCode(postalCode);
			lessor.setRegistrationNo(registrationNo);

			// Adding user into Lessor table.
			LessorDAO lessorDao = new LessorDAO(lessor);
			lessorDao.addLessor();

			// Adding user credentials into UserLogin table.
			UserLogin userLogin = new UserLogin();
			userLogin.setEmail(email);
			userLogin.setPasswordHash(MD5.hashString(password));
			userLogin.setIsLessor(true);
			UserLoginDAO userLoginDAO = new UserLoginDAO(userLogin);
			userLoginDAO.addUserLogin();

			response.put(Constants.STATUS, Constants.SUCCESS);

		} catch (MySQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			response.put(Constants.STATUS, Constants.FAILURE);
			response.put(Constants.DATA, "This email id is already registered.");
		} catch (Exception e) {
			e.printStackTrace();
			response.put(Constants.STATUS, Constants.FAILURE);
			response.put(Constants.DATA, e.getMessage());
		}
		return response;
	}
}