package lv.javaguru.java2.web.mvc.fileServlet;

import lv.javaguru.java2.database.FileDAO;
import lv.javaguru.java2.domain.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Sergey on 13.11.14.
 */
@Controller
@Transactional
public class FileController {

    @Autowired
    @Qualifier("ORM_FileDAO")
    private FileDAO fileDAO;


        @RequestMapping(value = "/file", method = RequestMethod.GET)
        public String listFiles(Model model) {
            model.addAttribute("fileItem", new File());
            model.addAttribute("listFiles", fileDAO.getAll());
            return "file";
        }


}






