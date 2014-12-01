package lv.javaguru.java2.web.mvc.todoItemServlet;

import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.domain.TodoItem;
import lv.javaguru.java2.web.mvc.core.MVCController;
import lv.javaguru.java2.web.mvc.core.MVCModel;
import lv.javaguru.java2.web.mvc.core.MVCProcessor;
import lv.javaguru.java2.web.mvc.core.MVCRequestParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by alekmiku on 2014.11.11..
 */
@Component
@MVCController(path = "/todoItem",
        pageName = "ToDo Item",
        isVisible = true)
public class TodoItemController implements MVCProcessor {

    private static final String INSERT_OR_EDIT = "/TodoItemList.jsp";
    private static final String LIST_TODOITEM = "/TodoItemList.jsp";

    String forward;

    @Autowired
    private TodoItemDAO todoItemDAO;

    @Override
    public MVCModel processRequest(MVCRequestParameters req) {

        String action = req.getValue("action");
        //TodoItem todoItemList = null;

        if (action.equals("list")) {

            forward = LIST_TODOITEM;

            List<TodoItem> itemList = todoItemDAO.getAll();
            return new MVCModel(forward,new TodoItemModel(itemList));

        } else if (action.equals("edit")) {

            forward = INSERT_OR_EDIT;

            String id = req.getValue("itemId");
            TodoItem todoItem = todoItemDAO.getById(Long.parseLong(id));
            return new MVCModel(forward, new TodoItemModel(todoItem));

        } else if (action.equals("delete")) {

            forward = INSERT_OR_EDIT;

            String id = req.getValue("itemId");
            todoItemDAO.delete(Long.parseLong(id));

        } else {
            forward = INSERT_OR_EDIT;
        }

        return new MVCModel(forward, "Controller after else");
    }

}





