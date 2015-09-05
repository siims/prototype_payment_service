package eu.onepay.payment.servlet;

import eu.onepay.payment.MerchantCredentials;
import eu.onepay.payment.OrderCredentials;
import eu.onepay.payment.PayMethod;
import eu.onepay.payment.PaymentCredential;
import eu.onepay.payment.html.Form;

public class NullObject {
    public static PayMethod payMethod() {
        return new PayMethod(0L) {

            @Override
            public void initAndVerify(PaymentCredential payCrede, OrderCredentials orderCrede, MerchantCredentials merchCrede) {
            }

            @Override
            public long getId() {
                return 0;
            }

        };
    }
}
