package eu.onepay.payment;


public abstract class PayMethod {
    public static final String CONTEXT_KEY = "paymentMethods";
    private Long id;
    protected boolean valid;

    public PayMethod ( Long id ){
        this.id = id;
    }

    /**
     * Makse sure that all payment details are added as should.
     * TODO: exception handling think through. One way is to throw exception
     * when something that can't be initialized can't be
     * 
     * @param payCrede
     * @param orderCrede
     * @param merchCrede
     */
    public abstract void initAndVerify(PaymentCredential payCrede, OrderCredentials orderCrede,
            MerchantCredentials merchCrede);

    public long getId(){
        return id;
    }

    public boolean notValid() {
        return !valid;
    }

    public boolean isValid() {
        return valid;
    }

}
