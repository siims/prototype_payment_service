package eu.onepay.payment.bank.ee;

import java.net.MalformedURLException;

import eu.onepay.payment.PaymentCredential;

public class VKBankPayCredentials implements PaymentCredential {

    private Long paymentId;
    private String imageUrl = "http://www.seb.ee/sites/default/files/web/images/logod/seb_88x31.gif";
    private String bankName;
    private transient String sendersId;
    private transient String returnUrl;
    private transient String cancelUrl;
    private transient String version = "008";
    private transient String service = "1012";
    private transient String currency = "EUR";
    private transient String encoding = "UTF-8";
    private transient String privateKeyAlias;
    private transient String defaultReturnUrl;
    private transient String defaultCancelUrl;
    private transient String publicKey;

    //TODO: Image and bankName as constructor arg
    public VKBankPayCredentials ( Long paymentId, String sendersId, String returnUrl, String cancelUrl,
            String privateKeyAlias, String defaultReturnUrl, String defaultCancelUrl, String publicKey )
            throws MalformedURLException{
        this.paymentId = paymentId;
        this.sendersId = sendersId;
        this.returnUrl = veryfyUrl(returnUrl);
        this.cancelUrl = veryfyUrl(cancelUrl);
        this.privateKeyAlias = privateKeyAlias;
        this.defaultReturnUrl = defaultReturnUrl;
        this.defaultCancelUrl = defaultCancelUrl;
        this.publicKey = publicKey;

    }

    private String veryfyUrl(String url) throws MalformedURLException {
        boolean startsWith = url.startsWith("http://");
        if (startsWith) {
            return url;
        } else {
            throw new MalformedURLException("URL:" + url + " but should start with: http://");
        }
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

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String getName() {
        return bankName;
    }

}
