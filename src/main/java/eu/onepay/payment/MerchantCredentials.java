package eu.onepay.payment;

import lombok.Data;

/**
 * This class will hold information about the merchant.
 * @author mihkel
 *
 */

@Data
public class MerchantCredentials {

    public static final String CONTEXT_KEY = "merchantCredentials";
    private Long merchantId = 0L;
    private String name;
    
}
