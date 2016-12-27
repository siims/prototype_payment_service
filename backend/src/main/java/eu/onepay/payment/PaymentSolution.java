package eu.onepay.payment;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;
import eu.onepay.payment.html.FormFactory;

public abstract class PaymentSolution {
    public static final String CONTEXT_KEY = "uniqueFinancialService";
    private Long id;
    protected boolean valid;
    private Long paymentTimeout;
    @Getter @Setter private Long transactionId;

    public PaymentSolution ( Long id ){
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

    public PaymentTransaction execute(HttpServletResponse response) throws IOException {
        // TODO: DATABASE: Save the payMethod to database
        PaymentTransaction transaction = new PaymentTransaction(this);// =
        // Database.saveAsTransaction(payMethod);
        this.setTransactionId(transaction.getId()); // FIXME: real value from database

        String retString = "jsonpCallback('"+FormFactory.asForm(this).toString()+ "')";
        response.getWriter().write(retString);
        return transaction;
    }

    /**
     * Maximum time for transaction to be confirmed.
     * 
     * @return
     */
    public Long getTimeout() {
        return paymentTimeout; 
    }

}
