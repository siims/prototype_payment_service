package eu.onepay.payment.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import eu.onepay.payment.OurTransaction;
import eu.onepay.payment.PayMethod;
import eu.onepay.payment.PaymentAction;
import eu.onepay.payment.html.FormFactory;

public class ApiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServletContext servCtx;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        servCtx = request.getServletContext();

        PaymentRequest payRequest = getRequestAsObject(request);

        PayMethod method = PaymentAction.makeTransaction(payRequest, servCtx);

        response.getWriter().write(FormFactory.asForm(method).toString());

        OurTransaction transaction = method.getTransaction();
        transaction.setTimeSentOut(new Date());
        TransactionChecker.addToTransactions(transaction);
    }

    private PaymentRequest getRequestAsObject(HttpServletRequest request) throws IOException {
        String json = request.getParameter("json_data");
        Gson gson = new Gson();
        return gson.fromJson(json, PaymentRequest.class);
    }

}
