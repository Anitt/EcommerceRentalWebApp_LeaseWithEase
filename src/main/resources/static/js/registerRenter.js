//Seller registration
function onLesserRegisterButtonClick() {
	$('#lesserRegistrationForm').submit(function (e) {
		e.preventDefault();
		e.stopPropagation();

		/**
		 * https://stackoverflow.com/a/29152483
		 * 
		 * The number of event handlers fired with every subsequent click increases.
		 */
		e.stopImmediatePropagation();
		var url = "/registration/registerlesser",
			data = {},
			password, confirmPassword;

		$("#lesserRegistrationForm input").each(function () {
			var inputField = $(this),
				fieldName = inputField.attr('name'),
				fieldValue = inputField.val();
			if (fieldValue) {
				data[fieldName] = fieldValue;
			}
		});
		if (fieldValidation(data)) {
			password = data["password"];
			confirmPassword = data["confirmPassword"];
			if (password == confirmPassword) {
				callAjax(url, data, onAddLesserResponse, failureCallback);
			} else {
				failureCallback("Password and confirm password should be same.");
			}
		}
	});
}

function onAddLesserResponse(response, status) {
	if ("success" == status && response && response.hasOwnProperty("status") && "success" == response.status) {
		var form = document.getElementById("lesserRegistrationForm");
		form.reset();
		alert("You are registered successfully.");
	} else {
		alert(response.data);
	}
}


function failureCallback(message) {
	var errorElement = document.getElementById("lesserErrorMessage");
	if (message) {
		errorElement.innerHTML = message;
	}
	errorElement.style["display"] = "block";
}

function fieldValidation(data) {
	var name_pattern = /^[a-zA-Z ]+$/;
	var email_patrn = /^[_a-z0-9_.-]+@[a-z0-9-]+(\.[a-z0-9-.]+)$/;
	var address_pattern = /^\d+\s{1}[a-zA-Z]+\s{1}[a-zA-Z]+$/;
	var post_cd_patrn = /^[A-Z]{1}\d{1}[A-Z]{1} \d{1}[A-Z]{1}\d{1}$/;
	var reg_no_pattern = /^[A-Z]{2}[0-9]+$/;
	var phone_pattern = /^\d{3}-\d{3}-\d{4}$/;
	var pwd = data["Password"];

	if (!reg_no_pattern.test(data["registrationNumber"])) {
		alert("Please enter a valid Registation number!");
		return false;
	}
	if (!name_pattern.test(data["firstName"])) {
		alert("Names should contain alphabets only!");
		return false;
	}
	if (!name_pattern.test(data["lastName"])) {
		alert("Names should contain alphabets only!");
		return false;
	}
	if (!email_patrn.test(data["email"])) {
		alert("Please enter a valid E-Mail address!");
		return false;
	}
	if (!phone_pattern.test(data["phoneNumber"])) {
		alert("Enter valid Phone no format!");
		return false;
	}
	if (!address_pattern.test(data["streetAddress"])) {
		alert("Please enter Mailing address in valid format!");
		return false;
	}
	if (!post_cd_patrn.test(data["postalCode"])) {
		alert("Please enter a valid Postal Code!");
		return false;
	}
	return true;
}