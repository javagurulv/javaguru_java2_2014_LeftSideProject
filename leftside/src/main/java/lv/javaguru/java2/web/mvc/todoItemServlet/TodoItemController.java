package lv.javaguru.java2.web.mvc.todoItemServlet;

import lv.javaguru.java2.database.TodoItemDAO;
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
    private static final String DEFAULT_VIEW = "/TodoItem.jsp";
    @Autowired
    private TodoItemDAO todoItemDAO;


    @Override
    public MVCModel processRequest(MVCRequestParameters req) {
        //FixMe: Current code has no value.
        //ToDo: Use authentication for filtering what to show

        List<String> errList = new ArrayList<String>();
        errList.add("User is not Authenticated");

        if (!req.isUserAuthenticated()) {
            return new MVCModel(DEFAULT_VIEW, null, errList);
        }

        req.getParameterNames();

        return new MVCModel(DEFAULT_VIEW, "Object Data from new MVC model in TodoItem Controller");
    }

}
