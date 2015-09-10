jQuery(document).ready(function($) {

	$.ajax({
		url: "http://localhost:8080/onepay_bankpayment/pay",
		dataType: 'jsonp',
		jsonpCallback: 'jsonpCallback',
		crossDomain: true,
		data: "json_data=" + JSON.stringify(data),
		success: successCallback,
		error: errorCallback
	});

});

function inputLenghtToContentSize() {
	$("input[NAME^='VK_']").each(function() {
		$(this).attr("size", $(this).val().length);
	});
}


var data = {
	"merchant_id": "1234",
	"pay_option": "23",
	"order_id": "12345",
	"amount": "123",
	"reference_no": "12344",
	"explanation": "text text text"

};
var form;
var errorCallback = function(response, second, third, forth) {
	console.log("error");
	console.log(response);
	console.log(second);
	console.log(third);
	console.log(forth);
}
var successCallback = function(response) {
	console.log("success");
	$("#response").append($(response));
	form = jQuery.parseHTML(response);
	inputLenghtToContentSize();
};
var jsonpCallback = function() {
	// body...
}

function submitForm() {
	$(form).appendTo("body").submit();
	console.log(form);
}