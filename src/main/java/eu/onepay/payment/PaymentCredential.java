package eu.onepay.payment;

public interface PaymentCredential {
    
    public static final String CUSTOM_CREDE_KEY = "customPaymentCredentials";
    public static final String CREDE_KEY = "PaymentCredentials";

    public Long getPaymentId();

}
