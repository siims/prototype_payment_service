package eu.onepay.payment;

/**
 * Things that usually go on order for e-shops are described here.
 * E.g amount, description, order id
 * 
 * @author mihkel
 *
 */
public class OrderCredentials {

    private String id = "";
    //TODO amount should be double i guess.
    private Double amount = 0.0;
    private String description = "";
    private Long referenceNo = 0L;
    private Long transactionId = 0L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(Long referenceNo) {
        this.referenceNo = referenceNo;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

}
