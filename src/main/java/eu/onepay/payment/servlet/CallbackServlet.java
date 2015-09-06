package eu.onepay.payment.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CallbackServlet extends HttpServlet{
    
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        System.out.println("post");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("get");
    }
}
