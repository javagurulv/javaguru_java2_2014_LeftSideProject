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
import java.util.Enumeration;
import java.util.List;

/**
 * Created by alekmiku on 2014.11.11..
 */
@Controller
@Transactional
public class TodoItemController {

    @Autowired
    @Qualifier("ORM_TodoItemDAO")
    private TodoItemDAO todoItemDAO;

    @RequestMapping(value = "todoItem", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequestList (HttpServletRequest request,
                                       HttpServletResponse response) {

        String action = request.getParameter("action");
        if (action != null && action.equals("delete")) {

            String itemID = request.getParameter("itemID");

            TodoItem itemToDel = todoItemDAO.getById(Long.valueOf(itemID));
            todoItemDAO.delete(itemToDel);
        }

        List<TodoItem> itemList = todoItemDAO.getAll();

//        TodoItem itemToEdit;
//
//        if (action.equals("edit")) {
//            String itemID = request.getParameter("itemID");
//            itemToEdit = todoItemDAO.getById(Long.valueOf(itemID));
//        }
//
//        ModelAndView model = new ModelAndView();
//        model.setViewName("TodoItemList");
//        model.addObject("model", itemToEdit);



        ModelAndView model = new ModelAndView();
        model.setViewName("TodoItemList");
        model.addObject("model", itemList);

        return model;

    }


//    @RequestMapping(value = "todoItem", method = {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView processRequestEdit (HttpServletRequest request, HttpServletResponse response) {
//
//
//        String action = request.getParameter("action");
//        TodoItem itemToEdit = null;
//        if (action.equals("edit")) {
//            String itemID = request.getParameter("itemID");
//            itemToEdit = todoItemDAO.getById(Long.valueOf(itemID));
//        }
//
//        ModelAndView model = new ModelAndView();
//        model.setViewName("TodoItemList");
//        model.addObject("model", itemToEdit);
//
//        return model;
//    }

}






