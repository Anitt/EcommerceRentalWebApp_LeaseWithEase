package com.leasewithease.rest.dao;

import com.leasewithease.rest.database.QueryExecutor;
import com.leasewithease.rest.database.StoredProcedures;
import com.leasewithease.rest.model.userType;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class userTypeDAO implements IDataAccessObject {

	private userType user_type;

	public userTypeDAO(userType user_type) {
		this.user_type = user_type;
	}

	public void getUserType() throws SQLException, ClassNotFoundException {
		QueryExecutor queryExecutor = QueryExecutor.getInstance();
		CallableStatement callableStatement = queryExecutor.getCallableStatement(StoredProcedures.USER_TYPE_STATEMENT);

		// gettng only email and will update only using email- check
		callableStatement.setString(1, user_type.getEmail());
		callableStatement.registerOutParameter(2, Types.INTEGER);
		callableStatement.execute();
		user_type.setIslessor(callableStatement.getInt(2));

		callableStatement.close();
	}

}
