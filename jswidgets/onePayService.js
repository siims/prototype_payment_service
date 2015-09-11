jQuery(document).ready(function($) {
	onepay.getPayMethods();

});
var onepay = {
		data: {
			merchant_id: "",
			pay_option: "",
			order_id: "",
			amount: "",
			reference_no: "",
			explanation: ""
		},
		dataParams: ["merchant_id", "pay_option", "order_id", "amount", "reference_no", "explanation"],
		add: function(fieldName, fieldvalue) {
			this.data[fieldName] = fieldvalue;
		},
		submitForm: function() {
			var form = $(form);
			form.css("display", "none");
			form.css("visibility", "hidden");
			form.appendTo("body").submit();
		},

		doCall: function() {
			$.ajax({
					url: "http://localhost:8080/onepay_bankpayment/pay",
					dataType: 'jsonp',
					jsonpCallback: 'jsonpCallback',
					crossDomain: true,
					data: "json_data=" + JSON.stringify(this.data, this.dataParams
					),
				success: successCallback,
				error: errorCallback
			});
	},
	getPayMethods: function() {
		$.ajax({
			url: "http://localhost:8080/onepay_bankpayment/get/paymethods/merchant/1234",
			type: "POST",
			dataType: 'jsonp',
			jsonpCallback: 'jsonpCallback',
			data: "",
			crossDomain: true,
			success: paymethodsSuccess,
			error: errorCallback
		});
	}

}
var paymethodsSuccess = function(response) {

	$("body").append(JSON.stringify(response));
	showMethodsInList(response);

}

function showMethodsInList(resp) {
	var ul = $("#paymethods");
	for (var i = 0; i < $(resp).length; i++) {
		var li = getListRow(resp[i]);
		ul.append(li);
	};

}

function getListRow(obj) {
	var li = $("<li>");
	var img = $("<img src='" + obj.imageUrl + "'>");
	li.append(img);
	li.append(obj.paymentId);
	li.click(function () {
		makePayment(obj.paymentId);
	});
	return li;
}

function makePayment(paymentId) {
	onepay.data.pay_option = paymentId;
	onepay.doCall();
}

var jsonpCallback = function() {};
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
}

function inputLenghtToContentSize() {
	$("input[NAME^='VK_']").each(function() {
		$(this).attr("size", $(this).val().length);
	});
}