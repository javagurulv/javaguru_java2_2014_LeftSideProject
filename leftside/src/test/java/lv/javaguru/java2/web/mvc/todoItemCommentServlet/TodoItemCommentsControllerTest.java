package lv.javaguru.java2.web.mvc.todoItemCommentServlet;

import lv.javaguru.java2.database.TodoItemCommentDAO;
import lv.javaguru.java2.database.TodoItemDAO;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TodoItemCommentsControllerTest {
    @Mock
    TodoItemDAO todoItemDAO;// = Mockito.mock(TodoItemDAO.class);
    @Mock
    TodoItemCommentDAO commentDAO;

    @InjectMocks
    TodoItemCommentsController controller = new TodoItemCommentsController();


//    @Test
//    public void testProcessRequest() throws Exception {
//        MVCRequestParameters param = Mockito.mock(MVCRequestParameters.class);
//        doReturn("1").when(param).getValue("item");
//
//
//        MVCModel model = controller.processRequest(param);
//
//        verify(todoItemDAO, times(1)).getById(any(Long.class));
//    }
}