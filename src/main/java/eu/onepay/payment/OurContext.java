package eu.onepay.payment;

import java.util.Map;

import javax.servlet.ServletContext;

public class OurContext {

    public static Map<Long, PaymentCredential> getPaymentCredentials(ServletContext servCtx, Long merchantId) {
        try {
            @SuppressWarnings("unchecked")
            Map<Long, Map<Long, PaymentCredential>> merchantPayMethods = (Map<Long, Map<Long, PaymentCredential>>) servCtx
                    .getAttribute(PaymentCredential.CREDE_KEY);
            return merchantPayMethods.get(merchantId);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static PaymentCredential getPaymentCredential(Long merchantId, Long paymentId, ServletContext servCtx) {

        return OurContext.getPaymentCredential(merchantId, paymentId, servCtx);

    }

}
