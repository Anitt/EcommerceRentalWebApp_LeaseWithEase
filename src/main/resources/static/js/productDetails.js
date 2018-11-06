$(window).on('load', function () {
	var url = new URL(window.location.href);
	data = {"productID" : url.searchParams.get("Product_ID"), "productName" : url.searchParams.get("Product_Name")};
	onProductDetailsLoad(data);
});

var prodID;

function getProdID() {
	var url = new URL(window.location.href);
	var prodID = url.searchParams.get("Product_ID");
	return prodID;
}

function onAddToCart() {

	var data = {
		"productId": getProdID(),
		"userEmail": "a@b.com",
		"productName": document.getElementById("span-product-name").innerText,
		"quantity": document.getElementsByName("quantity")[0].value,
		"days": document.getElementsByName("days")[0].value
	};
	callAjaxJSON("/cart/addproduct", data, onAddProductToCart, errorFn);

}

function onAddProductToCart(response) {
	if (response && response.hasOwnProperty("status") && response.status && response["status"] == "success") {
		return window.location.href = '/cart.html';
	} else {
		errorFn();
	}
}

function onProductDetailsLoad(data) {
	callAjax("/product/getproductbyid", data, onLoadProductDetails, errorFn);
}

function createHTMLElement(productData) {
	createLeftElement(productData);
	createCenterElement(productData);
	createRightElement(productData);
}

function createLeftElement(productData) {
	var htmlElement = "";
	htmlElement += '  <div class="picture-box-parent">';
	htmlElement += '  <div class="picture-box">';
	htmlElement += '  <img src="data:image/jpg;base64,' + productData.prodImg + '">';
	htmlElement += '  </div>';
	htmlElement += '  </div>';

	var elementToAppend = $('.content-left');
	elementToAppend.append(htmlElement);

}

function createCenterElement(productData) {
	var htmlElement = "";
	htmlElement += '<div class="details-container">';
	htmlElement += '<span class="product-name">';
	htmlElement += '<span id="span-product-name" name="prod-name">' + productData.productName + '</span>';
	htmlElement += '<span id="rating" class="badge badge-primary span-rating">4.5</span></span>';
	htmlElement += '<div class="cost-container">';
	htmlElement += '<span id="rent-text" class="rating-text">Rent: </span>';
	htmlElement += '<span id="rent-amount" class="rating-amount">$' + productData.rent + '</span>';
	htmlElement += '<span id="min-duration" class="min-duration"> per Day</span></div>';
	htmlElement += '<div class="duration-container">';
	htmlElement += '<span id="duration-text" class="rating-text">Days: </span>';
	htmlElement += '<input type="number" name="days" min="1" max="5" value="1" onkeydown="return false"></div>';
	htmlElement += '<div><ul class="list-group">';
	htmlElement += '<li class="list-group-item list-group-item-primary list-details">Details:</li>';
	htmlElement += '<li class="list-group-item">' + productData.description + '</li>';
	htmlElement += '</ul><br /><br /></div></div>';

	var elementToAppend = $('.content-center');
	elementToAppend.append(htmlElement);
}

function createRightElement(productData) {

}

function onLoadProductDetails(response) {
	if (response && response.hasOwnProperty("status") && response["status"] && response["status"] == "success") {

		var productData = response["data"];
		createHTMLElement(productData);
	} else {
		errorFn();
	}

}

function errorFn() {
	alert("Oops! Something went wrong. Try again.");
}