package eu.onepay.payment;

import java.util.HashMap;
import java.util.Map;

/**
 * This class will hold information about the merchant.
 * @author mihkel
 *
 */
public class MerchantCredentials {

    public static final String CONTEXT_KEY = "merchantCredentials";
    private Map<String, PayMethod> privatePayMethods = new HashMap<>();
    private Long merchantId = 0L;
    private String name;
    
    
    public boolean hasCustomSettingOnPayMethod(String paymentId) {
        return privatePayMethods.containsKey(paymentId);
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
