package eu.pank.payment.servlet;

import eu.pank.payment.MerchantCredentials;
import eu.pank.payment.OrderCredentials;
import eu.pank.payment.PayMethod;
import eu.pank.payment.PaymentCredential;
import eu.pank.payment.html.Form;

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
