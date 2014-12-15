package lv.javaguru.java2.web.mvc.helloServlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Emils on 2014.11.08..
 */

@Controller
public class HelloWorldController {

    @RequestMapping (value = "hello", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {

        ModelAndView model = new ModelAndView();
        model.setViewName("hello");
        model.addObject("model","Hello world from Spring MVC!");

        return model;
    }
}
