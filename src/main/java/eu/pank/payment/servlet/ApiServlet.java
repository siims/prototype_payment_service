package eu.pank.payment.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.pank.payment.MerchantCredentials;
import eu.pank.payment.OrderCredentials;
import eu.pank.payment.PayMethod;
import eu.pank.payment.PaymentCredential;
import eu.pank.payment.html.Form;

public class ApiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HttpServletRequest req;
    private ServletContext servCtx;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        servCtx = request.getServletContext();

        PaymentCredential payCrede = new PaymentCredential();
        OrderCredentials orderCrede = new OrderCredentials();
        MerchantCredentials merchCrede = new MerchantCredentials();

        payCrede = checkForCustomCredentials(merchCrede.getMerchantId(), payCrede);

        Form returnForm = getReturnForm(payCrede, orderCrede, merchCrede);

        response.getWriter().write(returnForm.toString());

    }

    private PaymentCredential checkForCustomCredentials(Long merchantId, PaymentCredential payCrede) {
        PaymentCredential customPayCrede = getCustomPaymentCredentials(merchantId, payCrede.getPaymentId());
        if (customPayCrede != null) {
            payCrede = customPayCrede;
        }

        return payCrede;
    }

    private Form getReturnForm(PaymentCredential payCrede, OrderCredentials orderCrede, MerchantCredentials merchCrede) {

        PayMethod method = getPaymentMethod(payCrede, merchCrede);
        method.init(payCrede, orderCrede, merchCrede);
        Form retForm = method.asForm();

        return retForm;
    }

    private PayMethod getPaymentMethod(PaymentCredential payCrede, MerchantCredentials merchCrede) {
        Long paymentId = payCrede.getPaymentId();

        PayMethod payMethod = getDefaultPaymentMethod(paymentId);

        return payMethod;
    }

    @SuppressWarnings("unchecked")
    private PayMethod getDefaultPaymentMethod(Long paymentId) {

        Map<String, PayMethod> payMethods = (Map<String, PayMethod>) servCtx.getAttribute(PayMethod.CONTEXT_KEY);

        return payMethods.get(paymentId);
    }

    @SuppressWarnings("unchecked")
    private PaymentCredential getCustomPaymentCredentials(Long merchantId, Long paymentId) {

        try {
            Map<String, Map<String, PaymentCredential>> merchantPayMethods = (Map<String, Map<String, PaymentCredential>>) servCtx
                    .getAttribute(PaymentCredential.CUSTOM_METHODS_KEY);
            Map<String, PaymentCredential> payCredentials = merchantPayMethods.get(merchantId);
            return payCredentials.get(paymentId);
        } catch (NullPointerException e) {
            return null;
        }

    }

}
