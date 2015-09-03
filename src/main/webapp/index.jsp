<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<script>

	var response = {
		merchant_data: {
			merchant_id: "1234"
		},
		payment_data: {
			currency: "EUR",
			pay_option: "seb",
			success: "/some/url",
			fail: "/some/url",
			cancel: "/some/url"
		},
		order_data:{
			orderId: "€˜12345",
			amount: "123",
			
			reference_no: "12345",
			explanation: "text text text",
		}
	}
	var form;
	$.ajax({
		url:"/pankpayment/form",
		success:function(response){
			$("#response").text(response);
			form = $(response);
		}
	})
	
	function submitForm() {
		
		$(form).submit();
		console.log(form);
	}

</script>

<body>
index.jsp
<div id="response"></div>

<button onclick="submitForm()">



</form>
</body>
</html>
