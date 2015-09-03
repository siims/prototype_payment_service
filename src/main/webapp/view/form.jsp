< html >
	< head >
	< meta http - equiv = "Content-Type"
content = "text/html; charset=utf-8" >
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	< /head> < body >
	< h2 > static htmls < /h2> < div > <%= request.toString() %> < /div>


< script >

	var response = {
		merchant_data: {
			merchant_id: ‘9 das8dASds’;
		}
		payment_data: {
			currency: ‘EUR’,
			pay_option: ’seb’,
			success: ’ /some/url’,
			fail: ’ /some/url’,
			cancel: ’ /some/url’
		}
		order_data:{
			orderId: ‘12345’,
			amount: ‘123’,
			
			reference_no: ’12345’,
			explanation: ‘text text text’,
		}
	}

	$("#response").innerHtml("some");

< /script>

<div id="response"></div>






< /body> < /html>