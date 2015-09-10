package eu.onepay.payment.bank.ee;

import lombok.Getter;
import eu.onepay.payment.PaymentCredential;

public class VKBankPayCredentials implements PaymentCredential {

    @Getter private Long paymentId;
    // Id with the bank
    @Getter private String sendersId;
    @Getter private String returnUrl;
    @Getter private String cancelUrl;
    @Getter private String version = "008";
    @Getter private String service = "1012";
    @Getter private String currency = "EUR";
    @Getter private String encoding = "UTF-8";
    @Getter private String privateKeyAlias;
    @Getter private String defaultReturnUrl;
    @Getter private String defaultCancelUrl;
    @Getter private String publicKey;
    
    public VKBankPayCredentials ( Long paymentId, String sendersId, String returnUrl, String cancelUrl,
            String privateKeyAlias, String defaultReturnUrl, String defaultCancelUrl, String publicKey ){
        this.paymentId = paymentId;
        this.sendersId = sendersId;
        this.returnUrl = returnUrl;
        this.cancelUrl = cancelUrl;
        this.privateKeyAlias = privateKeyAlias;
        this.defaultReturnUrl = defaultReturnUrl;
        this.defaultCancelUrl = defaultCancelUrl;
        this.publicKey = publicKey;

    }
}
