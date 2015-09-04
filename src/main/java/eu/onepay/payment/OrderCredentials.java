package eu.onepay.payment;

/**
 * Things that usually go on order for e-shops are described here.
 * E.g amount, description, order id
 * 
 * @author mihkel
 *
 */
public class OrderCredentials {

    private long id = 0L;
    private long ammount = 0L;
    private String description = "";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAmount() {
        return ammount;
    }

    public void setAmmount(long ammount) {
        this.ammount = ammount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
