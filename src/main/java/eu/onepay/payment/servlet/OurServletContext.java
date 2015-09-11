package eu.onepay.payment.servlet;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import eu.onepay.db.resource.data.MerchantResource;
import eu.onepay.db.resource.data.PaymentMethodResource;
import eu.onepay.payment.MerchantCredentials;
import eu.onepay.payment.UniqueFinancialService;
import eu.onepay.payment.PaymentCredential;
import eu.onepay.payment.PaymentCredentialFactory;
import eu.onepay.payment.bank.ee.BankEE;
import eu.onepay.payment.bank.ee.VKBankPayCredential;

@Slf4j
public class OurServletContext implements ServletContextListener {

    @Autowired private MerchantResource merchantResource;

    @Autowired private PaymentMethodResource paymentMethodResource;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext())
                .getAutowireCapableBeanFactory().autowireBean(this);
        ServletContext serCtx = event.getServletContext();

        setUniqueFinancialServices(serCtx);

        setMerchantCredentials(serCtx);

        setPaymentCredential(serCtx);

        String keyLocation = "/WEB-INF/classes/truststore.ks";
        BankEE.keyLocation = serCtx.getRealPath(keyLocation).toString();
    }

    private void setMerchantCredentials(ServletContext serCtx) {

        Map<Long, MerchantCredentials> merchantCrede = merchantResource
                .listActive()
                .stream()
                .collect(
                        Collectors.toMap(merchant -> merchant.getId(),
                                merchant -> MerchantCredentials.makeMerchantCredentials(merchant)));

        serCtx.setAttribute(MerchantCredentials.CONTEXT_KEY, merchantCrede);
    }

    /**
     * For every Merchant add their custom payment method credentials to the
     * their map.
     * 
     * Datastructure that is put to context: Map<merchantID <paymentId, PaymentCredential>>
     * 
     * @param serCtx
     */
    @SuppressWarnings("unchecked")
    private void setPaymentCredential(ServletContext serCtx) {

        Map<Long, MerchantCredentials> merchantCredentials = (Map<Long, MerchantCredentials>) serCtx
                .getAttribute(MerchantCredentials.CONTEXT_KEY);

        Map<Long, Map<Long, PaymentCredential>> merchantPaymentCredentials = new HashMap<Long, Map<Long, PaymentCredential>>();
        for (Entry<Long, MerchantCredentials> entry : merchantCredentials.entrySet()) {
            Long merchantId = entry.getKey();
            MerchantCredentials merchantCredential = entry.getValue();
            merchantPaymentCredentials.put(merchantId, PaymentCredentialFactory
                    .makePaymentCredentialMap(paymentMethodResource.getMerchantPaymentMethods(merchantId)));
        }
        serCtx.setAttribute(PaymentCredential.CREDE_KEY, merchantPaymentCredentials);
    }

    /*
     * 
     */
    private void setUniqueFinancialServices(ServletContext serCtx) {
        @SuppressWarnings("rawtypes")
        Map<Long, Class> uniqueFinancialServices = new HashMap<Long, Class>();
        // TODO: connect with some sort of database - document based perhaps
        // Get payment ID and connect it with required class
        try {
            long paymentId = 23L;
            @SuppressWarnings("unchecked")
            Class<UniqueFinancialService> act = (Class<UniqueFinancialService>) Class.forName("eu.onepay.payment.bank.ee.VKBankMethod");

            uniqueFinancialServices.put(paymentId, act);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        serCtx.setAttribute(UniqueFinancialService.CONTEXT_KEY, uniqueFinancialServices);

    }
}
