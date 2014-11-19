package lv.javaguru.java2.web;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FileDAO;
import lv.javaguru.java2.database.jdbc.FileDAOImpl;
import lv.javaguru.java2.domain.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Sergey on 07.11.14.
 */
//Todo: Move to MVC
public class FileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        FileDAO file = new FileDAOImpl();

        PrintWriter out = resp.getWriter();

        out.print("<h1>File page</h1><br>");

        try {
            List<File> fileList = file.getAll();
            out.println("File list size: " + fileList.size());

            for (File fileToken : fileList) {
                out.println("<hr><br><b>File Id</b> " + fileToken.getFileId()
                        + " <br><b>File Name:</b> " + fileToken.getFileName()
                        + " <br><b>Path:</b> " + fileToken.getPath()
                        + " <br><b>Upload Date:</b>" + fileToken.getUploadDate());
            }

        } catch (DBException e) {
            e.printStackTrace();
        }


    }

}
