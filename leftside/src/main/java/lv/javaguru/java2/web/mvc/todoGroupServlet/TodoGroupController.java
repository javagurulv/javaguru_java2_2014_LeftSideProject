package lv.javaguru.java2.web.mvc.todoGroupServlet;

import lv.javaguru.java2.database.TodoGroupDAO;
import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.domain.State;
import lv.javaguru.java2.domain.TodoGroup;
import lv.javaguru.java2.domain.TodoItem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
 * Created by Emils on 2014.11.15..
 */
@Controller
@Transactional
public class TodoGroupController {
    private static final String DEFAULT_VIEW = "/TodoGroup.jsp";
    private List<TodoGroup> deletableTodoGroups;
    @Autowired
    @Qualifier("ORM_TodoGroupDAO")
    private TodoGroupDAO todoGroupDAO;

    @Autowired
    @Qualifier("ORM_TodoItemDAO")
    private TodoItemDAO todoItemDAO;

    @Autowired
    SessionFactory sessionFactory;

    @RequestMapping (value = "todoGroup", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {

        //Check if logged in
        boolean isAuthenticated = isLoggedIn();

        ModelAndView model = new ModelAndView();
        model.setViewName("TodoGroup");
        List<TodoGroup> todoGroups = todoGroupDAO.getAll();

        //Get the name of added / deleted group
        String addedButtonName = request.getParameter("addedButtonName");
        String deletedButtonName = request.getParameter("deletedButtonName");
        String addedItemName = request.getParameter("addedItemName");
        String groupNameForAddedItem = request.getParameter("groupId");
        String extraGroupId = request.getParameter("extraGroupId");


        String msg = null;

        //Check if a button was pressed.
        if(addedButtonName != "" && addedButtonName != null){
            msg = createTodoGroup(addedButtonName);
        }else if(deletedButtonName != "" && deletedButtonName != null){
            msg = getDeletedTodoGroup(deletedButtonName,todoGroups);
        }else if(groupNameForAddedItem != null){
            long groupId = tryToParseToLong(groupNameForAddedItem);
            msg = createItemWithGroup(addedItemName,groupId,todoGroups);
        }else if(extraGroupId != null){
            long groupId = tryToParseToLong(extraGroupId);
            TodoGroup todoGroup = getTodoGroupFromId(todoGroups,groupId);
            todoGroupDAO.delete(todoGroup);
            msg = "Group Deleted!";
        }

        todoGroups = todoGroupDAO.getAll();
        loadItems(todoGroups);

        TodoGroupModel todoGroupModel = new TodoGroupModel(todoGroups);
        if(msg == "error"){
            msg = null;
            model.addObject("error", deletableTodoGroups);
        }
        setMsg(msg, getErrorMessage(addedButtonName,deletedButtonName), todoGroupModel);
        model.addObject("model", todoGroupModel);
        model.addObject("auth", isAuthenticated);
        return model;
    }

    private String getDeletedTodoGroup(String name, List<TodoGroup> todoGroups){
        String msg;
        TodoGroup todoGroup;
        try {
            todoGroup = getTodoGroupFromString(name, todoGroups);
        } catch (Exception e) {
            return "error";
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

    private String createTodoGroup(String groupName){
        TodoGroup todoGroup = new TodoGroup();
        todoGroup.setName(groupName);
        todoGroupDAO.create(todoGroup);
        return "Group Added!";
    }

    private String createItemWithGroup(String itemName, long groupID, List<TodoGroup> todoGroups){
        TodoGroup todoGroup;
        todoGroup = getTodoGroupFromId(todoGroups, groupID);
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle(itemName);
        todoItem.setTodoGroup(todoGroup);
        todoItem.setDescription(" ");
        todoItem.setDueDate(null);
        State state = new State();
        state.setId((long)1);
        todoItem.setStateId(state);
        todoItemDAO.create(todoItem);

        return "Item Created!";
    }

    private long tryToParseToLong(String id){
        return Long.parseLong(id);
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

    private TodoGroup getTodoGroupFromString(String groupName, List<TodoGroup> todoGroups) throws Exception{
        TodoGroup todoGroup = null;
        List<TodoGroup> deletableTodoGroups = new ArrayList<TodoGroup>();
        for(int i = 0; i < todoGroups.size(); i++){
            String TodoGroupName = todoGroups.get(i).getName().toLowerCase();
            if(TodoGroupName.equals(groupName.toLowerCase())){
                deletableTodoGroups.add(todoGroups.get(i));
            }
        }

        if(deletableTodoGroups.size() > 1){
            this.deletableTodoGroups = deletableTodoGroups;
            throw new Exception();
        }else if(deletableTodoGroups.size() == 1){
            todoGroup = deletableTodoGroups.get(0);
        }

        return todoGroup;
    }

    private boolean isLoggedIn(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authName = auth.getName();
        if(authName != "anonymousUser"){
            return true;
        }else{
            return false;
        }
    }

    private TodoGroup getTodoGroupFromId(List<TodoGroup> todoGroups, long id){
        TodoGroup todoGroup = null;
        for(int i = 0; i < todoGroups.size(); i++){
            long TodoGroupId = todoGroups.get(i).getGroupId();
            if(TodoGroupId == id){
                todoGroup = todoGroups.get(i);
            }
        }

        return todoGroup;
    }
}