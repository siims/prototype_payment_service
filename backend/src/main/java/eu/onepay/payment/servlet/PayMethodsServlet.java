package eu.onepay.payment.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import com.google.gson.Gson;

import eu.onepay.payment.MerchantCredentials;
import eu.onepay.payment.OurContext;
import eu.onepay.payment.PaymentCredential;
@Slf4j
@WebServlet("/get/paymethods/*")
public class PayMethodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServletContext servCtx;

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        servCtx = request.getServletContext();
        Long merchantId = getMerchantId(request);
        Map<Long, PaymentCredential> paymentCredentials = OurContext.getPaymentCredentials(servCtx, merchantId);
        
        Gson gson = new Gson();
        String json = gson.toJson(paymentCredentials.values());
        json = "jsonpCallback("+json+")";
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    
    }
    
    private String getFromURL(String name, HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        String retStr = "";
        String patternStr = ".*/" + name + "/([0-9]+).*";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(requestURL);
        if (matcher.find()) {
            retStr = matcher.group(1);
        } else {
            log.info("Didn't find requested string from url");
        }
        return retStr;
    }

    private Long getMerchantId(HttpServletRequest request) {
        String payIdString = getFromURL(MerchantCredentials.URL_KEY, request);
        return Long.parseLong(payIdString);
    }

}
