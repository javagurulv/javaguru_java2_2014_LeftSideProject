package lv.javaguru.java2.web.mvc.todoGroupServlet;

import lv.javaguru.java2.database.TodoGroupDAO;
import lv.javaguru.java2.domain.TodoGroup;
import lv.javaguru.java2.web.mvc.core.MVCController;
import lv.javaguru.java2.web.mvc.core.MVCModel;
import lv.javaguru.java2.web.mvc.core.MVCProcessor;
import lv.javaguru.java2.web.mvc.core.MVCRequestParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Emils on 2014.11.15..
 */
@Component
@Transactional
@MVCController(path = "/todoGroup",
        pageName = "ToDo Group",
        isVisible = true)

@Controller
@RequestMapping(value = "TodoGroup", method = {RequestMethod.GET, RequestMethod.POST})
public class TodoGroupController implements MVCProcessor {
    private static final String DEFAULT_VIEW = "/TodoGroup.jsp";
    @Autowired
    @Qualifier("ORM_TodoGroupDAO")
    private TodoGroupDAO todoGroupDAO;

    @Override
    public MVCModel processRequest(MVCRequestParameters req) {
        //ToDo: Use authentication for filtering what to show

        List<TodoGroup> todoGroups = todoGroupDAO.getAllGroups();
        return new MVCModel(DEFAULT_VIEW, new TodoGroupModel(todoGroups));
    }
}