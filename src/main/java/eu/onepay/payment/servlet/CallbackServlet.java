package eu.onepay.payment.servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import eu.onepay.payment.MerchantCredentials;
import eu.onepay.payment.OurContext;
import eu.onepay.payment.PaymentCredential;
import eu.onepay.payment.bank.ee.VKBankPayCredentials;
import eu.onepay.payment.bank.ee.calllback.VKBankCallback;

@Slf4j
public class CallbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServletContext servCtx;
    public static final String PAY_CALLBACK = "callback";

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String redirectUrl = logic(request, response);
        try {
            response.sendRedirect(redirectUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("get: " + request.getRequestURL());
        logic(request, response);

    }

    private String logic(HttpServletRequest request, HttpServletResponse response) {
        servCtx = request.getServletContext();
        Long merchantId = getMerchantId(request);
        Long paymentId = getPaymentId(request);
        PaymentCredential payCrede = getPayCredential(merchantId, paymentId);
        String redirectUri = "";
        if (payCrede instanceof VKBankPayCredentials) {

            VKBankCallback callback = new VKBankCallback(request, (VKBankPayCredentials) payCrede);
            log.debug("callback valid = " + callback.isValid() + " and successful = " + callback.isSuccessful());
            if (callback.isValid() && callback.isSuccessful()) {
                redirectUri = ((VKBankPayCredentials) payCrede).getReturnUrl();
            } else {
                redirectUri = ((VKBankPayCredentials) payCrede).getCancelUrl();
            }

            // TODO: DATABASE. Change transaction state in database.
            // VKBankCallback.getVK_Stamp - transaction Id.
        }
        return redirectUri;
    }

    private PaymentCredential getPayCredential(Long merchantId, Long paymentId) {
        return OurContext.getPaymentCredential(merchantId, paymentId, servCtx);
    }

    private Long getPaymentId(HttpServletRequest request) {
        String payIdString = getFromURL(PAY_CALLBACK, request);
        return Long.parseLong(payIdString);
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
