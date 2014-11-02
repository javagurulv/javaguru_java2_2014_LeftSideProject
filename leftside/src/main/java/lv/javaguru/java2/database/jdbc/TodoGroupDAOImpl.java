package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TodoGroupDAO;
import lv.javaguru.java2.domain.TodoGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SM on 10/18/2014.
 */
public class TodoGroupDAOImpl extends DAOImpl implements TodoGroupDAO {
    private static String tableName = "todoGroups";
    private static String keyFieldName = "GroupID";

    @Override
    public void create(TodoGroup todoGroup) throws DBException {
        if (todoGroup == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into " + tableName + " " +
                            "values (default, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, todoGroup.getName());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                todoGroup.setGroupId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute TodoGroupDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public TodoGroup getById(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from " + tableName + " where " + keyFieldName + " = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            TodoGroup todoGroup = null;
            if (resultSet.next()) {
                todoGroup = parseResultSetRow(resultSet);
            }
            return todoGroup;
        } catch (Throwable e) {
            System.out.println("Exception while execute TodoGroupDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<TodoGroup> getAll() throws DBException {
        List<TodoGroup> todoGroups = new ArrayList<TodoGroup>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from " + tableName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TodoGroup todoGroup = parseResultSetRow(resultSet);
                todoGroups.add(todoGroup);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list TodoGroupDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return todoGroups;
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
            System.out.println("Exception while execute TodoGroupDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(TodoGroup todoGroup) throws DBException {
        if (todoGroup == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update " + tableName + " set Name = ? " +
                            "where " + keyFieldName + " = ?");
            preparedStatement.setString(1, todoGroup.getName());
            preparedStatement.setLong(2, todoGroup.getGroupId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute TodoGroupDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }


    private TodoGroup parseResultSetRow(ResultSet resultSet) throws SQLException {
        TodoGroup todoGroup = new TodoGroup();
        todoGroup.setGroupId(resultSet.getLong(keyFieldName));
        todoGroup.setName(resultSet.getString("Name"));
        return todoGroup;
    }
}
