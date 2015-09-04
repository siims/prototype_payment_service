package eu.onepay.payment.servlet;

import java.io.IOException;
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

        String json = request.getReader().lines().collect(Collectors.joining());

        Gson gson = new Gson();
        PaymentRequest payRequest = gson.fromJson(json, PaymentRequest.class);

        OrderCredentials orderCrede = getOrderCredentials(payRequest);
        MerchantCredentials merchCrede = getMerchantCredentials(payRequest.merchantId);
        PaymentCredential payCrede = getPayCrededentials(merchCrede.getMerchantId(),  payRequest.paymentId);

        Form returnForm = getReturnForm(payCrede, orderCrede, merchCrede);

        response.getWriter().write(returnForm.toString());

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

    private PayMethod getDefaultPaymentMethod(Long paymentId) {
        @SuppressWarnings("unchecked")
        Map<String, PayMethod> payMethods = (Map<String, PayMethod>) servCtx.getAttribute(PayMethod.CONTEXT_KEY);

        return payMethods.get(paymentId);
    }

    private PaymentCredential getCustomPaymentCredentials(Long merchantId, Long paymentId) {

        try {
            @SuppressWarnings("unchecked")
            Map<String, Map<String, PaymentCredential>> merchantPayMethods = (Map<String, Map<String, PaymentCredential>>) servCtx
                    .getAttribute(PaymentCredential.CUSTOM_CREDE_KEY);
            Map<String, PaymentCredential> payCredentials = merchantPayMethods.get(merchantId);
            return payCredentials.get(paymentId);
        } catch (NullPointerException e) {
            return null;
        }

    }

}
