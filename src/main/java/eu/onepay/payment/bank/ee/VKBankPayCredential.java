package eu.onepay.payment.bank.ee;

import java.net.MalformedURLException;

import lombok.Getter;
import eu.onepay.db.data.PaymentMethod;
import eu.onepay.payment.PaymentCredential;

public class VKBankPayCredential implements PaymentCredential {

    @Getter private Long paymentId;
    @Getter private String imageUrl = "http://www.seb.ee/sites/default/files/web/images/logod/seb_88x31.gif";
    private String bankName;
    @Getter private transient String sendersId;
    @Getter private transient String returnUrl;
    @Getter private transient String cancelUrl;
    @Getter private transient String version = "008";
    @Getter private transient String service = "1012";
    @Getter private transient String currency = "EUR";
    @Getter private transient String encoding = "UTF-8";
    @Getter private transient String privateKeyAlias;
    @Getter private transient String defaultReturnUrl;
    @Getter private transient String defaultCancelUrl;
    @Getter private transient String publicKey;

    //TODO: Image and bankName as constructor arg
    public VKBankPayCredential ( Long paymentId, String sendersId, String returnUrl, String cancelUrl,
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
    public String getName() {
        return bankName;
    }
}
