package eu.onepay.payment.servlet;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.onepay.payment.OurContext;
import eu.onepay.payment.PaymentCredential;

public class PayMethodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServletContext servCtx;

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        servCtx = request.getServletContext();
        Long merchantId = null;
        Map<Long, PaymentCredential> paymentCredentials = OurContext.getPaymentCredentials(servCtx, merchantId);
        
    
    
    }

}
