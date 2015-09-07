package eu.onepay.payment;

import lombok.Data;

/**
 * Things that usually go on order for e-shops are described here.
 * E.g amount, description, order id
 * 
 * @author mihkel
 *
 */
@Data
public class OrderCredentials {

    private String id = "";
    private Double amount = 0.0;
    private String description = "";
    private Long referenceNo = 0L;
    private Long transactionId = 0L;

}
