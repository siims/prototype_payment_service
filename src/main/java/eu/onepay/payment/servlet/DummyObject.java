package eu.onepay.payment.servlet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import eu.onepay.payment.MerchantCredentials;
import eu.onepay.payment.OrderCredentials;
import eu.onepay.payment.PayMethod;
import eu.onepay.payment.PaymentCredential;
import eu.onepay.payment.html.Form;
import eu.onepay.payment.html.InputElement;

public class DummyObject {

    public static PayMethod payMethod() {

        return new PayMethod() {

            private PaymentCredential payCrede;
            private OrderCredentials orderCrede;
            private MerchantCredentials merchCrede;

            @Override
            public void initAndVerify(PaymentCredential payCrede, OrderCredentials orderCrede, MerchantCredentials merchCrede) {
                this.payCrede = payCrede;
                // TODO Auto-generated method stub
                this.orderCrede = orderCrede;
                this.merchCrede = merchCrede;

            }

            @Override
            public long getId() {
                return 23L;
            }

            @Override
            public Form asForm() {

                Form form = new Form();
                form.setMethod("POST");
                form.setAction("http://localhost:8081/banklink/seb-common");
                InputElement input;

                Map<String, String> inputNameValue = new HashMap<>();
                inputNameValue.put("VK_AMOUNT", Double.toString(orderCrede.getAmount()));
                // kliendile tuleb VK_SND_ID eraldi võtta kui tal on custom lahenduse
                inputNameValue.put("VK_SND_ID", "uid100010");
                inputNameValue.put("VK_SERVICE", "1012");
                inputNameValue.put("VK_VERSION", "008");
                inputNameValue.put("VK_STAMP", "12345");
                inputNameValue.put("VK_DATETIME", getCurrentDate());
                inputNameValue.put("VK_CURR", "EUR");
                inputNameValue.put("VK_REF", "");
                inputNameValue.put("VK_MSG", orderCrede.getDescription());
                inputNameValue.put("VK_CHARSET", "UTF-8");
                inputNameValue.put("VK_MAC", ""); // TODO: MAC here
                inputNameValue.put("VK_RETURN", "http://localhost:8080/pankpayment/");
                inputNameValue.put("VK_CANCEL", "http://localhost:8080/pankpayment/");

                inputNameValue.forEach((name, value) -> form.addInputElement(name, value));

                return form;

            }

            public String getCurrentDate() {
                Date date = new Date();
                SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                String format = isoFormat.format(date);
                return format;
            }
        };
    }

}