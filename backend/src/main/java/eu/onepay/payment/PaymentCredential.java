package eu.onepay.payment;


/**
 * Holds the logic to make the payment
 * @author mihkel
 *
 */
public interface PaymentCredential {

    public static final String CUSTOM_CREDE_KEY = "customPaymentCredentials";
    public static final String CREDE_KEY = "PaymentCredentials";

    public Long getUniqueFinancialServiceId();
    public String getImageUrl();
    public String getName();
}
