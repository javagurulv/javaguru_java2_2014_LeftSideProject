package lv.javaguru.java2.web.mvc.todoItemCommentServlet;

import lv.javaguru.java2.database.TodoItemCommentDAO;
import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.web.mvc.core.MVCModel;
import lv.javaguru.java2.web.mvc.core.MVCRequestParameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TodoItemCommentsControllerTest {
    @Mock
    TodoItemDAO todoItemDAO;// = Mockito.mock(TodoItemDAO.class);
    @Mock
    TodoItemCommentDAO commentDAO;

    @InjectMocks
    TodoItemCommentsController controller = new TodoItemCommentsController();


    @Test
    public void testProcessRequest() throws Exception {
        MVCRequestParameters param = Mockito.mock(MVCRequestParameters.class);
        doReturn("1").when(param).getValue("item");


        MVCModel model =  controller.processRequest(param);

        verify(todoItemDAO, times(1)).getById(any(Long.class));
    }
}