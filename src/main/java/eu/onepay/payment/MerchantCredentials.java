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
    public static final String URL_KEY = "merchant";
    private Long merchantId = 0L;
    private String name;
    
}
