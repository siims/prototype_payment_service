package eu.onepay.payment.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ResourceUpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("post:" + request.getRequestURL());

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("get: " + request.getRequestURL());
        // TODO: Implement resource reloading - resources that are held in ServletContext.

    }
    
}
