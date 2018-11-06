package com.leasewithease.rest.database;

public class StoredProcedures {
	public static final String USER_LOGIN_ADD_STATEMENT = "{call addUserLogin(?,?,?)}";
	public static final String USER_LOGIN_GET_STATEMENT="{call getUserLogin(?,?,?)}";
	public static final String USER_LOGIN_UPDATE_STATEMENT="{call updateUserLogin(?,?)}";
	public static final String USER_LOGIN_DELETE_STATEMENT ="{call deleteUserLogin(?)}";

	public static final String LESSEE_ADD_STATEMENT = "{call addLesse(?,?,?,?,?,?)}";
	public static final String LESSEE_GET_STATEMENT = "{call getLesse(?,?,?,?,?,?)}";
	public static final String LESSEE_UPDATE_STATEMENT = "{call updateLesse(?,?,?,?)}";
	public static final String LESSEE_DELETE_STATEMENT = "{call deleteLesse(?)}";

	public static final String LESSOR_ADD_STATEMENT = "{call addLessor(?,?,?,?,?,?,?)}";
	public static final String LESSOR_GET_STATEMENT = "{call getLessor(?,?,?,?,?,?,?)}";
	public static final String LESSOR_UPDATE_STATEMENT = "{call updateLessor(?,?,?,?)}";
	public static final String LESSOR_DELETE_STATEMENT = "{call deleteLessor(?)}";
	public static final String LESSOR_GET_REGISTRATION_NUMBER_STATEMENT = "{call getRegistrationNumber(?,?)}";

	public static final String PRODUCT_ADD_STATEMENT = "{call addProduct(?,?,?,?,?,?,?,?,?)}";
	public static final String PRODUCT_GET_STATEMENT = "{call getProduct(?)}";
	public static final String PRODUCT_GET_PRODUCT_BY_PRODUCTID_STATEMENT = "{call getProductByProductId(?,?)}";
	public static final String PRODUCT_GET_PRODUCT_BY_CATEGORYID_STATEMENT = "{call getProductByCategoryId(?)}";
	public static final String PRODUCT_GET_PRODUCT_BY_NAME_STATEMENT = "{call getProductByName(?)}";
	public static final String PRODUCT_GET_PRODUCT_BY_PRODID_STATEMENT = "{call getProductByProdID(?)}";
	public static final String PRODUCT_UPDATE_STATEMENT = "{call updateProduct(?,?,?,?)}";
	public static final String PRODUCT_DELETE_STATEMENT = "{call deleteProduct(?,?)}";

	public static final String CART_SELECT_STATEMENT = "{call getCartDetails(?)}";

	public static final String CART_DELETEITEM_STATEMENT = "{call deleteCartItem(?)}";
	public static final String CART_DELETE_STATEMENT = "{call deleteCart(?)}";
	

	public static final String CART_UPFATEITEM_STATEMENT = "{call addToCart(?,?,?,?,?)}";
	public static final String USER_TYPE_STATEMENT = "{call getUserType(?,?)}";
	
//	public static final String PRODUCT_GET_PRODUCT_BY_CATEGORYID_STATEMENT = "{call sproc_getProductByCategoryID(?,?,?,?,?,?,?,?)}";
//	public static final String PRODUCT_GET_PRODUCT_BY_PRODID_STATEMENT = "{call getProductByProdID(?)}";

	//public static final String PRODUCT_GET_PRODUCT_BY_CATEGORYID_STATEMENT = "{call sproc_getProductByCategoryID(?,?,?,?,?,?,?,?)}";


	//public static final String CART_DELETEITEM_STATEMENT = "{call deleteCartItem(?)}";
	//public static final String CART_UPFATEITEM_STATEMENT = "{call addToCart(?,?,?,?,?)}";

}