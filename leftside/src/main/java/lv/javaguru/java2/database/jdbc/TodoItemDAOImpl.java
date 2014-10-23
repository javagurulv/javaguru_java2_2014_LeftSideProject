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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SM on 10/18/2014.
 */
public class TodoItemDAOImpl extends DAOImpl implements TodoItemDAO {

    private static DateTimeFormatter dateFormat = DateTimeFormat.forPattern("YYYY-MM-dd");

    @Override
    public void create(TodoItem todoItem) throws DBException {
        if (todoItem == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into TODOITEMS values (default, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, todoItem.getStateId());
            preparedStatement.setString(2, todoItem.getCaption());
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
                    .prepareStatement("select * from TODOITEMS where ItemID = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            TodoItem todoItem = null;
            if (resultSet.next()) {
                todoItem = new TodoItem();
                todoItem.setItemId(resultSet.getLong("ItemID"));
                todoItem.setStateId(resultSet.getLong("StateID"));
                todoItem.setCaption(resultSet.getString("Caption"));
                todoItem.setDescription(resultSet.getString("Description"));
                String dateString = resultSet.getString("DueDate");
                if (null != dateString) {
                    todoItem.setDueDate(DateTime.parse(dateString, dateFormat));
                }
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
            PreparedStatement preparedStatement = connection.prepareStatement("select * from TODOITEMS");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TodoItem todoItem = new TodoItem();
                todoItem.setItemId(resultSet.getLong("ItemID"));
                todoItem.setStateId(resultSet.getLong("StateID"));
                todoItem.setCaption(resultSet.getString("Caption"));
                todoItem.setDescription(resultSet.getString("Description"));
                String dateString = resultSet.getString("DueDate");
                if (null != dateString) {
                    todoItem.setDueDate(DateTime.parse(dateString, dateFormat));
                }
                todoItems.add(todoItem);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list TodoItemDAOImpl.getList()");
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
            PreparedStatement preparedStatement = connection.prepareStatement("select * from todoItems " +
                    "where ItemId in (select ItemId from todoItemsToUsers where UserId = ?)");
            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TodoItem todoItem = new TodoItem();
                todoItem.setItemId(resultSet.getLong("ItemID"));
                todoItem.setStateId(resultSet.getLong("StateID"));
                todoItem.setCaption(resultSet.getString("Caption"));
                todoItem.setDescription(resultSet.getString("Description"));
                String dateString = resultSet.getString("DueDate");
                if (null != dateString) {
                    todoItem.setDueDate(DateTime.parse(dateString, dateFormat));
                }
                todoItems.add(todoItem);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list TodoItemDAOImpl.getList()");
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
            PreparedStatement preparedStatement = connection.prepareStatement("select * from todoItems " +
                    "where ItemId in (select ItemId from todoItemsToUsers where UserId = ?)" +
                    " and ItemId in (select ItemId from todoItemsToGroups where GroupId = ?)");
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, groupId
            );

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TodoItem todoItem = new TodoItem();
                todoItem.setItemId(resultSet.getLong("ItemID"));
                todoItem.setStateId(resultSet.getLong("StateID"));
                todoItem.setCaption(resultSet.getString("Caption"));
                todoItem.setDescription(resultSet.getString("Description"));
                String dateString = resultSet.getString("DueDate");
                if (null != dateString) {
                    todoItem.setDueDate(DateTime.parse(dateString, dateFormat));
                }
                todoItems.add(todoItem);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list TodoItemDAOImpl.getList()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return todoItems;
    }

    @Override
    public void delete(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from TODOITEMS where ItemID = ?");
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
                    .prepareStatement("update TODOITEMS set StateID = ?, Caption = ?, Description = ?, DueDate = ? " +
                            "where ItemID = ?");
            preparedStatement.setLong(1, todoItem.getStateId());
            preparedStatement.setString(2, todoItem.getCaption());
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

}
