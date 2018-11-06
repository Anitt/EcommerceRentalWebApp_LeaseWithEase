//global Variables

var globalCacheProducts = [];

function display_products(productList) {
	if (productList == null)
		return;
	var totalLength = productList.length;

	$("div.products").empty();
	let pdt_index = 0;
	let pdt_row = 0;
	let pdt_row_length = Math.ceil(totalLength / 3); // no of rows required depends on no of products
	for (i = 0; i < pdt_row_length; i++) // loop for no of rows
	{
		pdt_row = pdt_row + 1;
		$("div.products").append("<div class='row pdt-row" + pdt_row + "'></div>"); //tag for rows

		for (j = 0; j < 3; j++) // 3 items per row
		{
			$("div.pdt-row" + pdt_row).append("<div class='col-md-4 col_" + (pdt_index + 1) + "'></div>");
			$("div.col_" + (pdt_index + 1)).append("<div class='pdt-box pdt-box" + (pdt_index + 1) + "'></div>");
			$("div.pdt-box" + (pdt_index + 1)).append("<div class='pdt-box-content pdt-box-cont" + (pdt_index + 1) + "'></div>");
			$("div.pdt-box-cont" + (pdt_index + 1)).append("<div class='product-image pdt-img" + (pdt_index + 1) + "'></div>");
			$("div.pdt-img" + (pdt_index + 1)).append("<a href='/renteeProductDetails.html?Product_ID=" + productList[pdt_index].productId + "&Product_Name="+productList[pdt_index].productName+"'><img src=\"data:image/jpg;base64," + productList[pdt_index].prodImg + "\" style='object-fit: contain'/></a>");
			$("div.pdt-box-cont" + (pdt_index + 1)).append("<div class='product-desc pdt-desc" + (pdt_index + 1) + "'></div>");
			$("div.pdt-desc" + (pdt_index + 1)).append("<a href='#' class='pdt-name' style='color: steelblue;font-size:1.1em;'>" + productList[pdt_index].productName + "</a>");
			$("div.pdt-desc" + (pdt_index + 1)).append("<div class='stars stars" + (pdt_index + 1) + "'></div>");
			
			var ratingRandom;
			if(productList[pdt_index].rating== undefined)
			{
				ratingRandom = Math.floor(Math.random() * 5) + 1;
			}
			else
			{
				ratingRandom = productList[pdt_index].rating;
			}
			
			for (k = 0; k < ratingRandom; k++) //checked stars
			{
				$("div.stars" + (pdt_index + 1)).append("<span class='fa fa-star checked'></span>");
			}

			//button price and add to cart button
			$("div.pdt-desc" + (pdt_index + 1)).append("<span class='product-price'>$" + productList[pdt_index].rent + "</span><br>");
			/* $("div.pdt-desc"+(pdt_index+1)).append("<div type='button' class='btn btn-default btn-primary'>Add to Cart</div>");
			 */
			pdt_index = pdt_index + 1;
			if (pdt_index > (totalLength - 1)) // break the loop when all the products are listed
			{
				break;
			}
		}
	}

}

function priceFilter() {

	if (globalCacheProducts == null || globalCacheProducts == undefined)
		return;

	var tempArray = [];

	if ($(".filters>ul>li>input:checked").length == 0) {
		display_products(globalCacheProducts);
		return;
	}

	if ($(".filters>ul>li>input#below50")[0].checked) {

		$.each(globalCacheProducts, function (index, value) {
			if (Number(value["rent"]) < 50) {
				tempArray.push(value);
			}
		});

	}

	if ($(".filters>ul>li>input#50to100")[0].checked) {
		$.each(globalCacheProducts, function (index, value) {
			if (Number(value["rent"]) >= 50 && Number(value["rent"]) <= 100) {
				tempArray.push(value);
			}
		});
	}

	if ($(".filters>ul>li>input#above100")[0].checked) {
		$.each(globalCacheProducts, function (index, value) {
			if (Number(value["rent"]) > 100) {
				tempArray.push(value);
			}
		});
	}

	display_products(tempArray);

}

$(document).ready(function () {
	var productName = getUrlParameter("productName");
	if(productName){
		$("#searchText").val(productName);
		onSearchClick(productName);
	} else{
		getByCurrentPageCategoryId();
	}

	$(".filters>ul>li>input").change(priceFilter);
});

function onSearchClick(searchText) {
	if(!searchText){
		searchText = document.getElementById("searchText").value;
	}
	if (searchText == "") {
		getByCurrentPageCategoryId();
	}
	if (searchText == null || searchText == undefined) {
		return;
	}

	// Get the Products
	$.ajax({
		url: "/product/getproductByName?productName=" + searchText,
		type: 'GET',
		success: onProductResponse,
		failure: onFailure,
		cache: false,
		contentType: false,
		processData: false
	});
}

function getByCurrentPageCategoryId() {

	var category_id = getUrlParameter("Category_Id");
	// Get the Products
	$.ajax({
		url: "/product/getproductByCategoryID?Category_Id=" + category_id,
		type: 'GET',
		success: onProductResponse,
		failure: onFailure,
		cache: false,
		contentType: false,
		processData: false
	});
}

function onProductResponse(response, status) {
	if ("success" == status && response && response.hasOwnProperty("status") && "success" == response.status) {
		display_products(response["data"]);
		globalCacheProducts = response["data"];
		setRandomRating();
	} else {
		onFailure(response.data);
	}
}

function setRandomRating(){
	var lenGlobalCacheProducts = globalCacheProducts.length;
	var ratingRandom;

	for(var index=0; index<lenGlobalCacheProducts; index++){
		ratingRandom = Math.floor(Math.random() * 5) + 1;
		globalCacheProducts[index].rating = ratingRandom;
	}
}


function onFailure(message) {
	alert("Oops! Something went wrong. Try again.");
}


function getUrlParameter(name) {
	name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
	var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
	var results = regex.exec(location.search);
	return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};