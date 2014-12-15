package lv.javaguru.java2.web.mvc.todoItemServlet;

import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.domain.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekmiku on 2014.11.11..
 */
@Controller
@Transactional
public class TodoItemController {

    private static final String INSERT_OR_EDIT = "/TodoItemList.jsp";
    private static final String LIST_TODOITEM = "/TodoItemList.jsp";

    List<String> errList = new ArrayList<String>();

    @Autowired
    @Qualifier("ORM_TodoItemDAO")
    private TodoItemDAO todoItemDAO;

    @RequestMapping(value = "todoItem", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {

        ModelAndView model = new ModelAndView();
        model.setViewName("TodoItem");
/* Todo: add check of authentication and user id to list specific items only
        if (!req.isUserAuthenticated() ) {
            errList.add("User is not authenticated! ");
            return new MVCModel(LIST_TODOITEM, null, errList);
        }*/
/*
        String action = req.getValue("action");

        if (action.equals("list")) {
            System.out.println("Hello11");
            List<TodoItem> itemList = todoItemDAO.getAll();
            System.out.println("Hello22");
            return new MVCModel(LIST_TODOITEM, new TodoItemModel(itemList));

        } else if (action.equals("edit")) {

//            String id = req.getValue("itemId");
//            TodoItem todoItem = todoItemDAO.getById(Long.parseLong(id));
//            return new MVCModel(INSERT_OR_EDIT, new TodoItemModel(todoItem));

        } else if (action.equals("delete")) {

//            String id = req.getValue("itemId");
//            todoItemDAO.delete(Long.parseLong(id));

        }
*/


        System.out.println("Hello11");
        List<TodoItem> itemList = todoItemDAO.getAll();
        System.out.println("Hello22");
        model.addObject("model", new TodoItemModel(itemList));

        return model;
//        return new MVCModel(INSERT_OR_EDIT, "any other action requested");
    }


}






