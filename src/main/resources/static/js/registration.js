function onRenteeRegisterButtonClick() {
	$('#renteeRegistrationForm').submit(function (e) {
		e.preventDefault();
		e.stopPropagation();

		/**
		 * https://stackoverflow.com/a/29152483
		 * 
		 * The number of event handlers fired with every subsequent click increases.
		 */
		e.stopImmediatePropagation();
		var url = "/registration/registerrentee",
			data = {}, password, confirmPassword;

		$("#renteeRegistrationForm input").each(function () {
			var inputField = $(this),
				fieldName = inputField.attr('name'),
				fieldValue = inputField.val();
			if (fieldValue) {
				data[fieldName] = fieldValue;
			}
		});
		password = data["password"];
		confirmPassword = data["confirmPassword"];
		if (confirmPassword == password) {
			callAjax(url, data, onAddRenteeResponse, failureCallback);
		} else {
			showError("Password and confirm password values should be same.", "registerMessage");
		}
	});
}

function onAddRenteeResponse(response, status) {
	if ("success" == status && response && response.hasOwnProperty("status") && "success" == response.status) {
		var form = document.getElementById("renteeRegistrationForm");
		form.reset();
		window.location = "/login.html";
	} else {
		showError(response.data, "registerMessage");
	}
}

function onRenterRegisterButtonClick() {
	$('#renterRegistrationForm').submit(function (e) {
		e.preventDefault();
		e.stopPropagation();

		/**
		 * https://stackoverflow.com/a/29152483
		 * 
		 * The number of event handlers fired with every subsequent click increases.
		 */
		e.stopImmediatePropagation();
		var url = "/registration/registerrenter",
			data = {}, password, confirmPassword;

		$("#renterRegistrationForm input").each(function () {
			var inputField = $(this),
				fieldName = inputField.attr('name'),
				fieldValue = inputField.val();
			if (fieldValue) {
				data[fieldName] = fieldValue;
			}
		});
		password = data["password"];
		confirmPassword = data["confirmPassword"];
		if (confirmPassword == password) {
			callAjax(url, data, onAddRenterResponse, failureCallback);
		} else {
			showError("Password and confirm password values should be same.", "registerMessage");
		}
	});
}

function onAddRenterResponse(response, status) {
	if ("success" == status && response && response.hasOwnProperty("status") && "success" == response.status) {
		var form = document.getElementById("renterRegistrationForm");
		form.reset();
		window.location = "/login.html";
	} else {
		showError(response.data, "registerMessage");
	}
}

function failureCallback() {
	showError("Oops something went wrong.", "registerMessage");
}

function onErrorMessageClick() {
	var errorElement = document.getElementById("registerMessage");
	errorElement.style.display = "none";
}