package lv.javaguru.java2.servlets;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.database.jdbc.TodoItemDAOImpl;
import lv.javaguru.java2.domain.TodoItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * Created by alekmiku on 2014.11.06..
 */
public class TodoItemServlet extends HttpServlet {

    private static final String myTestText = "<h1>Todo Item page</h1><br>";

    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Item List: ");
    }

    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        TodoItemDAO item = new TodoItemDAOImpl();

        PrintWriter out = resp.getWriter();
        out.print(myTestText);

        try {
            List<TodoItem> itemList = item.getAll();
            out.println("Item list size: " + itemList.size());

            for (TodoItem itemToken : itemList) {
                out.println("<hr><br><b>Item</b> " + itemToken.getItemId()
                        + " <br><b>Title:</b> " + itemToken.getTitle()
                        + " <br><b>Description:</b> " + itemToken.getDescription()
                        + " <br><b>Due Date::</b>" + itemToken.getDueDate());
            }


        } catch (DBException e) {
            e.printStackTrace();
        }


    }
}
