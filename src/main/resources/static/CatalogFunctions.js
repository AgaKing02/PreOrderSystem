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

function animation() {
    let preloader = document.querySelector('.preloader');
    preloader.style.display = 'flex';
    setTimeout(() => {
        preloader.remove();
    }, 600);
}

function buy() {
    animation();
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
        error: function (jqXHR, error, errorThrown) {
            var json = "<h4>Order</h4><button class='btn btn-primary'>" + jqXHR.responseText + "</button>";
            $('#message').html(json);
            styles("danger")
        }
    });

}

function styles(mode) {
    $('#ajaxreader').removeClass("d-none")
    if (mode == "success") {
        $('#ajaxreader').addClass("alert alert-success alert-dismissible fade show")
    } else {
        $('#ajaxreader').addClass("alert alert-danger alert-dismissible fade show")

    }
    $('#ajaxreader').addClass("alert alert-success alert-dismissible fade show")
    $('body>*').not($('#layer')).css("opacity", "0.5")


}


function resetCart() {
    // if(sessionStorage.getItem("link")!=null){
    //     window.location.href=sessionStorage.getItem("link");
    //
    // }

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

function action2() {
    $('#ajaxreader').addClass('d-none');
    $('body>*').css("opacity", "1")

}

// localhost:8081/catalog?chair=2

function beka() {
    if (window.location.href.substr(-7, 5) === "chair") {
        createCookie("bekzat", window.location.href, 1);
    }
}

beka();


function aga() {
    if (readCookie("bekzat") != null) {
        window.location.replace(readCookie("bekzat"));
    } else {
        window.location.replace("http://localhost:8081/catalog");
    }
}


// function newDoc(chairNumber) {
//
//     createCookie(chairNumber,document.URL, 1);
//     if(window.location.href=="http://localhost:8081/catalog"){
//         location.replace("http://localhost:8081/catalog/?chair="+chairNumber);
//     }
//
//     alert(document.cookie);
//
//     if(document.cookie){
//         '<div><a>'
//         window.location.href="http://localhost:8081/catalog/?chair="+chairNumber;
//         '</a></div>'
//     }
//     else if(!document.cookie){
//         '<h1> Agakhan mal</h1>'
//     }
// }

function createCookie(name, value, days) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        var expires = "; expires=" + date.toGMTString();
    } else var expires = "";
    document.cookie = name + "=" + value + expires + "; path=/";
}

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1, c.length);
        }
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}

function deleteOrder(id) {
    var form = document.getElementById("delete-form " + id);
    var choice = confirm("The order would be deleted");
    if (choice) {
        form.submit();
    }

}


