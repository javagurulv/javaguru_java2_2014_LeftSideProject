package lv.javaguru.java2.web.mvc.todoItemServlet;

import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.domain.TodoItem;
import lv.javaguru.java2.web.mvc.core.MVCController;
import lv.javaguru.java2.web.mvc.core.MVCModel;
import lv.javaguru.java2.web.mvc.core.MVCProcessor;
import lv.javaguru.java2.web.mvc.core.MVCRequestParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    List<String> errList = new ArrayList<String>();

    @Autowired
    private TodoItemDAO todoItemDAO;

    @Override
    public MVCModel processRequest(MVCRequestParameters req) {
/* Todo: add check of authentication and user id to list specific items only
        if (!req.isUserAuthenticated() ) {
            errList.add("User is not authenticated! ");
            return new MVCModel(LIST_TODOITEM, null, errList);
        }*/

        String action = req.getValue("action");

        if (action.equals("list")) {

            List<TodoItem> itemList = todoItemDAO.getAll();
            return new MVCModel(LIST_TODOITEM, new TodoItemModel(itemList));

        } else if (action.equals("edit")) {

            String id = req.getValue("itemId");
            TodoItem todoItem = todoItemDAO.getById(Long.parseLong(id));
            return new MVCModel(INSERT_OR_EDIT, new TodoItemModel(todoItem));

        } else if (action.equals("delete")) {

            String id = req.getValue("itemId");
            todoItemDAO.delete(Long.parseLong(id));

        }

        return new MVCModel(INSERT_OR_EDIT, "any other action requested");
    }

}





