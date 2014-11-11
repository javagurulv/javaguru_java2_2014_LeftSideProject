package lv.javaguru.java2.web.mvc.todoItemServlet;

import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.database.jdbc.TodoItemDAOImpl;
import lv.javaguru.java2.web.mvc.core.MVCController;
import lv.javaguru.java2.web.mvc.core.MVCModel;
import lv.javaguru.java2.web.mvc.core.MVCProcessor;
import lv.javaguru.java2.web.mvc.core.MVCRequestParameters;

/**
 * Created by alekmiku on 2014.11.11..
 */

@MVCController(path = "/todoItem",
        pageName = "ToDo Item",
        isVisible = true)
public class TodoItemController implements MVCProcessor {
    private static final String DEFAULT_VIEW = "/TodoItem.jsp";
    private static final TodoItemDAO todoItemDAO = new TodoItemDAOImpl();


    @Override
    public MVCModel processRequest(MVCRequestParameters req) {
        return new MVCModel(DEFAULT_VIEW, "Object Data from new MVC model in TodoItem Controller");
    }
}
