package com.leasewithease.rest.dao;

import com.leasewithease.rest.database.QueryExecutor;
import com.leasewithease.rest.database.StoredProcedures;
import com.leasewithease.rest.model.Lessee;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class LesseeDAO implements IDataAccessObject {
	private Lessee lessee;

	public LesseeDAO(Lessee lessee) {
		this.lessee = lessee;
	}

	public void addLessee() throws Exception {
		QueryExecutor queryExecutor = QueryExecutor.getInstance();
		CallableStatement callableStatement = queryExecutor.getCallableStatement(StoredProcedures.LESSEE_ADD_STATEMENT);

		callableStatement.setString(1, lessee.getFirstName());
		callableStatement.setString(2, lessee.getLastName());
		callableStatement.setString(3, lessee.getEmail());
		callableStatement.setLong(4, Long.parseLong(lessee.getPhone()));
		callableStatement.setString(5, lessee.getStreetAddress());
		callableStatement.setString(6, lessee.getPostalCode());
		callableStatement.execute();
		callableStatement.close();
	}

	public void updateLessee() throws Exception {
		QueryExecutor queryExecutor = QueryExecutor.getInstance();
		CallableStatement callableStatement = queryExecutor
				.getCallableStatement(StoredProcedures.LESSEE_UPDATE_STATEMENT);

		callableStatement.setString(1, lessee.getEmail());
		callableStatement.setLong(2, Long.parseLong(lessee.getPhone()));
		callableStatement.setString(3, lessee.getStreetAddress());
		callableStatement.setString(4, lessee.getPostalCode());
		callableStatement.execute();
		callableStatement.close();
	}

	public void getLessee() throws SQLException, ClassNotFoundException {
		QueryExecutor queryExecutor = QueryExecutor.getInstance();
		CallableStatement callableStatement = queryExecutor.getCallableStatement(StoredProcedures.LESSEE_GET_STATEMENT);

		callableStatement.setString(1, lessee.getEmail());
		callableStatement.registerOutParameter(2, Types.VARCHAR);
		callableStatement.registerOutParameter(3, Types.VARCHAR);
		callableStatement.registerOutParameter(4, Types.LONGNVARCHAR);
		callableStatement.registerOutParameter(5, Types.VARCHAR);
		callableStatement.registerOutParameter(6, Types.VARCHAR);
		callableStatement.execute();
		lessee.setFirstName(callableStatement.getString(2));
		lessee.setLastName(callableStatement.getString(3));
		lessee.setPhone(Long.toString(callableStatement.getLong(4)));
		lessee.setStreetAddress(callableStatement.getString(5));
		lessee.setPostalCode(callableStatement.getString(6));
		callableStatement.close();
	}
}