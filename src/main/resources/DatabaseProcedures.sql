DELIMITER $$
CREATE PROCEDURE `addLesse`(
	IN firstName varchar(100),
	IN lastName varchar(100),
	IN email varchar(100),
	IN phone varchar(15),
	IN streetAddress varchar(200),
	IN postalCode varchar(7)
)
BEGIN
	insert into LESSEE (FIRFIRST_NAME, LAST_NAME, EMAIL, PHONE, STREET_ADDRESS, POSTAL_CODE)
	values (firstName, lastName, email, phone, streetAddress, postalCode);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `updateLesse`(
	IN in_email varchar(100),
	IN in_phone long,
	IN in_streetAddress varchar(200),
	IN in_postalCode varchar(7)
)
BEGIN
	update LESSEE
	set LESSEE.PHONE = in_phone, LESSEE.STREET_ADDRESS = in_streetAddress, LESSEE.POSTAL_CODE = in_postalCode
	where LESSEE.EMAIL = in_email;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `getLessor`(
	IN email varchar(100),
	OUT firstName varchar(100),
	OUT lastName varchar(100),
	OUT phone long,
	OUT streetAddress varchar(200),
	OUT postalCode varchar(7),
	OUT registrationNo varchar(100)
)
BEGIN
	select LESSOR.FIRST_NAME, LESSOR.LAST_NAME, LESSOR.PHONE, LESSOR.STREET_ADDRESS, LESSOR.POSTAL_CODE, LESSOR.REGISTRATION_NO
	INTO firstName, lastName, phone, streetAddress, postalCode, registrationNo
	from LESSOR  where LESSOR.EMAIL = email;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `getRegistrationNumber`(
	IN email varchar(100),
	OUT registrationNo varchar(100)
)
BEGIN
	select LESSOR.REGISTRATION_NO
	INTO registrationNo
	from LESSOR  where LESSOR.EMAIL = email;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `addLessor`(
	IN firstName varchar(100),
	IN lastName varchar(100),
	IN emailid varchar(100),
	IN phoneno varchar(15),
	IN streetAddress varchar(200),
	IN postalCode varchar(7),
	IN registrationNo varchar(100)
)
BEGIN
	insert into LESSOR (FIRST_NAME, LAST_NAME, EMAIL, PHONE, STREET_ADDRESS, POSTAL_CODE,REGISTRATION_NO)
	values (firstName, lastName, emailid, phoneno, streetAddress, postalCode,registrationNo);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `updateLessor`(
	IN email varchar(100),
	IN phone varchar(15),
	IN streetAddress varchar(200),
	IN postalCode varchar(7)
)
BEGIN
	update LESSOR set PHONE = phone, STREET_ADDRESS = streetAddress,
	 POSTAL_CODE = postalCode where EMAIL = email;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `getLesse`(
	IN emailid varchar(100),
	OUT firstname varchar(100),
	OUT lastname varchar(100),
	OUT phoneno long,
	OUT streetaddress varchar(200),
	OUT postalcode varchar(7)
)
BEGIN
	select FIRST_NAME ,LAST_NAME ,PHONE ,STREET_ADDRESS ,POSTAL_CODE
	INTO firstname, lastname, phoneno, streetaddress, postalcode
	from LESSEE  where LESSEE.EMAIL = emailid;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `addUserLogin`(
	IN email varchar(255),
	IN passwordhash varchar(255),
	IN isLessor bool
)
BEGIN
	insert into USER_LOGIN (EMAIL, PASSWORD_HASH, IS_LESSOR) 
	values (email, passwordhash, isLessor);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `getUserLogin`(
	IN USERNAME varchar(255),
	OUT PWD_HASH varchar(255),
	OUT ISLESSOR BOOLEAN
)
BEGIN
	SELECT PASSWORD_HASH, IS_LESSOR INTO PWD_HASH, ISLESSOR
	FROM USER_LOGIN
	WHERE USER_LOGIN.EMAIL=USERNAME;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `addProduct`(
	IN productId varchar(255),
	IN productName varchar(255),
	IN categoryId varchar(255),
	IN categoryName varchar(255),
	IN description varchar(255),
	IN rent int,
	IN quantity int,
	IN image longblob,
	IN registrationNo varchar(255))
BEGIN
	insert into PRODUCTS (PRODUCT_ID, PRODUCT_NAME, CATEGORY_ID, CATEGORY_NAME, DESCRIPTION, RENT, QUANTITY, IMAGE, REGISTRATION_NO) 
	values (productId, productName, categoryId, categoryName, description, rent, quantity, image, registrationNo);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `getProductByProductId`(
	IN productId varchar(255),
	IN registrationNo varchar(255),
	OUt productName varchar(255),
	OUt categoryId varchar(255),
	OUt categoryName varchar(255),
	OUt description varchar(1000),
	OUt rent int,
	OUt quantity int)
BEGIN
	select PRODUCT_NAME, CATEGORY_ID, CATEGORY_NAME, DESCRIPTION, RENT, QUANTITY
	INTO productName, categoryId, categoryName, description, rent, quantity
	from PRODUCTS where PRODUCTS.PRODUCT_ID=productId AND PRODUCTS.REGISTRATION_NO=registrationNo;
END $$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `getProduct`(
	IN productId varchar(255))
BEGIN
	select PRODUCT_ID, PRODUCT_NAME, CATEGORY_ID, CATEGORY_NAME, DESCRIPTION, RENT, QUANTITY, IMAGE, REGISTRATION_NO
	from PRODUCTS where PRODUCTS.PRODUCT_ID=productId;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `deleteProduct`(IN productId varchar(255), IN registrationNo varchar(255))
BEGIN
	DELETE FROM PRODUCTS WHERE PRODUCTS.PRODUCT_ID=productId AND PRODUCTS.REGISTRATION_NO=registrationNo;
END$$
DELIMITER ;


DELIMITER $$
Create PROCEDURE `getProductByCategoryId`(
	IN categoryId int(30),
	OUT productId varchar(255),
	OUT productName varchar(255),
	OUT categoryName varchar(255),
	OUT description varchar(255),
	OUT rent int,
	OUT quantity int,
	OUT image longblob,
	OUT registrationNo varchar(255))
BEGIN

	select CATEGORY_ID, PRODUCT_ID, PRODUCT_NAME, CATEGORY_NAME, DESCRIPTION, RENT, QUANTITY, IMAGE, REGISTRATION_NO
	into categoryId, productId, productName, categoryName, description, rent, quantity, image, registrationNo
	from PRODUCTS where PRODUCTS.CATEGORY_ID=categoryId;
END$$
DELIMITER ;

DELIMITER $$
Create PROCEDURE `getProductByName`(
	IN productName int(30),
	OUT productId varchar(255),
	OUT categoryId varchar(255),
	OUT categoryName varchar(255),
	OUT description varchar(255),
	OUT rent int,
	OUT quantity int,
	OUT image longblob,
	OUT registrationNo varchar(255))
BEGIN

	select PRODUCT_NAME, PRODUCT_ID, CATEGORY_ID, CATEGORY_NAME, DESCRIPTION, RENT, QUANTITY, IMAGE, REGISTRATION_NO
	into productName, productId, categoryId, categoryName, description, rent, quantity, image, registrationNo
	from PRODUCTS where PRODUCTS.PRODUCT_NAME=productName;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `getProductByProdID`(
	IN productId varchar(255),
	IN productName varchar(255))
BEGIN
	select *
	from PRODUCTS where PRODUCTS.PRODUCT_ID=productId AND PRODUCTS.PRODUCT_NAME=productName;
END$$
DELIMITER ;