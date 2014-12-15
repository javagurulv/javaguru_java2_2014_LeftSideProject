package lv.javaguru.java2.web.mvc.fileServlet;

import lv.javaguru.java2.database.FileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Sergey on 13.11.14.
 */
@Controller
@Transactional
public class FileController {
    private static final String DEFAULT_VIEW = "/file.jsp";

    @Autowired
    @Qualifier("ORM_FileDAO")
    private FileDAO fileDAO;

    @RequestMapping(value = "file", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {

        ModelAndView model = new ModelAndView();
        model.setViewName("file");

        return model;
    }

}






