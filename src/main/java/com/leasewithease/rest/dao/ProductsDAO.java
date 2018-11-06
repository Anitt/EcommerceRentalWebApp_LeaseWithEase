package com.leasewithease.rest.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import com.leasewithease.rest.database.QueryExecutor;
import com.leasewithease.rest.database.StoredProcedures;
import com.leasewithease.rest.model.Products;


public class ProductsDAO implements IDataAccessObject {
	private Products products;

	public ProductsDAO(Products products) {
		this.products = products;
	}

	public void addProduct() throws Exception {
		QueryExecutor queryExecutor = QueryExecutor.getInstance();
		CallableStatement callableStatement = queryExecutor
				.getCallableStatement(StoredProcedures.PRODUCT_ADD_STATEMENT);

		callableStatement.setString(1, products.getProductId());
		callableStatement.setString(2, products.getProductName());
		callableStatement.setString(3, products.getCategoryId());
		callableStatement.setString(4, products.getCategoryName());
		callableStatement.setString(5, products.getDescription());
		callableStatement.setInt(6, products.getRent());
		callableStatement.setInt(7, products.getQuantity());
		callableStatement.setBlob(8, products.getImage());
		callableStatement.setString(9, products.getRegistrationNo());
		callableStatement.execute();
		callableStatement.close();
	}

	public void removeProduct() throws Exception {
		QueryExecutor queryExecutor = QueryExecutor.getInstance();
		CallableStatement callableStatement = queryExecutor
				.getCallableStatement(StoredProcedures.PRODUCT_DELETE_STATEMENT);

		callableStatement.setString(1, products.getProductId());
		callableStatement.setString(2, products.getRegistrationNo());
		callableStatement.execute();
		callableStatement.close();
	}

	public void getProductByProductId() throws Exception {
		QueryExecutor queryExecutor = QueryExecutor.getInstance();
		CallableStatement callableStatement = queryExecutor.getCallableStatement(StoredProcedures.PRODUCT_GET_PRODUCT_BY_PRODUCTID_STATEMENT);
		
		callableStatement.setString(1, products.getProductId());
		callableStatement.setString(2, products.getRegistrationNo());
		ResultSet resultSet = callableStatement.executeQuery();
		while (resultSet.next()) {
			products.setProductId(resultSet.getString(1));
			products.setProductName(resultSet.getString(2));
			products.setCategoryId(resultSet.getString(3));
			products.setCategoryName(resultSet.getString(4));
			products.setDescription(resultSet.getString(5));
			products.setRent(resultSet.getInt(6));
			products.setQuantity(resultSet.getInt(7));
			break;
		}
		
		callableStatement.close();
	}

	public void getProduct() throws Exception {
		QueryExecutor queryExecutor = QueryExecutor.getInstance();
		CallableStatement callableStatement = queryExecutor
				.getCallableStatement(StoredProcedures.PRODUCT_GET_STATEMENT);

		callableStatement.setString(1, products.getProductId());

		ResultSet resultSet = callableStatement.executeQuery();
		while (resultSet.next()) {
			products.setProductId(resultSet.getString(1));
			products.setProductName(resultSet.getString(2));
			products.setCategoryId(resultSet.getString(3));
			products.setCategoryName(resultSet.getString(4));
			products.setDescription(resultSet.getString(5));
			products.setRent(resultSet.getInt(6));
			products.setQuantity(resultSet.getInt(7));
			products.setImage(resultSet.getBlob(8));
			products.setRegistrationNo(resultSet.getString(9));
			break;
		}

		callableStatement.close();
	}

	public HashMap<String, Object> getProductByID(String productID, String productName) throws Exception {
		QueryExecutor queryExecutor = QueryExecutor.getInstance();
		CallableStatement callableStatement = queryExecutor
				.getCallableStatement(StoredProcedures.PRODUCT_GET_PRODUCT_BY_PRODID_STATEMENT);

		callableStatement.setString(1, productID);
		//callableStatement.setString(2, productName);
		ResultSet resultset = callableStatement.executeQuery();
		HashMap<String, Object> rows = new HashMap<String, Object>();

		while (resultset.next()) {
			rows.put("productID", resultset.getString(1));
			rows.put("productName", resultset.getString(2));
			rows.put("categoryName", resultset.getString(4));
			rows.put("description", resultset.getString(5));
			rows.put("rent", resultset.getInt(6));
			rows.put("quantity", resultset.getInt(7));

			if (resultset.getBlob(8) != null) {
				Blob imgBlob = resultset.getBlob(8);
				InputStream resource = imgBlob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

				byte[] buffer = new byte[4096];
				int bytesread = -1;
				while ((bytesread = resource.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesread);
				}
				byte[] imageBytes = outputStream.toByteArray();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				resource.close();
				outputStream.close();
				rows.put("prodImg", base64Image);
			}
			rows.put("registration", resultset.getString(9));
		}
		return rows;
	}

	public List<HashMap<String, Object>> getProductByName(String name) throws Exception {
		QueryExecutor queryExecutor = QueryExecutor.getInstance();
		CallableStatement callableStatement = queryExecutor
				.getCallableStatement(StoredProcedures.PRODUCT_GET_PRODUCT_BY_NAME_STATEMENT);

		callableStatement.setString(1, name);
		ResultSet resultSet = callableStatement.executeQuery();
		List<HashMap<String, Object>> productsResultSet = new ArrayList<HashMap<String, Object>>();
		
		while (resultSet.next()) {
			HashMap<String, Object> currentProduct = new HashMap<String, Object>();
			currentProduct.put("productId", resultSet.getString(1));
			currentProduct.put("productName", resultSet.getString(2));
			currentProduct.put("categoryId", resultSet.getString(3));
			currentProduct.put("categoryName", resultSet.getString(4));
			currentProduct.put("description", resultSet.getString(5));
			currentProduct.put("rent", resultSet.getInt(6));
			currentProduct.put("quantity", resultSet.getInt(7));
			currentProduct.put("registrationNo", resultSet.getString(9));
			if (resultSet.getBlob(8) != null) {
				Blob imgBlob = resultSet.getBlob(8);
				InputStream resource = imgBlob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

				byte[] buffer = new byte[4096];
				int bytesread = -1;
				while ((bytesread = resource.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesread);
				}
				byte[] imageBytes = outputStream.toByteArray();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				resource.close();
				outputStream.close();
				currentProduct.put("prodImg", base64Image);
			}
			productsResultSet.add(currentProduct);
		}

		callableStatement.close();
		return productsResultSet;
	}

	// get products by CategoryID for product List page
	public List<HashMap<String, Object>> getProductByCategoryId(String categoryId) throws Exception {
		QueryExecutor queryExecutor = QueryExecutor.getInstance();
		CallableStatement callableStatement = queryExecutor
				.getCallableStatement(StoredProcedures.PRODUCT_GET_PRODUCT_BY_CATEGORYID_STATEMENT);

		callableStatement.setString(1, categoryId);
		ResultSet resultSet = callableStatement.executeQuery();

		List<HashMap<String, Object>> productsResultSet = new ArrayList<HashMap<String, Object>>();
		while (resultSet.next()) {
			HashMap<String, Object> currentProduct = new HashMap<String, Object>();
			currentProduct.put("categoryId", resultSet.getString(1));
			currentProduct.put("productId", resultSet.getString(2));
			currentProduct.put("productName", resultSet.getString(3));
			currentProduct.put("categoryName", resultSet.getString(4));
			currentProduct.put("description", resultSet.getString(5));
			currentProduct.put("rent", resultSet.getInt(6));
			currentProduct.put("quantity", resultSet.getInt(7));
			currentProduct.put("registrationNo", resultSet.getString(9));
			if (resultSet.getBlob(8) != null) {
				Blob imgBlob = resultSet.getBlob(8);
				InputStream resource = imgBlob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

				byte[] buffer = new byte[4096];
				int bytesread = -1;
				while ((bytesread = resource.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesread);
				}
				byte[] imageBytes = outputStream.toByteArray();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				resource.close();
				outputStream.close();
				currentProduct.put("prodImg", base64Image);
			}
			productsResultSet.add(currentProduct);
		}

		callableStatement.close();
		return productsResultSet;
	}
}