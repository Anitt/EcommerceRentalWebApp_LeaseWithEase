function form_validation() {
    $("div.name-error").empty();
    $("div.bill-addr-error").empty();
    $("div.card-no-error").empty();
    $("div.cvv-cd-error").empty();
    $("div.exp-dt-error").empty();

    var name_pattern = /^[A-Za-z ]+$/;
    var bill_addr_pattern = /^\d+\s{1}[a-zA-Z]+\s{1}[a-zA-Z]+$/;
    var card_no_pattern = /^\d{16}$/;
    var cvv_code_patter = /^\d{3}$/;
    var exp_date_pattern = /^(0[1-9]|1[0-2]|[1-9])\/([1-9][1-9])$/;

    var card_name = document.forms["pay-form"]["name-on-card"].value;
    var bill_addr = document.forms["pay-form"]["bill-address"].value;
    var card_no = document.forms["pay-form"]["card-no"].value;
    var cvv_code = document.forms["pay-form"]["cvv-code"].value;
    var exp_date = document.forms["pay-form"]["expiry-date"].value;

    if(card_name == "")
    {
        $("div.name-error").append("<h7 class='text-center'>Please enter the card name</h7>");
        return false;

    }
    else if (!name_pattern.test(card_name))
    {
        $("div.name-error").append("<h7 class='text-center'>Name should contain only alphabets</h7>");
        return false;

    }

    if(bill_addr == "")
    {
        $("div.bill-addr-error").append("<h7 class='text-center'>Please enter the billing address</h7>");
        return false;

    }
    else if (!bill_addr_pattern.test(bill_addr))
    {
        $("div.bill-addr-error").append("<h7 class='text-center'>Please enter a valid billing address</h7>");
        return false;


    }

    if(card_no == "")
    {
        $("div.card-no-error").append("<h7 class='text-center'>Please enter the card number</h7>");
        return false;

    }
    else if (!card_no_pattern.test(card_no))
    {
        $("div.card-no-error").append("<h7 class='text-center'>Please enter a valid card number</h7>");
        return false;

    }

    if(cvv_code == "")
    {
        $("div.cvv-cd-error").append("<h7 class='text-center'>Please enter the CVV Code</h7>");
        return false;

    }
    else if (!cvv_code_patter.test(cvv_code))
    {
        $("div.cvv-cd-error").append("<h7 class='text-center'>CVV Code must be 3 digit</h7>");
        return false;

    }

    if(exp_date == "")
    {
        $("div.exp-dt-error").append("<h7 class='text-center'>Please enter the expiry date</h7>");
        return false;

    }
    else if (!exp_date_pattern.test(exp_date))
    {
        $("div.exp-dt-error").append("<h7 class='text-center'>Please enter the expiry date in valid pattern</h7>");
        return false;

    }

    return true;
}