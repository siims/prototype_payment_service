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
        // TODO: DATABASE: Save the payMethod to database
        OurTransaction transaction = new OurTransaction();// =
                                                          // Database.saveAsTransaction(payMethod);
        payMethod.setTransaction(transaction);

        return payMethod;
    }

    private PaymentAction ( PaymentRequest payRequest, ServletContext servCtx ){

        this.servCtx = servCtx;

        orderCrede = getOrderCredentials(payRequest);
        merchCrede = getMerchantCredentials(payRequest.merchantId);
        payCrede = getPaymentCredentials(merchCrede.getMerchantId(), payRequest.paymentId);

    }

    private PayMethod getPayMethod() {

        PayMethod method = getDefaultPaymentMethod(payCrede.getPaymentId());
        method.initAndVerify(payCrede, orderCrede, merchCrede);
        if (method.notValid()) {
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

    private PaymentCredential getPaymentCredentials(Long merchantId, Long paymentId) {

        try {
            @SuppressWarnings("unchecked")
            Map<Long, Map<Long, PaymentCredential>> merchantPayMethods = (Map<Long, Map<Long, PaymentCredential>>) servCtx
                    .getAttribute(PaymentCredential.CREDE_KEY);
            Map<Long, PaymentCredential> payCredentials = merchantPayMethods.get(merchantId);
            return payCredentials.get(paymentId);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }

    }

}
