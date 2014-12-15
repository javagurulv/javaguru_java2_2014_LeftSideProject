package lv.javaguru.java2.web.mvc.todoGroupServlet;

import lv.javaguru.java2.database.TodoGroupDAO;
import lv.javaguru.java2.domain.TodoGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Emils on 2014.11.15..
 */
@Controller
@Transactional
public class TodoGroupController {
    private static final String DEFAULT_VIEW = "/TodoGroup.jsp";
    @Autowired
    @Qualifier("ORM_TodoGroupDAO")
    private TodoGroupDAO todoGroupDAO;

    @RequestMapping (value = "TodoGroup", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {
        //ToDo: Use authentication for filtering what to show

        ModelAndView model = new ModelAndView();
        model.setViewName("TodoGroup");
        List<TodoGroup> todoGroups = todoGroupDAO.getAllGroups();
        model.addObject("model", new TodoGroupModel(todoGroups));
        return model;
    }
}