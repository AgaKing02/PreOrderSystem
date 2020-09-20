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
function create(product, quantity) {
    return '{"product":{"name":"' + product + '"},"quantity":' + quantity + '}';
}

function generate(productArray) {
    let empty = '{"productOrders":[';

    for (let i = 0; i < productArray.length; i++) {
        if (productArray.length - 1 == i) {
            empty += productArray[i];
        } else {
            empty += productArray[i] + ',';

        }

    }
    return empty + ']}';

}

var products = [];

function addToCart(idd) {
    let count = parseInt($("input[name='count']#" + idd).val());


    if (count > 0) {
        let product = create(idd, count);
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

function buy() {
    var json = generate(products);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/test",
        data: json,
        dataType: 'json',
        cache: false,
        timeout: 600000, success: function (data) {

            var json = "<h4>Order</h4><h1>Created</h1><button class='btn btn-primary'><a href='/api/orders' style='color: white;'>See the order</a></button>";


            $('#message').html(json);

            styles("success")


        },
        error: function () {
            var json = "<h4>Order</h4><button class='btn btn-primary'>Not Created</button>";
            $('#message').html(json);
            styles("danger")


        }
    });

}

function styles(mode) {
    $('#ajaxreader').removeClass("d-none")
    if (mode == "success") {
        $('#ajaxreader').addClass("alert alert-success alert-dismissible fade show")
    }else {
        $('#ajaxreader').addClass("alert alert-danger alert-dismissible fade show")

    }
    $('#ajaxreader').addClass("alert alert-success alert-dismissible fade show")
    $('body>*').not($('#layer')).css("opacity","0.5")


}


function resetCart() {
    products = []
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
function action2(){
    $('#ajaxreader').addClass('d-none');
    $('body>*').css("opacity","1")

}




