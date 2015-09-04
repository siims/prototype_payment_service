package eu.onepay.payment.servlet;

import eu.onepay.payment.MerchantCredentials;
import eu.onepay.payment.OrderCredentials;
import eu.onepay.payment.PayMethod;
import eu.onepay.payment.PaymentCredential;
import eu.onepay.payment.html.Form;

public class NullObject {
    public static PayMethod payMethod() {
        return new PayMethod() {

            @Override
            public void init(PaymentCredential payCrede, OrderCredentials orderCrede, MerchantCredentials merchCrede) {
            }

            @Override
            public long getId() {
                return 0;
            }

            @Override
            public Form asForm() {
                return null;
            }
        };
    }
}
