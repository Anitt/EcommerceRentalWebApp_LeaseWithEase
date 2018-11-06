function onAddProductButtonClick() {
	$('#addProductForm').submit(function (e) {
		e.preventDefault();
		e.stopPropagation();

		/**
		 * https://stackoverflow.com/a/29152483
		 * 
		 * The number of event handlers fired with every subsequent click increases.
		 */
		e.stopImmediatePropagation();

		var formData = new FormData(this);
		$.ajax({
			url: "/product/addproduct",
			type: 'POST',
			data: formData,
			success: onAddProductResponse,
			failure: failureCallback,
			cache: false,
			contentType: false,
			processData: false
		});
	});
}

function onAddProductResponse(response, status) {
	if ("success" == status && response && response.hasOwnProperty("status") && "success" == response.status) {
		var form = document.getElementById("addProductForm");
		form.reset();
		showSuccess("Product added successfully.", "productErrorMessage");
	} else {
		showError(response.data, "productErrorMessage");
	}
}

function onRemoveProductButtonClick() {
	$('#removeProductForm').submit(function (e) {
		e.preventDefault();
		var productId = document.getElementById("productId").value,
			data = { "productId": productId.trim() };
		callAjax("/product/removeproduct", data, onRemoveProductResponse, failureCallback);
	});
}

function onRemoveProductResponse(response, status) {
	if ("success" == status && response && response.hasOwnProperty("status") && "success" == response.status) {
		var form = document.getElementById("removeProductForm");
		form.reset();
		form.elements["removeButton"].disabled = true;
		showSuccess("Product removed successfully.", "productErrorMessage");
	} else {
		showError(response.data, "productErrorMessage");
	}
}

function onGetProductButtonClick() {
	var productId = document.getElementById("productId").value,
		data = "productId=" + productId;
	callAjax("/product/getproductbyproductid", data, onGetProductResponse, failureCallback);
}

function onGetProductResponse(response, status) {
	var form = document.getElementById("removeProductForm");
	if ("success" == status && response && response.hasOwnProperty("status") && "success" == response.status) {
		var data = response.data, i;
		if (data) {
			$("#removeProductForm input").each(function () {
				var input =  $(this),
					name = input.attr('name'),
					value = data[name];
				if (value) {
					input.val(value);
				}
			});
			form.elements["removeButton"].disabled = false;
		}
	} else {
		form.elements["removeButton"].disabled = true;
		showError(response.data, "productErrorMessage");
	}
}

function failureCallback() {
	showError("Oops something went wrong.", "productErrorMessage");
}

function onErrorMessageClick() {
	var errorElement = document.getElementById("productErrorMessage");
	errorElement.style.display = "none";
}