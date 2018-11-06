package com.leasewithease.rest.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.leasewithease.rest.authentication.InvalidCredentialsException;
import com.leasewithease.rest.database.QueryExecutor;
import com.leasewithease.rest.database.StoredProcedures;
import com.leasewithease.rest.model.UserLogin;

public class UserLoginDAO implements IDataAccessObject {
	private UserLogin userLogin;

	public UserLoginDAO(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public void getPasswordFromDatabase() throws ClassNotFoundException, SQLException, InvalidCredentialsException {
		QueryExecutor queryExecutor = QueryExecutor.getInstance();
		CallableStatement callableStatement = queryExecutor.getCallableStatement(StoredProcedures.USER_LOGIN_GET_STATEMENT);
		callableStatement.setString(1, userLogin.getEmail());
		callableStatement.registerOutParameter(2, Types.VARCHAR);
		callableStatement.registerOutParameter(3, Types.BOOLEAN);
		int value = callableStatement.executeUpdate();
		if (value > 0) {
			userLogin.setPasswordHash(callableStatement.getString(2));
			userLogin.setIsLessor(callableStatement.getBoolean(3));
		} else {
			throw new InvalidCredentialsException();
		}
		callableStatement.close();
	}

	public void addUserLogin() throws Exception{
		QueryExecutor queryExecutor = QueryExecutor.getInstance();
		CallableStatement callableStatement = queryExecutor.getCallableStatement(StoredProcedures.USER_LOGIN_ADD_STATEMENT);
		
		callableStatement.setString(1, userLogin.getEmail());
		callableStatement.setString(2, userLogin.getPasswordHash());
		callableStatement.setBoolean(3, userLogin.isLessor());
		callableStatement.execute();
		callableStatement.close();
	}
}