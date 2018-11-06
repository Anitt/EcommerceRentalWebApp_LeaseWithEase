function callAjax(url, data, successCallback, failureCallback) {
	$.ajax({
		url: url,
		type: 'post',
		data: data,
		success: function (data, status, jqXHR) {
			var newUrl = jqXHR.getResponseHeader("redirect-header");
			successCallback.apply(this, arguments);
			if(newUrl != "" && newUrl != null) {
				window.location = newUrl;
			}
		},
		error: function (jqXHR) {
			var newUrl = jqXHR.getResponseHeader("redirect-header");
			failureCallback.apply(this, arguments);
			if(newUrl != "" && newUrl != null) {
				window.location = rednewUrlirectUrl;
			}
		}
	});
}
function callAjaxJSON(url, data, successCallback, failureCallback) {
	$.ajax({
		url: url,
		contentType: "application/json; charset=utf-8",
		type: 'post',
		data: JSON.stringify(data),
		success: function (data, status, jqXHR) {
			var newUrl = jqXHR.getResponseHeader("redirect-header");
			successCallback.apply(this, arguments);
			if(newUrl != "" && newUrl != null) {
				window.location = newUrl;
			}
		},
		error: function (jqXHR) {
			var newUrl = jqXHR.getResponseHeader("redirect-header");
			failureCallback.apply(this, arguments);
			if(newUrl != "" && newUrl != null) {
				window.location = rednewUrlirectUrl;
			}
		}
	});
}
function onLogoutClick() {
	callAjax('/security/logout', '', successFn, failureFn);
}

function successFn() {
	alert("Logout successfull.");
	window.location = "/index.html";
}

function failureFn() {
	alert("Failed to logout.");
	window.location = "/error.html";
}