package com.leasewithease.rest.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

import com.leasewithease.rest.AppContext;
import com.leasewithease.rest.database.QueryExecutor;
import com.leasewithease.rest.database.StoredProcedures;
import com.leasewithease.rest.model.CartModel;
import com.leasewithease.rest.session.SessionHandler;

public class CartDAO implements IDataAccessObject {
	private CartModel cart;
    SessionHandler sessionHandler = AppContext.getSessionHandler();

	public CartDAO(CartModel cart) {
	
		this.cart = cart;

	}

	public boolean updateCartDetails() throws SQLException{
		Boolean success = false;
		try
		{
			QueryExecutor queryExecutor = QueryExecutor.getInstance();
			CallableStatement callableStatement = queryExecutor.getCallableStatement(StoredProcedures.CART_UPFATEITEM_STATEMENT);
			callableStatement.setString(1, sessionHandler.getLoggedInUserId());	
			callableStatement.setString(2, cart.getProductId());
			callableStatement.setString(3, cart.getProductName()); 	
			callableStatement.setInt(4, cart.getQuantity());
			callableStatement.setInt(5, (int)cart.getDays());
			success = callableStatement.execute();
			callableStatement.close();
		}catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		return success;
	}

	public ArrayList<HashMap<String, Object>> loadCartDetails() throws Exception  {
		// fetch from db
		ArrayList<HashMap<String, Object>> output = new ArrayList<HashMap<String, Object>>();
			
		try
		{	
			QueryExecutor queryExecutor = QueryExecutor.getInstance();
			CallableStatement callableStatement = queryExecutor.getCallableStatement(StoredProcedures.CART_SELECT_STATEMENT);
			callableStatement.setString(1, sessionHandler.getLoggedInUserId());
			ResultSet resultSet = callableStatement.executeQuery();
			HashMap<String, Object> rows;
			while (resultSet.next())
				{
					rows = new HashMap<String, Object>();
					Integer categoryID = resultSet.getInt(1);
					String prodID = resultSet.getString(2);
					String prodName = resultSet.getString(3);
					Integer cartQuantity = resultSet.getInt(4);
					Integer cartCost = resultSet.getInt(5);
					Integer cartdays = resultSet.getInt(6);
					Blob  imgBlob= resultSet.getBlob(7);
					Integer prodQuantity = resultSet.getInt(8);
					String categoryName = resultSet.getString(9);
					Integer prodRent = resultSet.getInt(10);

					rows.put("catID", categoryID.toString());
					rows.put("prodID", prodID);
					rows.put("prodName", prodName);
					rows.put("cartQuantity", cartQuantity.toString());
					rows.put("cartCost", cartCost.toString());
					rows.put("cartDays", cartdays.toString());
					rows.put("prodQuantity", prodQuantity.toString());
					rows.put("categoryName", categoryName);
					rows.put("prodRent", prodRent.toString());
					if(imgBlob!=null){

						InputStream resource = imgBlob.getBinaryStream();
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

						byte [] buffer = new byte[4096];
						int bytesread = -1;
						while((bytesread=resource.read(buffer))!=-1){
							outputStream.write(buffer,0, bytesread);
						}
						byte[] imageBytes = outputStream.toByteArray();
						String base64Image = Base64.getEncoder().encodeToString(imageBytes);
						resource.close();
						outputStream.close();
						rows.put("prodImg", base64Image);
					}
					output.add(rows);
				}
			
		}catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		return output;

	}


	public boolean deleteItemFromCart(Integer itemID)throws ClassNotFoundException, SQLException {
		try
		{
			QueryExecutor queryExecutor = QueryExecutor.getInstance();
			CallableStatement callableStatement = queryExecutor.getCallableStatement(StoredProcedures.CART_DELETEITEM_STATEMENT);
			callableStatement.setInt(1, itemID);
			ResultSet resultSet = callableStatement.executeQuery();
			return resultSet!=null;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteCart()throws ClassNotFoundException, SQLException {
		try
		{
			QueryExecutor queryExecutor = QueryExecutor.getInstance();
			CallableStatement callableStatement = queryExecutor.getCallableStatement(StoredProcedures.CART_DELETE_STATEMENT);
			callableStatement.setString(1,  sessionHandler.getLoggedInUserId());
			ResultSet resultSet = callableStatement.executeQuery();
			return resultSet!=null;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
}