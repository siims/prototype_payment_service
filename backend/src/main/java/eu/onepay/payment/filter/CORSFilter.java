package eu.onepay.payment.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("*")
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;

    HttpServletResponse resp = (HttpServletResponse) servletResponse;
    resp.addHeader("Access-Control-Allow-Origin","*");
    resp.addHeader("Access-Control-Allow-Methods","GET,POST");
    resp.addHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");

    if ( request.getMethod().equals("OPTIONS") ) {
        resp.setStatus(HttpServletResponse.SC_OK);
        return;
    }
    
    chain.doFilter(request, servletResponse);
}

 @Override
public void destroy() {}
}