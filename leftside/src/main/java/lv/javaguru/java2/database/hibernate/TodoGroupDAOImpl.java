package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.TodoGroupDAO;
import lv.javaguru.java2.domain.TodoGroup;
import org.springframework.stereotype.Component;

/**
 * Created by SM on 12/6/2014.
 */
@Component("ORM_TodoGroupDAO")
public class TodoGroupDAOImpl extends DAOImpl<TodoGroup> implements TodoGroupDAO {
}
