function action(classname) {
    $("." + classname).removeClass("d-none");
    $('#row > *').not($('.' + classname)).addClass('d-none');
}

function plus(idd) {

    let value = parseInt($("input[name='count']#" + idd).val());
    $('input#' + idd).val(value + 1);
}

function minus(idd) {
    let value = parseInt($("input[name='count']#" + idd).val());
    if (value > 0) {
        $('input#' + idd).val(value - 1);

    }
}
//{"productOrders":[{"product":{"name":"Whopper"},"quantity":3},{"product":{"name":"Coca-Cola"},"quantity":5}]}
function create(product,quantity){
    return '{"product":{"name":"'+product+'"},"quantity":'+quantity+'}';
}
function generate(productArray){
    let empty = '{"productOrders":[';

    for (let i=0;i<productArray.length;i++){
        if(productArray.length-1==i){
            empty+=productArray[i];
        }else{
            empty+=productArray[i]+',';

        }

    }
    return empty+']}';

}
var products=[];
function addToCart(idd) {
    let count = parseInt($("input[name='count']#" + idd).val());



    if (count > 0) {
        let product=create(idd,count);
        products.push(product);
        let price = parseInt($("input." + idd).val());
        let item = $('#items').text();
        if (item == 'No items selected') {
            $('#items').text("");
        }
        $('#items').append('<br>' + idd + ' x ' + count + ' overall: ' + count * price + ' tg');
        $('#overall').val(parseInt($('#overall').val()) + count * price);
        $('#overall').removeClass("d-none");
        $('#over').removeClass("d-none");
        $('#tg').removeClass("d-none");
        $('.options').removeClass("d-none");

    } else {
        alert("Firstly,choose the quantity")

    }


}
function buy(){
    var json=generate(products);
    alert(json)
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/test",
        data:json,
        dataType: 'json',
        cache: false,
        timeout: 600000,    success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";

            $('#message').html(json);
            $('#ajaxreader').removeClass("d-none")
            $('#ajaxreader').addClass("alert alert-success alert-dismissible fade show")


        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#message').html(json);
            $('#ajaxreader').removeClass("d-none")
            $('#ajaxreader').addClass("alert alert-danger alert-dismissible fade show")



        }
    });

}

function resetCart() {
    products=[]
    $('#overall').val(parseInt(0));
    let item = $('#items').text();
    if (item != 'No items selected') {
        $('#items').text("No items selected");
        $('#overall').addClass("d-none");
        $('#over').addClass("d-none");
        $('#tg').addClass("d-none");
        $('.options').addClass("d-none");

    }
}




