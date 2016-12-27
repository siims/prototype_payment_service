package eu.onepay.payment.servlet;

import eu.onepay.payment.MerchantCredentials;
import eu.onepay.payment.OrderCredentials;
import eu.onepay.payment.PaymentCredential;
import eu.onepay.payment.PaymentSolution;

public class NullObject {
    public static PaymentSolution uniqueFinancialService() {
        return new PaymentSolution(0L) {

            @Override
            public void initAndVerify(PaymentCredential payCrede, OrderCredentials orderCrede,
                    MerchantCredentials merchCrede) {
            }

            @Override
            public long getId() {
                return 0;
            }

        };
    }
}
