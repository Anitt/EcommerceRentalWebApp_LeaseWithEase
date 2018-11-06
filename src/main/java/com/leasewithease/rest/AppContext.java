package com.leasewithease.rest;

import com.leasewithease.rest.session.SessionHandler;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppContext implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		AppContext.applicationContext = applicationContext;
	}

	public static SessionHandler getSessionHandler() {
		return applicationContext.getBean(SessionHandler.class);
	}
}