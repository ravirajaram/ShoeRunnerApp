var rootURL = "http://localhost:8084/ShoeRunner/rest";
var currentShoeRunnerRequest;

$(document).ready(function () {
    $('.mainArea').hide();
    $('.leftArea').hide();
    $('.rightArea').hide();
    $('.header').hide();
    $('#description').hide();
});

$('#login').click(function () {
    search($('#role').val());
    return false;
});

$('#shoeRunnerRequest a').live('click', function () {
    findById($(this).data('identity'));
});

// Replace broken images with generic wine bottle
$("img").error(function () {
    $(this).attr("src", "pics/shoe.jpg");

});

$('#btnAdd').click(function() {
	createRequest($('#searchKey').val());
	return false;
});
    
function createRequest(upc) {
	console.log('createRequest');
	$.ajax({
		type: 'GET',
		url: rootURL + '/login' + '/upc/' + upc,
		dataType: "json", // data type of response
		success: renderListNew
	});
}

function search(role) {
    if (role == 'ShoeRunner') {
        findAll();
    }
    if (role == 'SalesPerson') {
         $('.header').show();
        findAll();
    }
    
}

function findAll() {
    console.log('findall: ');
    $.ajax({
        type: 'GET',
        url: rootURL + '/login' + '/user/' + 'ravi' + '/role/' + 'shoerunner',
        dataType: "json",
        success: renderList
    });
}

function findById(id) {
    console.log('findById: ' + id);
    $.ajax({
        type: 'GET',
        url: rootURL + '/login' + '/id/' + id,
        dataType: "json",
        success: function (data) {
            console.log('findById success: ' + data.name);
            currentShoeRunnerRequest = data;
            renderDetails(currentShoeRunnerRequest);
        }
    });
}

function renderList(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    $('.mainArea').show();
    $('.leftArea').show();
    $('.rightArea').show();
    $('.login-card').hide();
    $('#shoeRunnerRequest li').remove();
    $.each(list, function (index, shoreRunnerRequest) {
        $('#shoeRunnerRequest').append('<li><a href="#" data-identity="' + shoreRunnerRequest.id + '">' + "REQUEST ID:" + shoreRunnerRequest.id + '</a></li>');
    });
}

function renderListNew(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    var currentRequest = list.pop();
    $('.mainArea').show();
    $('.leftArea').show();
    $('.rightArea').show();
    $('.login-card').hide();
    $('#shoeRunnerRequest li').remove();
    $.each(list, function (index, shoreRunnerRequest) {
        $('#shoeRunnerRequest').append('<li><a href="#" data-identity="' + shoreRunnerRequest.id + '">' + "REQUEST ID:" + shoreRunnerRequest.id + '</a></li>');
    });
    $('#description').val('New Request: ' + currentRequest.id + '  created');
    $('#description').show();
}

function renderDetails(shoeRunnerRequest) {
    $('#id1').val(shoeRunnerRequest.id);
    $('#upc').val(shoeRunnerRequest.upc);
    $('#itemDescription').val(shoeRunnerRequest.itemDescription);
    $('#itemColor').val(shoeRunnerRequest.itemColor);
    $('#requestStatus').val(shoeRunnerRequest.requestStatus);
    $('#pic').attr('src', 'pics/' + shoeRunnerRequest.itemImageUrl);
}
