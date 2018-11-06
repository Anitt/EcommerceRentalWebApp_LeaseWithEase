function onSaveClick() {
	var formdetails = document.getElementById("profileeditform");
	var formelements = formdetails.elements;
	var length_formelements = formelements.length;
	var currelement;
	var objElements = {};
	var url = "/profile/lesseeprofileupdate";

	for (var indexFormelement = 0; indexFormelement < length_formelements; indexFormelement++) {

		currelement = formelements[indexFormelement];

		if (currelement.type != "submit" || currelement.type != "reset") {
			objElements[currelement.name] = currelement.value;
		}
	}
	return fieldValidation(objElements);
}

function fieldValidation(p_objElements) {

	if (p_objElements["confirmpassword"] === p_objElements["password"]) {
		return true;
	}
	return false;

}
