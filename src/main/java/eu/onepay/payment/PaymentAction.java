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

    public static PayMethod makeTransaction(PaymentRequest payRequest, ServletContext servCtx) {
        PaymentAction paymentAction = new PaymentAction(payRequest, servCtx);
        PayMethod payMethod = paymentAction.getPayMethod();
        
        return payMethod;
    }

    private PaymentAction ( PaymentRequest payRequest, ServletContext servCtx ){

        this.servCtx = servCtx;

         orderCrede = getOrderCredentials(payRequest);
         merchCrede = getMerchantCredentials(payRequest.merchantId);
         payCrede = getPayCrededentials(merchCrede.getMerchantId(), payRequest.paymentId);

        
    }

    private PayMethod getPayMethod() {

        PayMethod method = getDefaultPaymentMethod(payCrede.getPaymentId());
        method.initAndVerify(payCrede, orderCrede, merchCrede);
        if(method.notValid()){
            method = NullObject.payMethod();
        }
        
        return method;
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

    private PaymentCredential getPayCrededentials(Long merchantId, Long paymentId) {
        PaymentCredential payCrede = getCustomPaymentCredentials(merchantId, paymentId);
        if (payCrede == null) {
            payCrede = getDefaultPaymentCredential(paymentId);
        }

        return payCrede;
    }

    @SuppressWarnings("unchecked")
    private PaymentCredential getDefaultPaymentCredential(Long paymentId) {
        Map<Long, PaymentCredential> payMethods = (Map<Long, PaymentCredential>) servCtx
                .getAttribute(PaymentCredential.CREDE_KEY);
        PaymentCredential payCredential = payMethods.get(paymentId);
        return payCredential;
    }

    @SuppressWarnings("rawtypes")
    private PayMethod getDefaultPaymentMethod(Long paymentId) {
        @SuppressWarnings("unchecked")
        Map<String, Class> payMethods = (Map<String, Class>) servCtx.getAttribute(PayMethod.CONTEXT_KEY);
        PayMethod payMethod = NullObject.payMethod();
        Class payMethodClass = payMethods.get(paymentId);

        try {
            @SuppressWarnings("unchecked")
            Constructor<? extends PayMethod> constructor = payMethodClass.getConstructor(Long.class);
            payMethod = constructor.newInstance(paymentId);
            return payMethod;
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return payMethod;
    }

    private PaymentCredential getCustomPaymentCredentials(Long merchantId, Long paymentId) {

        try {
            @SuppressWarnings("unchecked")
            Map<Long, Map<Long, PaymentCredential>> merchantPayMethods = (Map<Long, Map<Long, PaymentCredential>>) servCtx
                    .getAttribute(PaymentCredential.CUSTOM_CREDE_KEY);
            Map<Long, PaymentCredential> payCredentials = merchantPayMethods.get(merchantId);
            return payCredentials.get(paymentId);
        } catch (NullPointerException e) {
            return null;
        }

    }

}
