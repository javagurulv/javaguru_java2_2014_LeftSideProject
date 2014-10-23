package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoGroupDAO;
import lv.javaguru.java2.domain.TodoGroup;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TodoGroupDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();
    private TodoGroupDAO todoGroupDAO = new TodoGroupDAOImpl();

    @Before
    public void setUp() throws Exception {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws Exception {
        TodoGroup todoGroup = createTodoGroup("F");

        todoGroupDAO.create(todoGroup);

        TodoGroup todoGroupFromDB = todoGroupDAO.getById(todoGroup.getGroupId());
        assertNotNull(todoGroupFromDB);
        assertEquals(todoGroup.getGroupId(), todoGroupFromDB.getGroupId());
        assertEquals(todoGroup.getName(), todoGroupFromDB.getName());
    }

    @Test
    public void testDelete() throws Exception {
        List<TodoGroup> todoGroupsBefore = todoGroupDAO.getAll();
        TodoGroup todoGroup = createTodoGroup("Test");
        todoGroupDAO.create(todoGroup);

        List<TodoGroup> allTodoGroups = todoGroupDAO.getAll();
        assertEquals(allTodoGroups.size(), 1 + todoGroupsBefore.size());

        todoGroupDAO.delete(todoGroup.getGroupId());
        allTodoGroups = todoGroupDAO.getAll();
        assertEquals(allTodoGroups.size(), todoGroupsBefore.size());
    }

    @Test
    public void testUpdate() throws Exception {
        TodoGroup expected = createTodoGroup("QQQ");
        todoGroupDAO.create(expected);

        expected.setName("ZZZ");
        todoGroupDAO.update(expected);

        TodoGroup actual = todoGroupDAO.getById(expected.getGroupId());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void testNonExistingTodoGroupDoesNotExist() throws Exception {
        TodoGroup todoGroup = todoGroupDAO.getById(12345l);
        assertEquals(todoGroup, null);
    }

    @Test
    public void testMultipleTodoGroupCreation() throws DBException {
        List<TodoGroup> todoGroupsBefore = todoGroupDAO.getAll();
        TodoGroup todoGroup1 = createTodoGroup("F1");
        TodoGroup todoGroup2 = createTodoGroup("F2");
        todoGroupDAO.create(todoGroup1);
        todoGroupDAO.create(todoGroup2);
        List<TodoGroup> todoGroups = todoGroupDAO.getAll();
        assertEquals(2 + todoGroupsBefore.size(), todoGroups.size());
    }

    private TodoGroup createTodoGroup(String name) {
        TodoGroup todoGroup = new TodoGroup();
        todoGroup.setName(name);
        return todoGroup;
    }
}