package com.leasewithease.rest.authentication;

import java.sql.SQLException;

import com.leasewithease.rest.AppContext;
import com.leasewithease.rest.dao.LessorDAO;
import com.leasewithease.rest.dao.UserLoginDAO;
import com.leasewithease.rest.model.Lessor;
import com.leasewithease.rest.model.UserLogin;
import com.leasewithease.rest.session.SessionHandler;
import com.leasewithease.rest.util.Constants;

public class Authenticator {
	private String username;
	private String password;

	public Authenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void authenticateUser() throws ClassNotFoundException, SQLException, InvalidCredentialsException {
		UserLogin userLogin = new UserLogin();
		userLogin.setEmail(username);

		UserLoginDAO userLoginDAO = new UserLoginDAO(userLogin);
		userLoginDAO.getPasswordFromDatabase();

		String hashedpassword = MD5.hashString(password);
		boolean passwordMatch = hashedpassword.equals(userLogin.getPasswordHash());
		if(!passwordMatch) {
			throw new InvalidCredentialsException();
		} else {
			UserType userType = UserType.LESSEE;
			String registrationNumber;
			SessionHandler sessionHandler = AppContext.getSessionHandler();

			if(userLogin.getEmail().equals(Constants.WEB_ADMIN_USERNAME)) {
				userType = UserType.WEBADMIN;
			} else if (userLogin.isLessor()) {
				userType = UserType.LESSOR;
				Lessor lessor = new Lessor();
				LessorDAO lessorDAO = new LessorDAO(lessor);
				lessor.setEmail(username);
				lessorDAO.getRegistrationNumber();
				registrationNumber = lessor.getRegistrationNo();
				sessionHandler.setRegistrationNumber(registrationNumber);
			}
			sessionHandler.userLogin(username, userType);
		}
	}
}