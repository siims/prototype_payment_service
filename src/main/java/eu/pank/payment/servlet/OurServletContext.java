package eu.pank.payment.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import eu.pank.payment.PayMethod;
import eu.pank.payment.PaymentCredential;
@WebListener
public class OurServletContext implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext serCtx = event.getServletContext();

        setDefaultPaymentMethods(serCtx);

        setCustomPaymentCredential(serCtx);
    }

    /**
     * For every Merchant add their custom payment method credentials to the
     * their map.
     * 
     * @param serCtx
     */
    @SuppressWarnings("serial")
    private void setCustomPaymentCredential(ServletContext serCtx) {

        // <merchantID <paymentId, PaymentCredential>>
        Map<String, Map<Long, PaymentCredential>> merchantPayCredentials = new HashMap<String, Map<Long, PaymentCredential>>();

        List<String> merchants = new ArrayList<>();
        merchants.add("Apollo");
        // TODO: LookUp all merchants who have custom paymentCredentials.
        for (String merchant : merchants) {
            Map<Long, PaymentCredential> payCredentials = getPayCredentials(merchant);
            merchantPayCredentials.put(merchant, payCredentials);
        }

    }

    /**
     * Get all Credentials for this merchant
     * 
     * @param merchant
     * @return
     */
    private Map<Long, PaymentCredential> getPayCredentials(String merchant) {

        // TODO: connect with some sort of database - document based perhaps
        // SOLR for example
        Map<Long, PaymentCredential> payCredential = new HashMap<Long, PaymentCredential>();

        PaymentCredential payCrede = new PaymentCredential();
        payCrede.setPaymentId(23L);
        payCredential.put(payCrede.getPaymentId(), payCrede);
        payCrede = new PaymentCredential();
        payCrede.setPaymentId(24L);
        payCredential.put(payCrede.getPaymentId(), payCrede);

        return payCredential;
    }

    private void setDefaultPaymentMethods(ServletContext serCtx) {
        Map<Long, PayMethod> payMethods = new HashMap<Long, PayMethod>();
        
        PayMethod payMethod = DummyObject.payMethod();
        payMethods.put(payMethod.getId(), payMethod);
        
        serCtx.setAttribute(PayMethod.CONTEXT_KEY, payMethods);

    }

}
