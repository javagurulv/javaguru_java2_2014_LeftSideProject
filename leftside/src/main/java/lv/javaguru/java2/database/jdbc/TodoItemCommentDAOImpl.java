package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoItemCommentDAO;
import lv.javaguru.java2.domain.TodoItemComment;
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
 * Created by SM on 11/1/2014.
 */
public class TodoItemCommentDAOImpl extends DAOImpl implements TodoItemCommentDAO {
    private static DateTimeFormatter dateFormat = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss.0");

    @Override
    public void create(TodoItemComment itemComment) throws DBException {
        if (itemComment == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into todoItemComments values (default, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, itemComment.getUserId());
            preparedStatement.setLong(2, itemComment.getItemId());
            if (null != itemComment.getReplyToID()) {
                preparedStatement.setLong(3, itemComment.getReplyToID());
            } else {
                preparedStatement.setString(3, null);
            }
            if (null != itemComment.getDate()) {
                preparedStatement.setString(4, itemComment.getDate().toString(dateFormat));
            } else {
                preparedStatement.setString(4, null);
            }
            preparedStatement.setString(5, itemComment.getTitle());
            preparedStatement.setString(6, itemComment.getMessage());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                itemComment.setCommentId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute TodoItemCommentDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public TodoItemComment getById(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from todoItemComments where CommentID = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            TodoItemComment itemComment = null;
            if (resultSet.next()) {
                itemComment = parseResultSetRow(resultSet);
            }
            return itemComment;
        } catch (Throwable e) {
            System.out.println("Exception while execute TodoItemCommentDAOImpl.getById()");
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
                    .prepareStatement("delete from todoItemComments where CommentID = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute TodoItemCommentDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(TodoItemComment itemComment) throws DBException {
        if (itemComment == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update todoItemComments set UserID = ?, ItemID = ?, ReplyToID = ?, " +
                            "Date = ?, Title = ?, Message = ? " +
                            "where CommentID = ?");
            preparedStatement.setLong(1, itemComment.getUserId());
            preparedStatement.setLong(2, itemComment.getItemId());
            if (null != itemComment.getReplyToID()) {
                preparedStatement.setLong(3, itemComment.getReplyToID());
            } else {
                preparedStatement.setString(3, null);
            }
            if (null != itemComment.getDate()) {
                preparedStatement.setString(4, itemComment.getDate().toString(dateFormat));
            } else {
                preparedStatement.setString(4, null);
            }
            preparedStatement.setString(5, itemComment.getTitle());
            preparedStatement.setString(6, itemComment.getMessage());
            preparedStatement.setLong(7, itemComment.getCommentId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute TodoItemCommentDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<TodoItemComment> getAll() throws DBException {
        List<TodoItemComment> todoItems = new ArrayList<TodoItemComment>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from todoItemComments");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TodoItemComment todoItem = parseResultSetRow(resultSet);
                todoItems.add(todoItem);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list TodoItemCommentDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return todoItems;
    }

    @Override
    public List<TodoItemComment> getByUserId(Long userId) throws DBException {
        List<TodoItemComment> todoItems = new ArrayList<TodoItemComment>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from todoItemComments " +
                    "where UserID = ?");
            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TodoItemComment todoItem = parseResultSetRow(resultSet);
                todoItems.add(todoItem);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list TodoItemCommentDAOImpl.getByUserId()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return todoItems;
    }

    @Override
    public List<TodoItemComment> getByItemId(Long itemId) throws DBException {
        List<TodoItemComment> todoItems = new ArrayList<TodoItemComment>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from todoItemComments " +
                    "where ItemID = ?");
            preparedStatement.setLong(1, itemId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TodoItemComment todoItem = parseResultSetRow(resultSet);
                todoItems.add(todoItem);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list TodoItemCommentDAOImpl.getByItemId()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return todoItems;
    }

    private TodoItemComment parseResultSetRow(ResultSet resultSet) throws SQLException {
        TodoItemComment itemComment;
        itemComment = new TodoItemComment();
        itemComment.setCommentId(resultSet.getLong("CommentID"));
        itemComment.setUserId(resultSet.getLong("UserID"));
        itemComment.setItemId(resultSet.getLong("ItemID"));
        Object obj = resultSet.getObject("ReplyToID");
        if (null != obj) {
            itemComment.setReplyToID(((Integer) obj).longValue());
        }
        String dateString = resultSet.getString("Date");
        if (null != dateString) {
            itemComment.setDate(DateTime.parse(dateString, dateFormat));
        }
        itemComment.setTitle(resultSet.getString("Title"));
        itemComment.setMessage(resultSet.getString("Message"));
        return itemComment;
    }
}
