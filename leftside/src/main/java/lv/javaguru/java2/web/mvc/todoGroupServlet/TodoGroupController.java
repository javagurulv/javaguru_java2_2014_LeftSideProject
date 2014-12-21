package lv.javaguru.java2.web.mvc.todoGroupServlet;

import lv.javaguru.java2.database.TodoGroupDAO;
import lv.javaguru.java2.domain.TodoGroup;
import lv.javaguru.java2.domain.TodoItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    @Autowired
    SessionFactory sessionFactory;

    @RequestMapping (value = "todoGroup", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {

        //Check if logged in
        //Session session = sessionFactory.getCurrentSession();
        //boolean isAuthenticated = Authentication.isLoggedIn(session);

        ModelAndView model = new ModelAndView();
        model.setViewName("TodoGroup");
        List<TodoGroup> todoGroups = todoGroupDAO.getAll();

        //Get the name of added / deleted group
        String addedButtonName = request.getParameter("addedButtonName");
        String deletedButtonName = request.getParameter("deletedButtonName");

        String msg = null;
        //Check if a button was pressed.
        if(addedButtonName != "" && addedButtonName != null){
            createTodoGroup(addedButtonName);
            msg = "Group Added!";
        }else if(deletedButtonName != "" && deletedButtonName != null){
            msg = getDeletedTodoGroup(deletedButtonName,todoGroups);
        }

        todoGroups = todoGroupDAO.getAll();
        loadItems(todoGroups);

        TodoGroupModel todoGroupModel = new TodoGroupModel(todoGroups);
        setMsg(msg, getErrorMessage(addedButtonName,deletedButtonName), todoGroupModel);
        model.addObject("model", todoGroupModel);
        //model.addObject("auth", isAuthenticated);
        return model;
    }

    private String getDeletedTodoGroup(String name, List<TodoGroup> todoGroups){
        String msg;
        TodoGroup todoGroup = null;
        for(int i = 0; i < todoGroups.size(); i++){
            if(todoGroups.get(i).getName().equals(name)){
                todoGroup = todoGroups.get(i);
                break;
            }
        }
        if(todoGroup == null){
            msg = "No such group!";
        }else{
            todoGroupDAO.delete(todoGroup);
            msg = "Group Deleted!";
        }

        return msg;
    }

    private String getErrorMessage(String add, String delete){
        String msg = null;
        if(add == "" || delete == ""){
            msg = "Incorrect Name!";
        }
        return msg;
    }

    private void createTodoGroup(String groupName){
        TodoGroup todoGroup = new TodoGroup();
        todoGroup.setName(groupName);
        todoGroupDAO.create(todoGroup);
    }

    private void loadItems(List<TodoGroup> todoGroups){
        //PreLoad/Warmup (workaround for lazy loading)
        for (TodoGroup todoGroup : todoGroups) {
            List<TodoItem> todoItemList = todoGroup.getTodoItems();
            if(todoItemList != null) {
                for (TodoItem item : todoItemList) {
                    item.getTitle();
                }
            }
        }

    }

    private void setMsg(String Msg1, String Msg2,TodoGroupModel todoGroupModel){
        //Set Message
        if(Msg1 == null && Msg2 == null){
            todoGroupModel.setMessage(null);
        }else if(Msg1 != null && Msg2 == null){
            todoGroupModel.setMessage(Msg1);
        }else if(Msg1 == null && Msg2 != null){
            todoGroupModel.setMessage(Msg2);
        }
    }
}