/*== On cart page load ===*/

$(window).on('load', function () {
	setLoadMask();
	onLoadCall();
});

function onLoadCall() {
	try {
		callAjax("/cart/load", {}, loadcartresponse, errorFn);
	} catch (err) {
		removeLoadMask();
	}
}

function loadcartresponse(loadresponse) {
	try {
		if (loadresponse && loadresponse.hasOwnProperty("data") && loadresponse["data"] != null) {
			var cartdata = loadresponse["data"];
			var lenCartData = cartdata.length;
			for (var index = 0; index < lenCartData; index++) {
				createHTMLElement(cartdata[index]);
				onquantitychange();
			}
			if(lenCartData>0){
				createPaypalButton();
			}
			
		}
		else {
			alert("CART EMPTY.");
		}
	} catch (err) {

	} finally {
		removeLoadMask();
	}
}

function createPaypalButton() {
	paypal.Button.render({
		env: 'sandbox',
		client: {
			// Don't change the id.
			sandbox: 'AcPWaLYNTsqEuSBnomAkFD0ELV9KBZDsXjkHV_h1vwoGthhyArGJuCCQxBwfmY7OP5Lig2RH0nt284KG'
		},
		style: {
			color: 'blue',
			shape: 'rect',
			size: 'responsive'
		},
		payment: function (data, actions) {
			return actions.payment.create({
				// Set amount and currency here.
				transactions: [{
					amount: {
						total: onquantitychange(),
						currency: 'CAD'
					}
				}]
			});
		},
		onAuthorize: function (data, actions) {
			return actions.payment.execute().then(function () {
				ondeletecart();
				window.alert('Thank you for your purchase!');
				// Handler for after payment is done.
			});
		}
	}, '#paypal-button');
}

function createHTMLElement(cartdata) {
	var htmlElement = "";
	var stockInfo = "In Stock";

	if (cartdata.prodQuantity < 1) {
		var stockInfo = "Out of Stock";
	}

	htmlElement += "<tr cartID=" + cartdata.catID + ">";
	//htmlElement+='<td class="cartdataImage"><img class="cartelementicon" src="images/img1.jpg"></td>';
	htmlElement += '<td class="cartdataImage"><img class="cartelementicon" src="data:image/jpg;base64,' + cartdata.prodImg + '"></td>';
	htmlElement += '<td class="cartdataDetails">';
	htmlElement += '<div class="cartItemName">' + cartdata.prodName + '</div>';
	htmlElement += '<div class="cartItemAvailability">' + stockInfo + '</div>';
	htmlElement += '<div class="cartItemShipping">' + cartdata.prodQuantity + ' item(s) in stock</div>';
	htmlElement += '<div class="cartDetailsButtonGroup"><button class="cartDetailsBtn" onclick="onbtndeleteclick(this)">Delete</button></div>';

	htmlElement += '</td>';
	htmlElement += '<td class="cartdataPrice" name="price">$' + cartdata.prodRent + '</td>';
	htmlElement += '<td class="cartdataQuant"><input type="number" name="quantity" min="0" max="' + cartdata.prodQuantity + '" value=' + cartdata.cartQuantity + ' onkeydown="return false" onchange="onquantitychange(this)" ></td>';
	htmlElement += '<td class="cartdataDays"><input type="number" name="days" min="1" max="30" value=' + cartdata.cartDays + ' onkeydown="return false" onchange="ondayschange(this)" ></td>';
	htmlElement += '</tr>';

	var elementToAppend = $('.cartelement table tbody');
	elementToAppend.append(htmlElement);
}
function errorFn() {
	alert("Oops! Something went wrong. Try again.");
	removeLoadMask();
}
function onbtndeleteclick(currentElement) {
	setLoadMask()
	try {
		var rowElement = currentElement.parentElement.parentElement.parentElement;
		var cartID = rowElement.getAttribute("cartid");
		var data = "cartID=" + cartID;
		callAjax("/cart/removeitem", data, deleteitemresponse, errorFn);
	} catch (err) {
		removeLoadMask();
	}

}

function deleteitemresponse(response) {
	try {
		if (response && response.hasOwnProperty('status') && response.status == "success") 
		{
			location.reload();
		} 
		else 
		{
			alert("Delete was unsuccessful");
		}
	} catch (err) {
	} finally {
		removeLoadMask();
	}
}

function oncheckoutbtnclick() {
	return window.location.href = '/payment_checkout.html';
}

function ondayschange() {
	onquantitychange();
}
function onquantitychange() {

	var pricevalues = document.getElementsByName("price");
	var quantityvalues = document.getElementsByName("quantity");
	var dayvalues = document.getElementsByName("days");

	var lengthpricevalues = pricevalues.length;

	var subtotal = 0;

	var priceval;
	var quantval;
	var dayval;

	for (var indexpriceelements = 0; indexpriceelements < lengthpricevalues; indexpriceelements++) {
		quantval = quantityvalues[indexpriceelements].value;
		dayval = dayvalues[indexpriceelements].value;
		priceval = pricevalues[indexpriceelements].innerText;

		priceval = priceval.replace("$", "");

		subtotal = subtotal + parseFloat(priceval) * parseInt(quantval);
		if (subtotal != 0) {
			subtotal += 0.3 * dayval;
		}
	}
	subtotal = subtotal.toFixed(2);
	var subtotalelement = document.getElementsByName("subtotal")[0];
	var itemcountelement = document.getElementsByName("itemcount")[0];
	itemcountelement.innerText = "("+lengthpricevalues;
	if(lengthpricevalues<2){
		itemcountelement.innerText  = itemcountelement.innerText +	" item)";
	}else{
		itemcountelement.innerText  = itemcountelement.innerText +	" items)";
	}
	subtotalelement.innerText = "$" + subtotal;
	return subtotal;
}

function itemcountchange() {
	var itemcount = document.getElementsByName("itemcount");
	var rowcount = document.getElementsByTagName("tr").length;
	var suffix = " item";

	var totalitemcount = rowcount - 1;

	if (totalitemcount > 0) {
		if (totalitemcount > 1) {
			suffix = suffix + "s";
		}
	}
	itemcount[0].innerText = "(" + totalitemcount + suffix + ")";
}

function ondeletecart(){
	var url = "/cart/emptycart";
	callAjax(url, "", deleteitemresponse, errorFn);
}
