package eu.onepay.payment;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import eu.onepay.payment.servlet.NullObject;
import eu.onepay.payment.servlet.PaymentRequest;

public class PaymentAction {

    private ServletContext servCtx;
    private OrderCredentials orderCrede;
    private MerchantCredentials merchCrede;
    private PaymentCredential payCrede;

    public static PaymentTransaction makeTransaction(PaymentRequest payRequest, ServletContext servCtx,
            HttpServletResponse response) throws IOException {
        PaymentAction paymentAction = new PaymentAction(payRequest, servCtx);
        PaymentSolution solution = paymentAction.getPaymentSolution();

        return solution.execute(response);
    }

    private PaymentAction ( PaymentRequest payRequest, ServletContext servCtx ){

        this.servCtx = servCtx;

        orderCrede = getOrderCredentials(payRequest);
        merchCrede = getMerchantCredentials(payRequest.merchantId);
        payCrede = getPaymentCredential(merchCrede.getMerchantId(), payRequest.paymentId);

    }

    private PaymentSolution getPaymentSolution() {

        PaymentSolution service = getUniqueFinancialService(payCrede.getUniqueFinancialServiceId());
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
    private PaymentSolution getUniqueFinancialService(Long paymentId) {
        @SuppressWarnings("unchecked")
        Map<String, Class> payMethods = (Map<String, Class>) servCtx.getAttribute(PaymentSolution.CONTEXT_KEY);
        PaymentSolution payMethod = NullObject.uniqueFinancialService();
        Class payMethodClass = payMethods.get(paymentId);

        try {
            @SuppressWarnings("unchecked")
            Constructor<? extends PaymentSolution> constructor = payMethodClass.getConstructor(Long.class);
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
