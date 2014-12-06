package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.TodoItemCommentDAO;
import lv.javaguru.java2.domain.TodoItemComment;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TodoItemCommentDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();
    private TodoItemCommentDAO todoItemCommentDAO = new TodoItemCommentDAOImpl();

    @Before
    public void setUp() throws Exception {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws Exception {
        TodoItemComment itemComment = createTodoItemComment("T1", "M1", 1, 1);

        todoItemCommentDAO.create(itemComment);

        TodoItemComment todoCommentFromDB = todoItemCommentDAO.getById(itemComment.getCommentId());
        assertNotNull(todoCommentFromDB);
        assertEquals(itemComment.getCommentId(), todoCommentFromDB.getCommentId());
        assertEquals(itemComment.getUserId(), todoCommentFromDB.getUserId());
        assertEquals(itemComment.getItemId(), todoCommentFromDB.getItemId());
        assertEquals(itemComment.getTitle(), todoCommentFromDB.getTitle());
        assertEquals(itemComment.getMessage(), todoCommentFromDB.getMessage());
        assertEquals(itemComment.getDate(), todoCommentFromDB.getDate());
    }

    @Test
    public void testDelete() throws Exception {
        List<TodoItemComment> todoCommentsBefore = todoItemCommentDAO.getAll();
        TodoItemComment itemComment = createTodoItemComment("T1", "M1", 1, 1);
        todoItemCommentDAO.create(itemComment);

        List<TodoItemComment> allTodoComments = todoItemCommentDAO.getAll();
        assertEquals(allTodoComments.size(), 1 + todoCommentsBefore.size());

        todoItemCommentDAO.delete(itemComment.getCommentId());
        allTodoComments = todoItemCommentDAO.getAll();
        assertEquals(allTodoComments.size(), todoCommentsBefore.size());
    }

    @Test
    public void testUpdate() throws Exception {
        TodoItemComment expected = createTodoItemComment("T1", "M1", 1, 1);
        todoItemCommentDAO.create(expected);

        expected.setMessage("ZZZ");
        todoItemCommentDAO.update(expected);

        TodoItemComment actual = todoItemCommentDAO.getById(expected.getCommentId());
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    private TodoItemComment createTodoItemComment(String title, String message, long userId, long itemId) {
        TodoItemComment itemComment = new TodoItemComment();

        itemComment.setUserId(userId);
        itemComment.setItemId(itemId);
        itemComment.setDate(Calendar.getInstance());
        itemComment.setTitle(title);
        itemComment.setMessage(message);
        return itemComment;
    }
}