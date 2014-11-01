package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.domain.TodoItem;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SM on 10/18/2014.
 */
public class TodoItemDAOImpl extends DAOImpl implements TodoItemDAO {
    private static DateTimeFormatter dateFormat = DateTimeFormat.forPattern("YYYY-MM-dd");
    private static String tableName = "todoItems";
    private static String keyFieldName = "ItemID";

    @Override
    public void create(TodoItem todoItem) throws DBException {
        if (todoItem == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into " + tableName + " " +
                            "values (default, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, todoItem.getStateId());
            preparedStatement.setString(2, todoItem.getTitle());
            preparedStatement.setString(3, todoItem.getDescription());
            if (null != todoItem.getDueDate()) {
                preparedStatement.setString(4, todoItem.getDueDate().toString(dateFormat));
            } else {
                preparedStatement.setString(4, null);
            }

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                todoItem.setItemId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute TodoItemDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public TodoItem getById(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from " + tableName + " where " + keyFieldName + " = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            TodoItem todoItem = null;
            if (resultSet.next()) {
                todoItem = parseResultSetRow(resultSet);
            }
            return todoItem;
        } catch (Throwable e) {
            System.out.println("Exception while execute TodoItemDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<TodoItem> getAll() throws DBException {
        List<TodoItem> todoItems = new ArrayList<TodoItem>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from " + tableName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TodoItem todoItem = parseResultSetRow(resultSet);
                todoItems.add(todoItem);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list TodoItemDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return todoItems;
    }

    @Override
    public List<TodoItem> getByUserId(Long userId) throws DBException {
        List<TodoItem> todoItems = new ArrayList<TodoItem>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from " + tableName + " " +
                    "where " + keyFieldName + " in (select ItemId from todoItemsToUsers where UserId = ?)");
            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TodoItem todoItem = parseResultSetRow(resultSet);
                todoItems.add(todoItem);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list TodoItemDAOImpl.getByUserId()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return todoItems;
    }

    @Override
    public List<TodoItem> getByTodoUserAndGroupId(Long userId, Long groupId) throws DBException {
        List<TodoItem> todoItems = new ArrayList<TodoItem>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from " + tableName + " " +
                    "where " + keyFieldName + " in (select ItemId from todoItemsToUsers where UserId = ?)" +
                    " and " + keyFieldName + " in (select ItemId from todoItemsToGroups where GroupId = ?)");
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, groupId
            );

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TodoItem todoItem = parseResultSetRow(resultSet);
                todoItems.add(todoItem);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list TodoItemDAOImpl.getByTodoUserAndGroupId()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return todoItems;
    }

    @Override
    public void setTodoGroup(Long todoItemId, Long todoGroupId) throws DBException {
        if (null == todoItemId) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into todoItemsToGroups (ItemID, GroupID) values (?, ?) " +
                            "on duplicate key update GroupID = ?", PreparedStatement.NO_GENERATED_KEYS);
            preparedStatement.setLong(1, todoItemId);
            preparedStatement.setLong(2, todoGroupId);
            preparedStatement.setLong(3, todoGroupId);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute TodoItemDAOImpl.setTodoGroup()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void setAuthor(Long todoItemId, Long userId) throws DBException {
        if (todoItemId == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into todoItemsToUsers (ItemID, UserID) values (?, ?) " +
                            "on duplicate key update UserID = ?", PreparedStatement.NO_GENERATED_KEYS);
            preparedStatement.setLong(1, todoItemId);
            preparedStatement.setLong(2, userId);
            preparedStatement.setLong(3, userId);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute TodoItemDAOImpl.setAuthor()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from " + tableName + " where " + keyFieldName + " = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute TodoItemDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(TodoItem todoItem) throws DBException {
        if (todoItem == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update " + tableName + " set StateID = ?, Title = ?, Description = ?, DueDate = ? " +
                            "where " + keyFieldName + " = ?");
            preparedStatement.setLong(1, todoItem.getStateId());
            preparedStatement.setString(2, todoItem.getTitle());
            preparedStatement.setString(3, todoItem.getDescription());
            if (null != todoItem.getDueDate()) {
                preparedStatement.setString(4, todoItem.getDueDate().toString(dateFormat));
            } else {
                preparedStatement.setString(4, null);
            }
            preparedStatement.setLong(5, todoItem.getItemId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute TodoItemDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    private TodoItem parseResultSetRow(ResultSet resultSet) throws SQLException {
        TodoItem todoItem = new TodoItem();
        todoItem.setItemId(resultSet.getLong(keyFieldName));
        todoItem.setStateId(resultSet.getLong("StateID"));
        todoItem.setTitle(resultSet.getString("Title"));
        todoItem.setDescription(resultSet.getString("Description"));
        String dateString = resultSet.getString("DueDate");
        if (null != dateString) {
            todoItem.setDueDate(DateTime.parse(dateString, dateFormat));
        }
        return todoItem;
    }
}
