package eu.onepay.payment;

public interface PaymentCredential {
    
    public static final String CUSTOM_METHODS_KEY = "customPaymentMethods";

    public Long getPaymentId();

}
