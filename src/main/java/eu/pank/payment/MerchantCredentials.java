package eu.pank.payment;

import java.util.HashMap;
import java.util.Map;
public class MerchantCredentials {

    private Map<String, PayMethod> privatePayMethods = new HashMap<>();
    private Long merchantId = 0L;
    
    
    public boolean hasCustomSettingOnPayMethod(String paymentId) {
        return privatePayMethods.containsKey(paymentId);
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

}
