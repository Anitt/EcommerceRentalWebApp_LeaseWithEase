package com.leasewithease.rest;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = { "classpath:application.properties" }, ignoreResourceNotFound = false)
public class Properties implements EnvironmentAware {
	private static Environment env;

	@Override
	public void setEnvironment(Environment env) {
		Properties.env = env;
	}

	public static String getProperty(String key) {
		return env.getProperty(key);
	}
}