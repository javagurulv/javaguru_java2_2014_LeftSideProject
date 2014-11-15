package lv.javaguru.java2.web.mvc.todoGroupServlet;

import lv.javaguru.java2.database.TodoGroupDAO;
import lv.javaguru.java2.database.jdbc.TodoGroupDAOImpl;
import lv.javaguru.java2.domain.TodoGroup;
import lv.javaguru.java2.web.mvc.core.MVCController;
import lv.javaguru.java2.web.mvc.core.MVCModel;
import lv.javaguru.java2.web.mvc.core.MVCProcessor;
import lv.javaguru.java2.web.mvc.core.MVCRequestParameters;

import java.util.List;

/**
 * Created by Emils on 2014.11.15..
 */
@MVCController(path = "/todoGroup",
        pageName = "ToDo Group",
        isVisible = true)
public class TodoGroupController implements MVCProcessor {
    private static final String DEFAULT_VIEW = "/TodoGroup.jsp";
    private static final TodoGroupDAO todoGroupDAO = new TodoGroupDAOImpl();


    @Override
    public MVCModel processRequest(MVCRequestParameters req) {

        List<TodoGroup> todoGroups = todoGroupDAO.getAll();

        return new MVCModel(DEFAULT_VIEW, new TodoGroupModel(todoGroups));
    }
}