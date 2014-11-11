package lv.javaguru.java2.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author <a href="mailto:viktor.savonin@odnoklassniki.ru">Viktor Savonin</a>
 */
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        // Set response content type
        resp.setContentType("text/html");

        //Get current session
        HttpSession session = req.getSession();

        //In the url (EG. localhost:8080/hello?m=5) (The ?m=5)
        String str = req.getParameter("m");

        //long i = session.getCreationTime();

        //Set an attribute
        session.setAttribute("Time", "18:54");

        //Get an attribute
        Object i = session.getAttribute("Time");


        // Prepare output html
        PrintWriter out = resp.getWriter();
        out.println("<h1>" + "Hello WWW world from Java!" + "</h1>");
        if (str != null) {
            out.println("<h2>" + str + "</h2>");
        } else {
            out.println("<h2>" + i + "</h2>");
        }


    }


}
