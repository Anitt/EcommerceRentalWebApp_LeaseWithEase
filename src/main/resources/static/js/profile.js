$(window).on('load', function () {
	callAjax("/profile/getprofile", {}, getProfileResponse, failureCallback);
});

function getProfileResponse(response, status) {
	if ("success" == status && response && response.hasOwnProperty("status") && "success" == response.status) {
		var data = response.data,
			button;

		$("#userProfileForm input").each(function () {
			var input = $(this),
				name = input.attr('name'),
				value = data[name];
			if (value) {
				input.val(value);
			}
		});
	} else {
		showError(response.data, "profileErrorMessage");
	}
}

function onUpdateProfileButtonClick() {
	$('#userProfileForm').submit(function (e) {
		e.preventDefault();
		e.stopPropagation();

		/**
		 * https://stackoverflow.com/a/29152483
		 * 
		 * The number of event handlers fired with every subsequent click increases.
		 */
		e.stopImmediatePropagation();

		var data = {};
		$("input[name=phone], input[name=streetAddress], input[name=postalCode]").each(function () {
			var input = $(this),
				name = input.attr('name')
			value = input.val();
			data[name] = value;
		});
		callAjax("/profile/updateprofile", data, onUpdateProfileResponse, failureCallback);
	});
}

function onUpdateProfileResponse(response, status) {
	if ("success" == status && response && response.hasOwnProperty("status") && "success" == response.status) {
		showSuccess("Profile updated successfully.", "profileErrorMessage");
	} else {
		showError(response.data, "profileErrorMessage");
	}
}

function failureCallback() {
	showError("Oops something went wrong.", "registerMessage");
}

function onErrorMessageClick() {
	var errorElement = document.getElementById("profileErrorMessage");
	errorElement.style.display = "none";
}