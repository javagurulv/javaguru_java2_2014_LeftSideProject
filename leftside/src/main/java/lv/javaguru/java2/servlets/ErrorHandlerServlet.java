package lv.javaguru.java2.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by SM on 11/3/2014.
 */
public class ErrorHandlerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        Throwable throwable = (Throwable)
                request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer)
                request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String)
                request.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String)
                request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        //FixMe:temporary start page
        out.println("<a href='/login'>LoginServlet page</a><br>");

        out.println("<h2>General information</h2>");
        out.println("Servlet Name : " + servletName + "<br>");
        out.println("The request URI: " + requestUri + "<br>");

        if (throwable == null && statusCode == null) {
            out.println("<h2>Error information is missing</h2>");
        }

        if (statusCode != null) {
            out.println("<h2>Status information</h2>");
            out.println("Status code : " + statusCode);
        }

        if (throwable != null) {
            out.println("<h2>Exception information</h2>");

            out.println("Exception Type : " + throwable.getClass().getName() + "</br>");
            out.println("Exception message: " + throwable.getMessage() + "<br><br>");
            out.println("StackTrace: <br>");

            for (StackTraceElement trace : throwable.getStackTrace()) {
                out.println("FileName: " + trace.getFileName() + "<br>");
                out.println("ClassName: " + trace.getClassName() + "<br>");
                out.println("MethodName: " + trace.getMethodName() + "<br>");
                out.println("LineNumber: " + trace.getLineNumber() + "<br><hr>");
            }
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}