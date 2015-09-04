package eu.onepay.payment;

public class PaymentCredential {
    
    public static final String CUSTOM_METHODS_KEY = "customPaymentMethods";

    private Long paymentId = 0L;

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

}
