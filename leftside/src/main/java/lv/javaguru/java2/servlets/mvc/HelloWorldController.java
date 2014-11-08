package lv.javaguru.java2.servlets.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Emils on 2014.11.08..
 */
public class HelloWorldController implements MVCController{


    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {


        return new MVCModel("/hello.jsp", "Hello world from MVC!");
    }
}
