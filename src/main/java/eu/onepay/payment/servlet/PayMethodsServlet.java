package eu.onepay.payment.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayMethodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServletContext servCtx;

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        servCtx = request.getServletContext();
        
    
    
    }

}
