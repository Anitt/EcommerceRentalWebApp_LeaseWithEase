package com.leasewithease.rest.session;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.leasewithease.rest.authentication.UserType;
import com.leasewithease.rest.util.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionHandler {
	@Autowired
	private HttpSession httpSession;

	@Autowired
	private SessionCart sessionCart;

	@Autowired
	private HttpServletResponse httpServletResponse;

	private static final String LOGGED_IN_USER_ID = "LOGGED_IN_USER_ID";
	private static final String LESSOR_LOGGED_IN = "LESSOR_LOGGED_IN";
	private static final String LESSEE_LOGGED_IN = "LESSEE_LOGGED_IN";
	private static final String WEB_ADMIN_LOGGED_IN = "WEB_ADMIN_LOGGED_IN";
	private static final String REGISTRATION_NUMBER = "REGISTRATION_NUMBER";

	public void userLogin(String username, UserType userType) {
		String lessorLoggedIn = Constants.FALSE;
		String lesseeLoggedIn = Constants.FALSE;
		String webAdminLoggedIn = Constants.FALSE;

		if (userType == UserType.WEBADMIN) {
			webAdminLoggedIn = Constants.TRUE;
		} else if (userType == UserType.LESSOR) {
			lessorLoggedIn = Constants.TRUE;
		} else {
			lesseeLoggedIn = Constants.TRUE;
		}

		httpSession.setAttribute(SessionHandler.LOGGED_IN_USER_ID, username);
		httpSession.setAttribute(SessionHandler.WEB_ADMIN_LOGGED_IN, webAdminLoggedIn);
		httpSession.setAttribute(SessionHandler.LESSOR_LOGGED_IN, lessorLoggedIn);
		httpSession.setAttribute(SessionHandler.LESSEE_LOGGED_IN, lesseeLoggedIn);
	}

	public String getLoggedInUserId() {
		return (String) httpSession.getAttribute(SessionHandler.LOGGED_IN_USER_ID);
	}

	public boolean isWebAdminLoggedIn() {
		String webAdminLoggedIn = (String) httpSession.getAttribute(SessionHandler.WEB_ADMIN_LOGGED_IN);
		return Constants.TRUE.equals(webAdminLoggedIn);
	}

	public boolean isLessorLoggedIn() {
		String lessorLoggedIn = (String) httpSession.getAttribute(SessionHandler.LESSOR_LOGGED_IN);
		return Constants.TRUE.equals(lessorLoggedIn);
	}

	public boolean isLesseeLoggedIn() {
		String lesseeLoggedIn = (String) httpSession.getAttribute(SessionHandler.LESSEE_LOGGED_IN);
		return Constants.TRUE.equals(lesseeLoggedIn);
	}

	public boolean isUserLoggedIn() {
		String webAdminLoggedIn = (String) httpSession.getAttribute(SessionHandler.WEB_ADMIN_LOGGED_IN);
		String lessorLoggedIn = (String) httpSession.getAttribute(SessionHandler.LESSOR_LOGGED_IN);
		String lesseeLoggedIn = (String) httpSession.getAttribute(SessionHandler.LESSEE_LOGGED_IN);

		return Constants.TRUE.equals(webAdminLoggedIn) || Constants.TRUE.equals(lessorLoggedIn)
				|| Constants.TRUE.equals(lesseeLoggedIn);
	}

	public void userLogout() {
		httpSession.invalidate();
	}

	public void headerRedirect(String location) {
		httpServletResponse.addHeader(Constants.REDIRECT_HEADER, location);
	}

	public void httpRedirect(String location) throws IOException {
		httpServletResponse.sendRedirect(location);
	}

	public SessionCart getSessionCart() {
		return sessionCart;
	}

	public String getRegistrationNumber() {
		return (String) httpSession.getAttribute(SessionHandler.REGISTRATION_NUMBER);
	}

	public void setRegistrationNumber(String registrationNumber) {
		httpSession.setAttribute(SessionHandler.REGISTRATION_NUMBER, registrationNumber);
	}
}