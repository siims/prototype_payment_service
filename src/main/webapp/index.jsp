<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<script>

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

<button onclick="submitForm()"> send the form to bank</button>


</body>
</html>
