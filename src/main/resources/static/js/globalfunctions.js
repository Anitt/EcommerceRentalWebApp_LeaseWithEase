function onLogoutClick() {
	callAjax('/security/logout', '', function () {
		alert("Logout successfull.");
		sessionStorage.removeItem("IS_USER_LOGGED_IN");
		sessionStorage.removeItem("LOGGED_IN_USER_ID");
		window.location = "/index.html";
	}, function () {
		alert("Failed to logout.");
		window.location = "/error.html";
	});
}

function setLoadMask() {
	$.msg({
		bgPath: '/includes/loadmask/',
		content: "Loading"
	});
}

function removeLoadMask() {
	var msgContent = document.getElementById("jquery-msg-content");
	var msgOverlay = document.getElementById("jquery-msg-overlay");
	if (msgContent) {
		msgContent.remove();
	}
	if (msgOverlay) {
		msgOverlay.remove();
	}
}

function getLoggedInUserId() {
	return sessionStorage.getItem("LOGGED_IN_USER_ID");
}

function isUserLoggedIn() {
	return !!sessionStorage.getItem("IS_USER_LOGGED_IN");
}

function renderMenuItems(lessor) {
	var userLoggedIn = isUserLoggedIn();
	var topRightNavigation = $("#toprightnavigation");
	if (userLoggedIn) {
		if (lessor) {
			sessionStorage.setItem("isRenterLoggedIn", true);
			topRightNavigation.append("<span class=\"dropdown\"><button class=\"dropbtn\">Product<span class=\"dropdown-content\"><a href=\"./addProducts.html\">Add Product</a><a href=\"./removeProducts.html\">Remove Product</a></span></button></span>");
			topRightNavigation.append("<a href=\"./renterProfile.html\"><span><span class=\"glyphicon glyphicon-user\"></span>Profile</span></a>");
			topRightNavigation.append("<a href=\"javascript:onLogoutClick();\"><span>Logout</span></a>");
		} else {
			sessionStorage.setItem("isRenterLoggedIn", false);
			topRightNavigation.append("<a href=\"./renteeProfile.html\"><span><span class=\"glyphicon glyphicon-user\"></span>Profile</span></a>");
			topRightNavigation.append("<a href=\"./cart.html\"><span><span class=\"glyphicon glyphicon-shopping-cart\"></span>Cart</span></a>");
			topRightNavigation.append("<a href=\"javascript:onLogoutClick();\"><span>Logout</span></a>");
		}
	} else {
		topRightNavigation.append("<span class=\"dropdown\"><button class=\"dropbtn\">Register<span class=\"dropdown-content\"><a href=\"./registerRentee.html\">Register as Lessee</a><a href=\"./registerRenter.html\">Register as Lessor</a></span></button></span>");
		topRightNavigation.append("<a href=\"./login.html\"><span>Login</span></a>");
	}
}

function showError(message, elementId) {
	var element;
	if (elementId) {
		element = document.getElementById(elementId);
		element.style.display = "block";
		element.classList.add("error");
		element.classList.remove("success");
		if (message) {
			element.innerHTML = message;
		}
	}
}

function showSuccess(message, elementId) {
	var element;
	if (elementId) {
		element = document.getElementById(elementId);
		element.style.display = "block";
		element.classList.add("success");
		element.classList.remove("error");
		if (message) {
			element.innerText = message;
		}
	}
}

//Search field changes
function onSearchClick() {
	var searchText = document.getElementById("searchText").value;
	if(searchText){
		if(sessionStorage.getItem("isRenterLoggedIn") && sessionStorage.getItem("isRenterLoggedIn")=="true"){
			window.location = "renterProductsList.html?productName="+searchText;
		} else{
			window.location = "renteeProductsList.html?productName="+searchText;
		}
	}
}