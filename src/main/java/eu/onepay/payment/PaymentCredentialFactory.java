package eu.onepay.payment;

import java.net.MalformedURLException;

import eu.onepay.db.data.PaymentMethod;

public class PaymentCredentialFactory {

    public static PaymentCredential makePaymentCredential(PaymentMethod paymentMethod) {
        // TODO: Add other payment credential types
        
        return null; // FIXME: new VKBankPayCredential();
    }
    
    private String veryfyUrl(String url) throws MalformedURLException {
        //TODO: ka HTTPS
        boolean startsWith = url.startsWith("http://");
        if (startsWith) {
            return url;
        } else {
            throw new MalformedURLException("URL:" + url + " but should start with: http://");
        }
    }
}
