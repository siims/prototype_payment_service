package eu.onepay.payment;

import eu.onepay.db.data.PaymentMethod;

public class PaymentCredentialFactory {

    public static PaymentCredential makePaymentCredential(PaymentMethod paymentMethod) {
        // TODO: Add other payment credential types
        return null; // FIXME: new VKBankPayCredential();
    }
}
