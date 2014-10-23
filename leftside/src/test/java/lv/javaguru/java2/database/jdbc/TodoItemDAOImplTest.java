package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.domain.TodoItem;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TodoItemDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();
    private TodoItemDAO todoItemDAO = new TodoItemDAOImpl();

    @Before
    public void setUp() throws Exception {
        databaseCleaner.cleanDatabase();
    }

    @Test
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
    public void testDelete() throws Exception {
        List<TodoItem> todoItemsBefore = todoItemDAO.getAll();
        TodoItem todoItem = createTodoItem("C1", "D1");
        todoItemDAO.create(todoItem);

        List<TodoItem> allTodoItems = todoItemDAO.getAll();
        assertEquals(allTodoItems.size(), 1 + todoItemsBefore.size());

        todoItemDAO.delete(todoItem.getItemId());
        allTodoItems = todoItemDAO.getAll();
        assertEquals(allTodoItems.size(), todoItemsBefore.size());
    }

    @Test
    public void testUpdate() throws Exception {
        TodoItem expected = createTodoItem("QQQ", "DDD");
        todoItemDAO.create(expected);

        expected.setTitle("ZZZ");
        todoItemDAO.update(expected);

        TodoItem actual = todoItemDAO.getById(expected.getItemId());
        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    public void testNonExistingTodoItemDoesNotExist() throws Exception {
        TodoItem todoItem = todoItemDAO.getById(12345l);
        assertEquals(todoItem, null);
    }

    @Test
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
        return createTodoItem(TodoItem.State.CREATED.value, title, description, DateTime.now().withTime(0, 0, 0, 0));
    }

    private TodoItem createTodoItem(Long stateId, String title, String description, DateTime dueDate) {
        TodoItem todoItem = new TodoItem();
        todoItem.setStateId(stateId);
        todoItem.setTitle(title);
        todoItem.setDescription(description);
        todoItem.setDueDate(dueDate);
        return todoItem;
    }
}