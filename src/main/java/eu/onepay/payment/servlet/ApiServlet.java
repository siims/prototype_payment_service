package eu.onepay.payment.servlet;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import eu.onepay.payment.MerchantCredentials;
import eu.onepay.payment.OrderCredentials;
import eu.onepay.payment.PayMethod;
import eu.onepay.payment.PaymentCredential;
import eu.onepay.payment.html.Form;

public class ApiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServletContext servCtx;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        servCtx = request.getServletContext();

        PaymentRequest payRequest = getRequestAsObject(request);

        OrderCredentials orderCrede = getOrderCredentials(payRequest);
        MerchantCredentials merchCrede = getMerchantCredentials(payRequest.merchantId);
        PaymentCredential payCrede = getPayCrededentials(merchCrede.getMerchantId(),  payRequest.paymentId);

        Form returnForm = getReturnForm(payCrede, orderCrede, merchCrede);

        response.getWriter().write(returnForm.toString());
        

    }

    private PaymentRequest getRequestAsObject(HttpServletRequest request) throws IOException{
        String json = request.getReader().lines().collect(Collectors.joining());
        Gson gson = new Gson();
        return gson.fromJson(json, PaymentRequest.class);
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

    private Form getReturnForm(PaymentCredential payCrede, OrderCredentials orderCrede, MerchantCredentials merchCrede) {

        PayMethod method = getDefaultPaymentMethod(payCrede.getPaymentId());
        method.initAndVerify(payCrede, orderCrede, merchCrede);
        Form retForm = method.asForm();

        return retForm;
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
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
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
