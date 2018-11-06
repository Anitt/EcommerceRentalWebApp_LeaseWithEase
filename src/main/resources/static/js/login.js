$(document).ready(function () {
	$('#login-form').submit(function (e) {
		e.preventDefault();
		e.stopPropagation();

		/**
		 * https://stackoverflow.com/a/29152483
		 * 
		 * The number of event handlers fired with every subsequent click increases.
		 */
		e.stopImmediatePropagation();
		var username = $("#username").val();

		var data = "username=" + username + "&password=" + $("#password").val();
		callAjax("/security/login", data, function (response, status, jqXhr) {
			if (status == "success" && response.status == "success") {
				sessionStorage.setItem("LOGGED_IN_USER_ID", username);
				sessionStorage.setItem("IS_USER_LOGGED_IN", true);
				window.location = "index.html";
			} else {
				failureFn();
			}
		}, failureFn);
	});
});

function failureFn() {
	alert("Failed to login.");
}