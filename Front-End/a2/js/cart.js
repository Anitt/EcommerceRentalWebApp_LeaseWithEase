function onbtndeleteclick(currentElement) {

	var rowElement = currentElement.parentElement.parentElement.parentElement;
	rowElement.remove();
	onquantitychange();
	itemcountchange();
}

function onquantitychange() {
		
	var pricevalues = document.getElementsByName("price");
	var quantityvalues = document.getElementsByName("quantity");
	var lengthpricevalues = pricevalues.length;
	
	var subtotal=0;
	
	var priceval;
	var quantval;
	
	
	
	for (var indexpriceelements = 0; indexpriceelements < lengthpricevalues; indexpriceelements++) {
		quantval = quantityvalues[indexpriceelements].value;
		priceval = pricevalues[indexpriceelements].innerText;
		
		priceval = priceval.replace("$","");
		subtotal = subtotal+ parseFloat(priceval) * parseInt(quantval);
			
	}
	subtotal = subtotal.toFixed(2);
	var subtotalelement = document.getElementsByName("subtotal")[0];
	//var subtotal = subtotalelement.innerText + "$";
	subtotalelement.innerText = "$" + subtotal ;
	console.log(subtotal);

}

function itemcountchange(){
	var itemcount = document.getElementsByName("itemcount");
	var rowcount = document.getElementsByTagName("tr").length;
	var suffix = " item";
	
	var totalitemcount = rowcount-1;
	
	if(totalitemcount>0){		
		if(totalitemcount>1){
			suffix = suffix + "s";
		}
	}
	itemcount[0].innerText = "("+totalitemcount + suffix+ ")";	
}
