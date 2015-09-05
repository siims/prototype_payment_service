package eu.onepay.payment.servlet;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
        
        // TODO: Save the payMethod to database
        // Database.saveTransaction(payMethod);
        
        // TODO: Add job to Some Atomic class or job queue
    }
    
    

    private PaymentRequest getRequestAsObject(HttpServletRequest request) throws IOException{
        String json = request.getReader().lines().collect(Collectors.joining());
        Gson gson = new Gson();
        return gson.fromJson(json, PaymentRequest.class);
    }

}
