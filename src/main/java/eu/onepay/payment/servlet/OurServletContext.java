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
import eu.onepay.payment.bank.ee.VKBankMethod;
import eu.onepay.payment.bank.ee.VKBankPayCredentials;

@WebListener
public class OurServletContext implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext serCtx = event.getServletContext();

        setDefaultPaymentMethods(serCtx);
        setDefaultPaymentCedentials(serCtx);
        
        setMerchantCredentials(serCtx);
        
        setCustomPaymentCredential(serCtx);

        String keyLocation = "/WEB-INF/classes/truststore.ks";
        BankEE.keyLocation = serCtx.getRealPath(keyLocation).toString(); 
    }

    private void setMerchantCredentials(ServletContext serCtx) {
        // TODO: connect with some sort of database - document based perhaps
        Map<Long, MerchantCredentials> merchantCrede = new HashMap<>();

        MerchantCredentials merchCrede = new MerchantCredentials();
        merchCrede.setMerchantId(1234L);
        merchantCrede.put(merchCrede.getMerchantId(), merchCrede);

        serCtx.setAttribute(MerchantCredentials.CONTEXT_KEY, merchantCrede);

    }

    private void setDefaultPaymentCedentials(ServletContext serCtx) {
        Map<Long, PaymentCredential> payCredential = new HashMap<Long, PaymentCredential>();
        PaymentCredential payCrede;
        // TODO: connect with some sort of database - document based perhaps
        // SOLR for example.
        // Siin pannakse kokku siis PaymentCredential'id mida meie defaultis anname.
        // Võimalik, et tuleks ka ülejäänud parameetrid lisada siin, payment construktorisse.
        // Trikk on siin, erinevad payment meetodid omavad erinevaid paymentCredentialeid.
        // Kuidagi neid tuleks panna eristama teineteisest.
        // Näiteks eesti pangad kasutavad kõik VKBankPayCredentials'it. Teised makseviisid ka muid.
        
        
        // sendersID = panga poolt meie ettevõttele antud ID
        String sendersId = "uid100010";
        String returnUrl = "http://localhost:8080/pankpayment/";
        String cancelUrl = "http://localhost:8080/pankpayment/";
        // privateKeyAlias keyfailis olevat võtme alias
        String privateKeyAlias = "1";

        payCrede = new VKBankPayCredentials(23L, sendersId, returnUrl, cancelUrl, privateKeyAlias );
        payCredential.put(payCrede.getPaymentId(), payCrede);

        serCtx.setAttribute(PaymentCredential.CREDE_KEY, payCredential);
    }

    /**
     * For every Merchant add their custom payment method credentials to the
     * their map.
     * 
     * @param serCtx
     */
    private void setCustomPaymentCredential(ServletContext serCtx) {
        
        // <merchantID <paymentId, PaymentCredential>>
        Map<String, Map<Long, PaymentCredential>> merchantPayCredentials = new HashMap<String, Map<Long, PaymentCredential>>();
        // TODO: connect with some sort of database - document based perhaps
        // get all merchant ID's in a list. And connect with their paymentCredentials.
        List<String> merchants = new ArrayList<>();
        merchants.add("Apollo");
        // TODO: LookUp all merchants who have custom paymentCredentials.
        for (String merchant : merchants) {
            Map<Long, PaymentCredential> payCredentials = getPayCredentials(merchant);
            merchantPayCredentials.put(merchant, payCredentials);
        }
        serCtx.setAttribute(PaymentCredential.CUSTOM_CREDE_KEY, merchantPayCredentials);
    }

    /**
     * Get all Credentials for this merchant
     * 
     * @param merchant
     * @return
     */
    private Map<Long, PaymentCredential> getPayCredentials(String merchant) {

        // TODO: connect with some sort of database - document based perhaps
        // SOLR for example. 
        Map<Long, PaymentCredential> payCredential = new HashMap<Long, PaymentCredential>();
        String sendersId = "uid100010";
        String returnUrl = "http://localhost:8080/pankpayment/";
        String cancelUrl = returnUrl;
        String privateKeyAlias = "1";

        VKBankPayCredentials payCrede = new VKBankPayCredentials(23L, sendersId, returnUrl, cancelUrl, privateKeyAlias);
        payCredential.put(payCrede.getPaymentId(), payCrede);

        payCrede = new VKBankPayCredentials(24L, sendersId, returnUrl, cancelUrl, privateKeyAlias);
        payCredential.put(payCrede.getPaymentId(), payCrede);

        return payCredential;
    }

    private void setDefaultPaymentMethods(ServletContext serCtx) {
        @SuppressWarnings("rawtypes")
        Map<Long, Class> payMethods = new HashMap<Long, Class>();
        // TODO: connect with some sort of database - document based perhaps
        // Get
        long paymentId = 23L;
        payMethods.put(paymentId, VKBankMethod.class);
        serCtx.setAttribute(PayMethod.CONTEXT_KEY, payMethods);

    }

}
