package lv.javaguru.java2.web;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoGroupDAO;
import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.database.jdbc.TodoGroupDAOImpl;
import lv.javaguru.java2.database.jdbc.TodoItemDAOImpl;
import lv.javaguru.java2.domain.TodoGroup;
import lv.javaguru.java2.domain.TodoItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emils on 2014.11.01..
 */
public class TodoGroupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        // Set response content type
        resp.setContentType("text/html");

        TodoGroupDAO td = new TodoGroupDAOImpl();
        TodoItemDAO tdi = new TodoItemDAOImpl();

        List<TodoGroup> todo = null;

        try {
            todo = td.getAll();
        } catch (DBException e) {
            throw new ServletException(e);
        }

        List<String> str = new ArrayList<String>();
        PrintWriter out = resp.getWriter();

        addGroupNamesToArray(todo, str);
        write(str, out, req, tdi);


    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException {

        String addedValue = req.getParameter("add");
        String deletedValue = req.getParameter("delete");

        // Set response content type
        resp.setContentType("text/html");

        TodoGroupDAO td = new TodoGroupDAOImpl();
        TodoItemDAO tdi = new TodoItemDAOImpl();

        List<TodoGroup> todo = null;

        try {
            todo = td.getAll();
        } catch (DBException e) {
            throw new ServletException(e);
        }

        List<String> groupNames = new ArrayList<String>();
        PrintWriter out = resp.getWriter();


        if (deletedValue != null) {
            long deletedLong = -1;
            deletedLong = removeGroup(deletedValue, todo, deletedLong);
            deleteGroupIdFromArray(td, deletedLong);
            addGroupNamesToArray(todo, groupNames);
            write(groupNames, out, req, tdi);
        } else if (addedValue != null) {

            addGroupNamesToArray(todo, groupNames);

            groupNames.add(addedValue);

            try {
                TodoGroup todoGroup = new TodoGroup();
                todoGroup.setName(addedValue);

                td.create(todoGroup);
            } catch (DBException e) {
                throw new ServletException(e);
            }


            write(groupNames, out, req, tdi);
        }
    }

    private void addGroupNamesToArray(List<TodoGroup> todo, List<String> str) {
        for (int i = 0; i < todo.size(); i++) {
            str.add(todo.get(i).getName());
        }
    }

    private void deleteGroupIdFromArray(TodoGroupDAO td, long deletedLong) throws ServletException {
        if (deletedLong != -1) {
            try {
                td.delete(deletedLong);
            } catch (DBException e) {
                throw new ServletException(e);
            }
        }
    }

    private long removeGroup(String deletedValue, List<TodoGroup> todo, long deletedLong) {
        for (int i = 0; i < todo.size(); i++) {
            if (isNameEqualDeletedValue(deletedValue, todo, i)) {
                deletedLong = todo.get(i).getGroupId();
                todo.remove(i);
                break;
            }
        }
        return deletedLong;
    }

    private boolean isNameEqualDeletedValue(String deletedValue, List<TodoGroup> todo, int i) {
        return todo.get(i).getName().equals(deletedValue);
    }


    private void write(List<String> str, PrintWriter out, HttpServletRequest req, TodoItemDAO tdi) throws ServletException {

        out.println("<h1>" + "Remote User : " + req.getRemoteUser() + "</h1>");

        List<TodoItem> todoItems = null;

        try {
            todoItems = tdi.getAll();
        } catch (DBException e) {
            throw new ServletException(e);
        }
        List<TodoItem> list1 = null;
        List<TodoItem> list2 = null;
        List<TodoItem> list3 = null;
        List<TodoItem> list4 = null;

        for (int i = 0; i < todoItems.size(); i++) {

            try {
                switch (i) {
                    case (0):
                        list1 = tdi.getByGroupId((long) 1);
                        break;
                    case (1):
                        list2 = tdi.getByGroupId((long) 2);
                        break;
                    case (2):
                        list3 = tdi.getByGroupId((long) 3);
                        break;
                    case (3):
                        list4 = tdi.getByGroupId((long) 4);
                        break;
                }
            } catch (DBException dbe) {
                throw new ServletException(dbe);
            }

        }

        int size = -1;

        breakPoint:
        for (int i = 0; i < str.size(); i++) {
            int nr = i + 1;

            switch (i) {
                case (0):
                    out.print("<h1>" + nr + ". " + str.get(i) + " ");
                    for (int j = 0; j < list1.size(); j++) {
                        out.print(" : " + list1.get(j).getTitle());
                    }
                    out.print(" --- Amount = " + list1.size() + "</h1>");
                    break;
                case (1):
                    out.print("<h1>" + nr + ". " + str.get(i) + " ");
                    for (int j = 0; j < list2.size(); j++) {
                        out.print(" : " + list2.get(j).getTitle());
                    }
                    out.print(" --- Amount = " + list2.size() + "</h1>");
                    break;
                case (2):
                    out.print("<h1>" + nr + ". " + str.get(i) + " ");
                    for (int j = 0; j < list3.size(); j++) {
                        out.print(" : " + list3.get(j).getTitle());
                    }
                    out.print(" --- Amount = " + list3.size() + "</h1>");
                    break;
                case (3):
                    out.print("<h1>" + nr + ". " + str.get(i) + " ");
                    for (int j = 0; j < list4.size(); j++) {
                        out.print(" : " + list4.get(j).getTitle());
                    }
                    out.print(" --- Amount = " + list4.size() + "</h1>");
                    break;
                default:
                    size = i;
                    break breakPoint;
            }

        }

        if (size < str.size() && size != -1) {
            for (int i = size; i < str.size(); i++) {
                int nr = i + 1;
                out.print("<h1>" + nr + ". " + str.get(i) + " --- Amount = 0 </h1>");
            }
        }

        out.println("<body>\n" +
                "<form action=\"todoGroup\" method=\"post\">\n" +
                "Add: <input type=\"text\" name=\"add\"><br>\n" +
                "<input type=\"submit\">\n" +
                "</form>\n" +
                "</body>");

        out.println("<body>\n" +
                "<form action=\"todoGroup\" method=\"post\">\n" +
                "Delete: <input type=\"text\" name=\"delete\"><br>\n" +
                "<input type=\"submit\">\n" +
                "</form>\n" +
                "</body>");
    }
}