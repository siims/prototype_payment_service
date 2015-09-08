package eu.onepay.payment.bank.ee;

import eu.onepay.payment.PaymentCredential;

public class VKBankPayCredentials implements PaymentCredential {

    private Long paymentId;
    // Id with the bank
    private String sendersId;
    private String returnUrl;
    private String cancelUrl;
    private String version = "008";
    private String service = "1012";
    private String currency = "EUR";
    private String encoding = "UTF-8";
    private String privateKeyAlias;
    private String defaultReturnUrl;
    private String defaultCancelUrl;
    private String publicKey;
    
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

    @Override
    public Long getPaymentId() {
        return paymentId;
    }

    public String getSendersId() {
        return sendersId;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public String getVersion() {
        return version;
    }

    public String getService() {
        return service;
    }

    public String getCurrency() {
        return currency;
    }

    public String getEncoding() {
        return encoding;
    }

    public String getPrivetKeyAlias() {

        return privateKeyAlias;
    }

    public String getDefaultReturnUrl() {
        return defaultReturnUrl;
    }

    public String getDefaultCancelUrl() {
        return defaultCancelUrl;
    }

    public String getPublicKey() {
        return publicKey;
    }

}
