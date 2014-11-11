package lv.javaguru.java2.web.filters;

import lv.javaguru.java2.web.mvc.core.Authentication;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by SM on 11/3/2014.
 */
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (Authentication.isLoggedIn(session)) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect("login");
        }

    }

    @Override
    public void destroy() {

    }
}
