package eu.onepay.payment.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import eu.onepay.payment.MerchantCredentials;
import eu.onepay.payment.PayMethod;
import eu.onepay.payment.PaymentCredential;
import eu.onepay.payment.bank.ee.BankEE;
import eu.onepay.payment.bank.ee.VKBankPayCredentials;

@WebListener
public class OurServletContext implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext serCtx = event.getServletContext();

        setPaymentMethods(serCtx);

        setMerchantCredentials(serCtx);

        setPaymentCredential(serCtx);

        String keyLocation = "/WEB-INF/classes/truststore.ks";
        BankEE.keyLocation = serCtx.getRealPath(keyLocation).toString();
    }

    private void setMerchantCredentials(ServletContext serCtx) {

        Map<Long, MerchantCredentials> merchantCrede = new HashMap<>();
        // TODO: connect with some sort of database - document based perhaps
        //
        MerchantCredentials merchCrede = new MerchantCredentials();
        merchCrede.setMerchantId(1234L);
        merchCrede.setName("Human Readable e.g. comapy name");
        merchantCrede.put(merchCrede.getMerchantId(), merchCrede);

        serCtx.setAttribute(MerchantCredentials.CONTEXT_KEY, merchantCrede);

    }

    /**
     * For every Merchant add their custom payment method credentials to the
     * their map.
     * 
     * @param serCtx
     */
    private void setPaymentCredential(ServletContext serCtx) {

        // <merchantID <paymentId, PaymentCredential>>
        Map<Long, Map<Long, PaymentCredential>> merchantPayCredentials = new HashMap<Long, Map<Long, PaymentCredential>>();
        // TODO: connect with some sort of database - document based perhaps
        // get all merchant ID's in a list. And connect with their
        // paymentCredentials.
        List<Long> merchants = new ArrayList<>();
        merchants.add(1234L);
        // TODO: LookUp all merchants who have custom paymentCredentials.
        for (Long merchant : merchants) {
            Map<Long, PaymentCredential> payCredentials = getPayCredentials(merchant);
            merchantPayCredentials.put(merchant, payCredentials);
        }
        serCtx.setAttribute(PaymentCredential.CREDE_KEY, merchantPayCredentials);
    }

    /**
     * Get all Credentials for this merchant
     * 
     * @param merchant
     * @return
     */
    private Map<Long, PaymentCredential> getPayCredentials(Long merchant) {

        Map<Long, PaymentCredential> payCredential = new HashMap<Long, PaymentCredential>();
        // TODO: connect with some sort of database - document based perhaps
        // SOLR for example.
        // like setDefaultPaymentCedentials but only get those
        // paymentCredentials
        // that are customConfigured for the merchant
        String sendersId = "uid100010";
        String returnUrl = "http://merchant.eu/callback/23";
        String cancelUrl = "http://merchant.eu/callback/23";
        String privateKeyAlias = "1";
        String publicKey = "-----BEGIN CERTIFICATE-----\nMIIDljCCAn4CCQCBmddvYTNKbTANBgkqhkiG9w0BAQUFADCBjDELMAkGA1UEBhMCRUUxETAPBgNVBAgTCEhhcmp1bWFhMRAwDgYDVQQHEwdUYWxsaW5uMQ0wCwYDVQQKEwRUZXN0MREwDwYDVQQLEwhiYW5rbGluazEXMBUGA1UEAxMObG9jYWxob3N0IDgwODExHTAbBgkqhkiG9w0BCQEWDnRlc3RAbG9jYWxob3N0MB4XDTE1MDgzMTExNDExNVoXDTM1MDgyNjExNDExNVowgYwxCzAJBgNVBAYTAkVFMREwDwYDVQQIEwhIYXJqdW1hYTEQMA4GA1UEBxMHVGFsbGlubjENMAsGA1UEChMEVGVzdDERMA8GA1UECxMIYmFua2xpbmsxFzAVBgNVBAMTDmxvY2FsaG9zdCA4MDgxMR0wGwYJKoZIhvcNAQkBFg50ZXN0QGxvY2FsaG9zdDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBANfS/2Z5c/1PBveT0RvM/TA162oumn8P7ecG0mfM2FI7i2uAtXlluj0fMLj8Slw6xO2y6SmTLkq31XoO+hDy10gp7OhKZLhyaSS1ueFO1ef/j7gGvbipXhxPS6HToyI06NbaSel206eP+NrbF+DvZ76RY42bkAScN5Y8AGGUdQUX1OPVOgbv7LO+3sUfQNBDMwxy/y+rCI0AHYR0bKtas7h18TS6WpDnj7TZCPTJIX1jUO5pWCvPsi4t3k/yiUAEALEpn//qZhdR+lgP5n0SNo63SWTkpgT+qnJ2vrycLJTJ7I+G/5BPKtMZoKHOohILrqBPP8Bhmrx9hF4UXVlZXYcCAwEAATANBgkqhkiG9w0BAQUFAAOCAQEAwD3Iis24RLRKO6CehOXB31Id4Pe+jz3PyFNJjC4mM7beYcpbDVRX4TMb5BfBiYgK80y417jT1k3Wsi4HwOSkU+bANiKjE0YJRLx8uNCV0XkFv2clz7saJq0OgCqs2+8hxlm6tiDoU3JowVeadCkU4gnzZlaLlviHLVd+zVdwLQM8VPm+0Y5H2stveq0Yj5t2ekvsSxoYiXD1Aga2plZBWUp4bgGVOr1uIkV8IoboVbe6A4D5QBakXAucioizighVv7zJGQ/Ir0HbP02G6AbcerVs4K3mMTkwgAlhFVjJtyAKd0QvA86XQovB9TaGWUcX/zwcOVu9G8/zjpVVxvTdfw==-----END CERTIFICATE-----";

        String defaultCancelUrl = "http://localhost:8080/pankpayment/";
        String defaultReturnUrl = "http://localhost:8080/pankpayment/callback/23/merchant/1234";

        VKBankPayCredentials payCrede = new VKBankPayCredentials(23L, sendersId, returnUrl, cancelUrl, privateKeyAlias,
                defaultReturnUrl, defaultCancelUrl, publicKey);
        payCredential.put(payCrede.getPaymentId(), payCrede);

        payCrede = new VKBankPayCredentials(24L, sendersId, returnUrl, cancelUrl, privateKeyAlias, defaultReturnUrl,
                defaultCancelUrl, publicKey);
        payCredential.put(payCrede.getPaymentId(), payCrede);

        return payCredential;
    }

    private void setPaymentMethods(ServletContext serCtx) {
        @SuppressWarnings("rawtypes")
        Map<Long, Class> payMethods = new HashMap<Long, Class>();
        // TODO: connect with some sort of database - document based perhaps
        // Get payment ID and connect it with required class
        try {
            long paymentId = 23L;
            @SuppressWarnings("unchecked")
            Class<PayMethod> act = (Class<PayMethod>) Class.forName("eu.onepay.payment.bank.ee.VKBankMethod");
            
            payMethods.put(paymentId, act);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        serCtx.setAttribute(PayMethod.CONTEXT_KEY, payMethods);

    }

}
