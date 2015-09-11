package eu.onepay.payment;


public abstract class UniqueFinancialService {
    public static final String CONTEXT_KEY = "uniqueFinancialService";
    private Long id;
    protected boolean valid;
    private OurTransaction transaction;

    public UniqueFinancialService ( Long id ){
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

    public void setTransaction(OurTransaction trans) {
        this.transaction = trans;
    }
    /**
     * Transaction ID form database.
     * @return
     */
    public OurTransaction getTransaction() {
        return transaction;
    }

}
