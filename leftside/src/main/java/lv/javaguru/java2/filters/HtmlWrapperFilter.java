package lv.javaguru.java2.filters;

import lv.javaguru.java2.core.ConfigReader;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by SM on 11/3/2014.
 */
public class HtmlWrapperFilter implements Filter {
    private static ConfigReader config = new ConfigReader();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        PrintWriter out = response.getWriter();

        out.println("<table> <tr><td valign='top' style='padding:10;'><b>Menu:</b><br>" +
                "<a href='/hello'>HelloWorld</a><br>" +
                "<a href='/todoGroup'>ToDo Groups</a><br>" +
                "<a href='/todoItem'>ToDo Items</a><br>" +
                "<a href='/todoComments?item='>ToDo Comments</a><br>" +
                "<a href='/file'>Files</a><br>" +
                "<a href='/login'>LoginServlet</a><br>" +
                "<a href='/ErrorHandler'>ErrorHandler</a><br>" +
                "</td><td width='*' valign='top' style='padding:10;'>");

        chain.doFilter(request, response);

        if (config.isDebugMode()) {

            out.println("<br><br><hr><h1>Debug section</h1>");
            HttpSession session = req.getSession(false);

            out.println("<h2>General information</h2>");
            out.println("The request URI: " + req.getRequestURI() + "<br>");
            out.println("Method : " + req.getMethod() + "<br>");

            out.println("<h2>Request parameters:</h2>");
            Enumeration<String> parameterNames = request.getParameterNames();
            if (parameterNames.hasMoreElements()) {
                out.println("<table><thead><tr><td>Name</td><td>Value</td></tr></thead><tbody>");
                while (parameterNames.hasMoreElements()) {
                    String name = parameterNames.nextElement();
                    String value = request.getParameter(name);
                    out.println("<tr><td>" + name + "</td><td>" + value + "</td></tr>");
                }
                out.println("</tbody></table><br>");
            } else {
                out.println("---None---<br>");
            }

            out.println("<h2>Session information</h2>");
            if (null == session) {
                out.println("---No session---<br>");
            } else {
                Enumeration<String> attributeNames = session.getAttributeNames();
                if (attributeNames.hasMoreElements()) {
                    out.println("<table><thead><tr><td>Name</td><td>Value</td></tr></thead><tbody>");
                    while (attributeNames.hasMoreElements()) {
                        String name = attributeNames.nextElement();
                        Object value = session.getAttribute(name);
                        out.println("<tr><td>" + name + "</td><td>" + value.toString() + "</td></tr>");
                    }
                    out.println("</tbody></table><br>");
                } else {
                    out.println("--- No attributes set ---<br>");
                }
            }
        }
        
        out.println("</td></tr></table>");
    }

    @Override
    public void destroy() {

    }
}
