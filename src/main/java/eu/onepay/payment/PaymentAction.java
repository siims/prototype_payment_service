package eu.onepay.payment;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletContext;

import eu.onepay.payment.servlet.NullObject;
import eu.onepay.payment.servlet.PaymentRequest;

public class PaymentAction {

    private ServletContext servCtx;
    private OrderCredentials orderCrede;
    private MerchantCredentials merchCrede;
    private PaymentCredential payCrede;

    public static UniqueFinancialService makeTransaction(PaymentRequest payRequest, ServletContext servCtx) {
        PaymentAction paymentAction = new PaymentAction(payRequest, servCtx);
        UniqueFinancialService service = paymentAction.getPayMethod();
        // TODO: DATABASE: Save the payMethod to database
        OurTransaction transaction = new OurTransaction();// =
                                                          // Database.saveAsTransaction(payMethod);
        service.setTransaction(transaction);

        return service;
    }

    private PaymentAction (PaymentRequest payRequest, ServletContext servCtx) {

        this.servCtx = servCtx;

        orderCrede = getOrderCredentials(payRequest);
        merchCrede = getMerchantCredentials(payRequest.merchantId);
        payCrede = getPaymentCredential(merchCrede.getMerchantId(), payRequest.paymentId);

    }

    private UniqueFinancialService getPayMethod() {

        UniqueFinancialService service = getUniqueFinancialService(payCrede.getUniqueFinancialServiceId());
        service.initAndVerify(payCrede, orderCrede, merchCrede);
        if (service.notValid()) {
            service = NullObject.uniqueFinancialService();
        }

        return service;
    }

    private MerchantCredentials getMerchantCredentials(Long merchantId) {
        @SuppressWarnings("unchecked")
        Map<Long, MerchantCredentials> merchantsCrede = (Map<Long, MerchantCredentials>) servCtx
                .getAttribute(MerchantCredentials.CONTEXT_KEY);
        MerchantCredentials merchantCrede = merchantsCrede.get(merchantId);
        return merchantCrede;
    }

    private OrderCredentials getOrderCredentials(PaymentRequest payRequest) {
        OrderCredentials orderCrede = new OrderCredentials();
        orderCrede.setAmount(payRequest.amount);
        orderCrede.setDescription(payRequest.explanation);
        orderCrede.setId(payRequest.orderId);
        orderCrede.setReferenceNo(payRequest.referenceNo);

        return orderCrede;
    }

    @SuppressWarnings("rawtypes")
    private UniqueFinancialService getUniqueFinancialService(Long paymentId) {
        @SuppressWarnings("unchecked")
        Map<String, Class> payMethods = (Map<String, Class>) servCtx.getAttribute(UniqueFinancialService.CONTEXT_KEY);
        UniqueFinancialService payMethod = NullObject.uniqueFinancialService();
        Class payMethodClass = payMethods.get(paymentId);

        try {
            @SuppressWarnings("unchecked")
            Constructor<? extends UniqueFinancialService> constructor = payMethodClass.getConstructor(Long.class);
            payMethod = constructor.newInstance(paymentId);
            return payMethod;
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return payMethod;
    }

    private PaymentCredential getPaymentCredential(Long merchantId, Long paymentId) {

        try {
            return OurContext.getPaymentCredential(merchantId, paymentId, servCtx);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }

    }

}
