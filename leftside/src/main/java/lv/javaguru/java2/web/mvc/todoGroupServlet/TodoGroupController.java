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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Emils on 2014.11.15..
 */
@Component
@Transactional
@MVCController(path = "/todoGroup",
        pageName = "ToDo Group",
        isVisible = true)
public class TodoGroupController implements MVCProcessor {
    private static final String DEFAULT_VIEW = "/TodoGroup.jsp";
    @Autowired
    @Qualifier("ORM_TodoGroupDAO")
    private TodoGroupDAO todoGroupDAO;

    @Override
    public MVCModel processRequest(MVCRequestParameters req) {
        //ToDo: Use authentication for filtering what to show

        List<TodoGroup> todoGroups = todoGroupDAO.getAll();
        //for(int i = 1; i < todoGroups.size(); i++){
        //    todoGroupDAO.getByGroupId((long)i);
        //}

        return new MVCModel(DEFAULT_VIEW, new TodoGroupModel(todoGroups));
    }
}