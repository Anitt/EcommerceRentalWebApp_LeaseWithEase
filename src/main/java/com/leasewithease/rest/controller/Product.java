package com.leasewithease.rest.controller;

import java.sql.Blob;
import java.util.HashMap;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import com.leasewithease.rest.AppContext;
import com.leasewithease.rest.dao.ProductsDAO;
import com.leasewithease.rest.model.Products;
import com.leasewithease.rest.session.SessionHandler;
import com.leasewithease.rest.util.Constants;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/product")
public class Product {
	HashMap<String,String> categories = new HashMap<String,String>();

	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	public HashMap<String, String> addProduct(@RequestParam(value = "productId", required = true) String productId,
		@RequestParam(value = "productName", required = true) String productName,
		@RequestParam(value = "categoryId", required = true) String categoryId,
		@RequestParam(value = "description", required = true) String description,
		@RequestParam(value = "rent", required = true) String rent,
		@RequestParam(value = "quantity", required = true) String quantity,
		@RequestParam(value = "productImage", required = false) MultipartFile image)
	{
		HashMap<String, String> response = new HashMap<String, String>();
		try {
			SessionHandler sessionHandler = AppContext.getSessionHandler();
			Products product = new Products();
			product.setProductId(productId);
			product.setProductName(productName);
			product.setCategoryId(categoryId);
			product.setCategoryName(Category.getCategories().get(categoryId));
			product.setDescription(description);
			product.setRent(Integer.parseInt(rent));
			product.setQuantity(Integer.parseInt(quantity));
			product.setRegistrationNo(sessionHandler.getRegistrationNumber());

			if (image != null) {
				byte[] imageBytes = image.getBytes();
				Blob imageBlob = new SerialBlob(imageBytes);
				product.setImage(imageBlob);
			}
			ProductsDAO productsDao = new ProductsDAO(product);
			productsDao.addProduct();

			response.put(Constants.STATUS, Constants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.put(Constants.STATUS, Constants.FAILURE);
			response.put(Constants.DATA, e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/removeproduct", method = RequestMethod.POST)
	public HashMap<String, String> removeProduct(@RequestParam(value = "productId", required = true) String productId) {
		HashMap<String, String> response = new HashMap<String, String>();
		try {
			SessionHandler sessionHandler = AppContext.getSessionHandler();
			Products product = new Products();
			product.setProductId(productId);
			product.setRegistrationNo(sessionHandler.getRegistrationNumber());
			ProductsDAO productsDao = new ProductsDAO(product);
			productsDao.removeProduct();
			response.put(Constants.STATUS, Constants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.put(Constants.STATUS, Constants.FAILURE);
			response.put(Constants.DATA, e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/getproductbyproductid", method = RequestMethod.POST)
	public HashMap<String, Object> getProductByProductId(
			@RequestParam(value = "productId", required = true) String productId) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		try {
			SessionHandler sessionHandler = AppContext.getSessionHandler();
			Products product = new Products();
			product.setProductId(productId);
			product.setRegistrationNo(sessionHandler.getRegistrationNumber());
			ProductsDAO productsDao = new ProductsDAO(product);
			productsDao.getProductByProductId();
			response.put(Constants.STATUS, Constants.SUCCESS);
			response.put(Constants.DATA, product);
		} catch (Exception e) {
			e.printStackTrace();
			response.put(Constants.STATUS, Constants.FAILURE);
			response.put(Constants.DATA, e.getMessage());
		}
		return response;
	}


	@RequestMapping(value = "/getproductbyid", method = RequestMethod.POST)
	public HashMap<String, Object> getProductbyID(
			@RequestParam(value = "productID", required = true) String productID,
			@RequestParam(value = "productName", required = true) String productName) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		try {
			Products product = new Products();
			ProductsDAO productsDao = new ProductsDAO(product);
			HashMap<String, Object> result = new HashMap<String, Object>();
			result = productsDao.getProductByID(productID, productName);
			if (result != null) {
				response.put("status", "success");
				response.put("data", result);
			} else {
				response.put("status", "failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "failure");
		}
		return response;
	}

	@RequestMapping(value = "/getproductByCategoryID", method = RequestMethod.GET)
	public HashMap<String, Object> getproductByCategoryID(@RequestParam(value = "Category_Id", required = true) String categoryId) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		try {

			ProductsDAO productsDao = new ProductsDAO(null);
			List<HashMap<String, Object>> result =  productsDao.getProductByCategoryId(categoryId);
			response.put("status", "success");
			response.put("data", result);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "failure");
			response.put("status", e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/getproductByName", method = RequestMethod.GET)
	public HashMap<String, Object> getProductbyName(
			@RequestParam(value = "productName", required = true) String productName) {

		HashMap<String, Object> response = new HashMap<String, Object>();
		try {
			ProductsDAO productsDao = new ProductsDAO(null);
			List<HashMap<String, Object>> result = productsDao.getProductByName(productName);
			response.put("status", "success");
			response.put("data", result);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "failure");
		}
		return response;
	}

	@RequestMapping(value = "/Image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImage(@RequestParam(value = "imageId") String productId) throws Exception {

		Products product = new Products();
		product.setProductId(productId);
		ProductsDAO productsDao = new ProductsDAO(product);
		productsDao.getProduct();

		byte[] byteImage = product.getImage().getBytes(1, (int) product.getImage().length());
		return byteImage;
	}
}