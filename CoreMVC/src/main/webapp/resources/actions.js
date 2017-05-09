/**
 * Created by Дмитрий on 09.05.2017.
 */
$(document).ready(function() {
    $(document).on("change", ".seat-select", function() {
        elements = document.getElementsByClassName("seat-select");
        for (var i = 0; i < elements.length; i++) {
            //console.log(i);
            if (elements[i].id != this.id)
                elements[i].selectedIndex = -1;
        }
        priceitem = document.getElementById("price_item");
        price = this;
            //$(".seat-select").find(":selected").val();
        console.log(price);
        /*console.log(seat);
        priceitem.value = seat.price;*/
    });
});