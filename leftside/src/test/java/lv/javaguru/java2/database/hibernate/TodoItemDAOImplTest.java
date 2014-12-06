package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.TodoItem;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TodoItemDAOImplTest extends SpringIntegrationTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();
    @Autowired
    @Qualifier("ORM_TodoItemDAO")
    private TodoItemDAO todoItemDAO;

    @Before
    public void setUp() throws Exception {
        databaseCleaner.cleanDatabase();
    }

    @Test
    @Transactional
    public void testCreate() throws Exception {
        TodoItem todoItem = createTodoItem("C1", "D1");

        todoItemDAO.create(todoItem);

        TodoItem todoItemFromDB = todoItemDAO.getById(todoItem.getItemId());
        assertNotNull(todoItemFromDB);
        assertEquals(todoItem.getItemId(), todoItemFromDB.getItemId());
        assertEquals(todoItem.getStateId(), todoItemFromDB.getStateId());
        assertEquals(todoItem.getTitle(), todoItemFromDB.getTitle());
        assertEquals(todoItem.getDescription(), todoItemFromDB.getDescription());
        assertEquals(todoItem.getDueDate(), todoItemFromDB.getDueDate());
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        List<TodoItem> todoItemsBefore = todoItemDAO.getAll();
        TodoItem todoItem = createTodoItem("C1", "D1");
        todoItemDAO.create(todoItem);

        List<TodoItem> allTodoItems = todoItemDAO.getAll();
        assertEquals(allTodoItems.size(), 1 + todoItemsBefore.size());

        todoItemDAO.delete(todoItem);
        allTodoItems = todoItemDAO.getAll();
        assertEquals(allTodoItems.size(), todoItemsBefore.size());
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        TodoItem expected = createTodoItem("QQQ", "DDD");
        todoItemDAO.create(expected);

        expected.setTitle("ZZZ");
        todoItemDAO.update(expected);

        TodoItem actual = todoItemDAO.getById(expected.getItemId());
        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    @Transactional
    public void testNonExistingTodoItemDoesNotExist() throws Exception {
        TodoItem todoItem = todoItemDAO.getById(12345l);
        assertEquals(todoItem, null);
    }

    @Test
    @Transactional
    public void testMultipleTodoItemCreation() throws DBException {
        List<TodoItem> todoItemsBefore = todoItemDAO.getAll();
        TodoItem todoItem1 = createTodoItem("C1", "D1");
        TodoItem todoItem2 = createTodoItem("C2", "D2");
        todoItemDAO.create(todoItem1);
        todoItemDAO.create(todoItem2);
        List<TodoItem> todoItems = todoItemDAO.getAll();
        assertEquals(2 + todoItemsBefore.size(), todoItems.size());
    }

    private TodoItem createTodoItem(String title, String description) {
        return createTodoItem(TodoItem.State.CREATED.value, title, description, Calendar.getInstance());
    }

    private TodoItem createTodoItem(Long stateId, String title, String description, Calendar dueDate) {
        TodoItem todoItem = new TodoItem();
        todoItem.setStateId(stateId);
        todoItem.setTitle(title);
        todoItem.setDescription(description);
        todoItem.setDueDate(dueDate);
        return todoItem;
    }
}