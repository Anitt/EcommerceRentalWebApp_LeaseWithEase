package com.leasewithease.rest.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.leasewithease.rest.Properties;

public class QueryExecutor {
	private static QueryExecutor queryExecutor = null;

	public static QueryExecutor getInstance() {
		if (queryExecutor == null) {
			queryExecutor = new QueryExecutor();
		}
		return queryExecutor;
	}

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		String datasourceUrl = Properties.getProperty("database.url");
		String username = Properties.getProperty("database.username");
		String password = Properties.getProperty("database.password");

		Class.forName(Properties.getProperty("database.driver"));
		return DriverManager.getConnection(datasourceUrl, username, password);
	}

	public CallableStatement getCallableStatement(String query) throws ClassNotFoundException, SQLException {
		return this.getConnection().prepareCall(query);
	}
}