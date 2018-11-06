function form_validation() {
    //Error tags are initially made empty
    $("div.name-error").empty();
    $("div.bill-addr-error").empty();
    $("div.card-no-error").empty();
    $("div.cvv-cd-error").empty();
    $("div.exp-dt-error").empty();

    // Regular Expression patterns
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

    //Field validations

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
    //when all the field validations are success then success message
    alert('Payment successful!');
    return true;
}
function display_products()
{
    // Product item list
    // image size 1000x887
    let pdt_name = ["ABC headphones","DEF headphones","GHI headphones","JKL headphones","MNO headphones"];
    let pdt_rating=[3,4,2,4,3];
    let pdt_price = [100,250,150,100,150];
    let pdt_image =['images/Headphone.jpeg','images/headphones2.jpg','images/headphones31.png','images/headphones41.jpg','images/headphones51.jpg'];

    $("div.products").empty();
    let pdt_index = 0;
    let pdt_row = 0;
    let pdt_row_length = Math.ceil(pdt_name.length/3); // no of rows required depends on no of products
    for(i=0;i<pdt_row_length ;i++) // loop for no of rows
    {
        pdt_row = pdt_row + 1;
        $("div.products").append("<div class='row pdt-row"+pdt_row+"'></div>"); //tag for rows

        for(j=0;j<3;j++) // 3 items per row
        {

            $("div.pdt-row"+pdt_row).append("<div class='col-md-3 col_"+(pdt_index+1)+"'></div>");
            $("div.col_"+(pdt_index+1)).append("<div class='pdt-box pdt-box"+(pdt_index+1)+"'></div>");
            $("div.pdt-box"+(pdt_index+1)).append("<div class='pdt-box-content pdt-box-cont"+(pdt_index+1)+"'></div>");
            $("div.pdt-box-cont"+(pdt_index+1)).append("<div class='product-image pdt-img"+(pdt_index+1)+"'></div>");
            $("div.pdt-img"+(pdt_index+1)).append("<img src='"+pdt_image[pdt_index]+"'style='width: 100%;object-fit: contain'/>");
            $("div.pdt-box-cont"+(pdt_index+1)).append("<div class='product-desc pdt-desc"+(pdt_index+1)+"'></div>");
            $("div.pdt-desc"+(pdt_index+1)).append("<a href='#' class='pdt-name'>"+pdt_name[pdt_index]+"</a><br>");
            $("div.pdt-desc"+(pdt_index+1)).append("<div class='stars stars"+(pdt_index+1)+"'></div>");

            for (k=0;k < pdt_rating[pdt_index];k++) //checked stars
            {
                $("div.stars"+(pdt_index+1)).append("<span class='fa fa-star checked'></span>");
            }
            for(l=0;l<(5 - pdt_rating[pdt_index]);l++) // unchecked stars
            {
                $("div.stars"+(pdt_index+1)).append("<span class='fa fa-star '></span>");
            }
            //button price and add to cart button
            $("div.pdt-desc"+(pdt_index+1)).append("<span class='product-price'>"+pdt_price[pdt_index]+"</span><br>");
            $("div.pdt-desc"+(pdt_index+1)).append("<div type='button' class='btn btn-default btn-primary'>Add to Cart</div>");

            pdt_index = pdt_index + 1;
            if(pdt_index > (pdt_name.length-1)) // break the loop when all the products are listed
            {
                break;
            }
        }


    }


}
// to return to home page on click of application logo
function tohome(loc) {
    var url="index.html";
    if (loc == "non-payment") // if the logo button is clicked in non payment pages , it will directly direct to homepage
    {
        window.location.href=url;
    }
    else // if logo button is clicked in payment page then user has to confirm to leave the page
    {
        if(window.confirm("Are you sure you want to leave?"))
        {
            window.location.href=url;
        }
        else
        {
            die();
        }


    }

}

