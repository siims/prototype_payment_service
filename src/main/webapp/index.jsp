<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>


<body>
index.jsp
<h2>Payment request</h2>

<button onclick="submitForm()"> send the form to bank</button>
<br>
<br>
<div id="response"></div>
<button onclick="submitForm()"> send the form to bank</button>
<script>

function inputLenghtToContentSize(){
	$("input[NAME^='VK_']").each(function () {
	    $(this).attr("size", $(this).val().length );
	});
}

	var data = {
			merchant_id: "1234",
			pay_option: "23",
			order_id: "12345",
			amount: "123",
			reference_no: "12344",
			explanation: "text text text"
		
	}
	var form;
	$.ajax({
		url:"/pankpayment/pay",
		method:"POST",
		processData: false,
		data: JSON.stringify(data),
		contentType: "application/javascript; charset=utf-8",
		success:function(response){
			$("#response").append($(response));
			form = $(response);
			inputLenghtToContentSize();
		}
	})
	
	function submitForm() {
		
		$(form).submit();
		console.log(form);
	}

</script>

</body>
</html>
