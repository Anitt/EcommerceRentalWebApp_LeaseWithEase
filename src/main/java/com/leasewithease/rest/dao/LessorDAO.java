package com.leasewithease.rest.dao;

import com.leasewithease.rest.database.QueryExecutor;
import com.leasewithease.rest.database.StoredProcedures;
import com.leasewithease.rest.model.Lessor;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class LessorDAO implements IDataAccessObject {
	private Lessor lessor;

	public LessorDAO(Lessor lessor) {
		this.lessor = lessor;
	}

	public void addLessor() throws SQLException, ClassNotFoundException {
		QueryExecutor queryExecutor = QueryExecutor.getInstance();
		CallableStatement callableStatement = queryExecutor.getCallableStatement(StoredProcedures.LESSOR_ADD_STATEMENT);

		callableStatement.setString(1, lessor.getFirstName());
		callableStatement.setString(2, lessor.getLastName());
		callableStatement.setString(3, lessor.getEmail());
		callableStatement.setLong(4, Long.parseLong(lessor.getPhone()));
		callableStatement.setString(5, lessor.getStreetAddress());
		callableStatement.setString(6, lessor.getPostalCode());
		callableStatement.setString(7, lessor.getRegistrationNo());
		callableStatement.execute();
		callableStatement.close();
	}

	public void updateLessor() throws SQLException, ClassNotFoundException {
		QueryExecutor queryExecutor = QueryExecutor.getInstance();
		CallableStatement callableStatement = queryExecutor
				.getCallableStatement(StoredProcedures.LESSOR_UPDATE_STATEMENT);

		callableStatement.setString(1, lessor.getEmail());
		callableStatement.setLong(2, Long.parseLong(lessor.getPhone()));
		callableStatement.setString(3, lessor.getStreetAddress());
		callableStatement.setString(4, lessor.getPostalCode());
		callableStatement.execute();
		callableStatement.close();
	}

	public void getLessor() throws SQLException, ClassNotFoundException {
		QueryExecutor queryExecutor = QueryExecutor.getInstance();
		CallableStatement callableStatement = queryExecutor.getCallableStatement(StoredProcedures.LESSOR_GET_STATEMENT);

		callableStatement.setString(1, lessor.getEmail());
		callableStatement.registerOutParameter(2, Types.VARCHAR);
		callableStatement.registerOutParameter(3, Types.VARCHAR);
		callableStatement.registerOutParameter(4, Types.LONGNVARCHAR);
		callableStatement.registerOutParameter(5, Types.VARCHAR);
		callableStatement.registerOutParameter(6, Types.VARCHAR);
		callableStatement.registerOutParameter(7, Types.VARCHAR);
		callableStatement.execute();
		lessor.setFirstName(callableStatement.getString(2));
		lessor.setLastName(callableStatement.getString(3));
		lessor.setPhone(Long.toString(callableStatement.getLong(4)));
		lessor.setStreetAddress(callableStatement.getString(5));
		lessor.setPostalCode(callableStatement.getString(6));
		lessor.setRegistrationNo(callableStatement.getString(7));
		callableStatement.close();
	}

	public void getRegistrationNumber() throws SQLException, ClassNotFoundException {
		QueryExecutor queryExecutor = QueryExecutor.getInstance();
		CallableStatement callableStatement = queryExecutor.getCallableStatement(StoredProcedures.LESSOR_GET_REGISTRATION_NUMBER_STATEMENT);

		callableStatement.setString(1, lessor.getEmail());
		callableStatement.registerOutParameter(2, Types.VARCHAR);
		callableStatement.execute();
		lessor.setRegistrationNo(callableStatement.getString(2));
		callableStatement.close();
	}
}