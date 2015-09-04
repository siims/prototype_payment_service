package eu.onepay.payment;

import eu.onepay.payment.html.Form;

public interface PayMethod {
    public static final String CONTEXT_KEY = "paymentMethods";
    
    /**
     * Makse sure that all payment details are added as should.
     * TODO: exception handling think through. One way is to throw exception
     * when something that can't be initialized can't be
     * 
     * @param payCrede
     * @param orderCrede
     * @param merchCrede
     */
    public void init(PaymentCredential payCrede, OrderCredentials orderCrede, MerchantCredentials merchCrede);

    /**
     * 
     * @return paymentMethod in the form of Form Class.
     */
    public Form asForm();

    public long getId();

}
