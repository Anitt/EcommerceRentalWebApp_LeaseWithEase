function display_products()
{
    // Product item list
    // image size 1000x887
    let pdt_name = ["ABC headphones","DEF headphones","GHI headphones","JKL headphones","MNO headphones","PQR headphones"];
    let pdt_rating=[3,4,2,4,3,2];
    let pdt_price = [100,250,150,100,150,200];
    let pdt_image =['images/Headphone.jpeg','images/headphones2.jpg','images/headphones31.png','images/headphones41.jpg','images/headphones51.jpg','images/hedphone61.jpg'];

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