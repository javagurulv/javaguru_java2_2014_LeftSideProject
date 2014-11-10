package lv.javaguru.java2.web;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.web.mvc.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by SM on 11/2/2014.
 */
public class LoginServlet extends HttpServlet {
    private static final String paramUser = "user";
    private static final String paramPass = "pwd";
    private static final String loginForm = "<h1>Please log in:</h1><br>" +
            "<form method=\"post\">" +
            "Username: <input type=\"text\" name=\"" + paramUser + "\"><br>" +
            "Password: <input type=\"password\" name=\"" + paramPass + "\"><br>" +
            "<input type=\"submit\" value=\"Login\">" +
            "<br><br><br>Hint: Easiest combination is freddy:rock";

    private static final UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {

        String usr = req.getParameter(paramUser);
        String pwd = req.getParameter(paramPass);

        HttpSession session = req.getSession();
        String errorMsg = null;

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        if (0 != usr.length() && 0 != pwd.length()) {
            try {
                User user = userDAO.getByLogin(usr);
                if (null != user) {
                    if (user.getPassword().equals(pwd)) {
                        Authentication.authenticate(session, user);
                    } else {
                        errorMsg = "<h2><font color=\"red\">Password does not match for provided username.</font></h2><br>";
                    }
                } else {
                    errorMsg = "<h2><font color=\"red\">User not found for provided username.</font></h2><br>";
                }
            } catch (DBException e) {
                errorMsg = "<h2><font color=\"red\">Exception occurred:</font></h2><br>"
                        .concat(e.getStackTrace().toString());
            }
        } else {
            errorMsg = "<h2><font color=\"red\">Please check your input and try again.</font></h2><br>";
        }

        if (Authentication.isLoggedIn(session)) {
            out.println("<h1>" + "You logged in!" + "</h1>");
        } else {
            out.println(null == errorMsg ? "Unknown error happened." : errorMsg);
            out.println("<br>");
            out.println(loginForm);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession();
        if (Authentication.isLoggedIn(session)) {
            String logoutRequest = req.getParameter("logout");
            if (null != logoutRequest && logoutRequest.equals("true")) {
                Authentication.invalidate(session);
                out.println("<h1>" + "Good bye..." + "</h1>");
            } else {
                out.println("<h1>" + "You already logged in!" + "</h1><br>");
                out.println("<a href=\"?logout=true\">Log out.</a>");
            }
        } else {
            out.println(loginForm);
        }
    }

}
