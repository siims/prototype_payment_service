package eu.onepay.payment;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * This class will hold information about the merchant.
 * @author mihkel
 *
 */

@Data
public class MerchantCredentials {

    public static final String CONTEXT_KEY = "merchantCredentials";
    private Map<String, PayMethod> privatePayMethods = new HashMap<>();
    private Long merchantId = 0L;
    private String name;
    
    
    public boolean hasCustomSettingOnPayMethod(String paymentId) {
        return privatePayMethods.containsKey(paymentId);
    }

}
