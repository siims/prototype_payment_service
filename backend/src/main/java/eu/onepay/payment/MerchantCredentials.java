package eu.onepay.payment;

import eu.onepay.db.data.Merchant;
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

    public static MerchantCredentials makeMerchantCredentials(Merchant merchant) {
        MerchantCredentials merchantCredentials = new MerchantCredentials();
        merchantCredentials.setMerchantId(merchant.getId());
        merchantCredentials.setName(merchant.getName());
        return merchantCredentials;
    }
}
