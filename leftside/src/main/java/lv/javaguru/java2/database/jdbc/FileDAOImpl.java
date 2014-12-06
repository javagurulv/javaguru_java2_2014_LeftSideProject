package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FileDAO;
import lv.javaguru.java2.domain.File;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SM on 10/18/2014.
 */
@Component("JDBC_FileDAO")
public class FileDAOImpl extends DAOImpl implements FileDAO {
    private static DateTimeFormatter dateFormat = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss.0");
    private static String tableName = "files";
    private static String keyFieldName = "FileID";

    @Override
    public void create(File file) throws DBException {
        if (file == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into " + tableName + " " +
                            "values (default, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, file.getPath());
            preparedStatement.setString(2, file.getFileName());
            if (null != file.getExtensionId()) {
                preparedStatement.setByte(3, file.getExtensionId());
            } else {
                preparedStatement.setString(3, null);
            }
            preparedStatement.setLong(4, file.getTodoItemID());
            preparedStatement.setString(5, file.getUploadDate().toString(dateFormat));

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                file.setFileId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute FileDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public File getById(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from " + tableName + " where " + keyFieldName + " = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            File file = null;
            if (resultSet.next()) {
                file = parseResultSetRow(resultSet);
            }
            return file;
        } catch (Throwable e) {
            System.out.println("Exception while execute FileDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<File> getAll() throws DBException {
        List<File> files = new ArrayList<File>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from " + tableName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                File file = parseResultSetRow(resultSet);
                files.add(file);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list FileDAOImpl.getList()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return files;
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
            System.out.println("Exception while execute FileDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(File file) throws DBException {
        if (file == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update " + tableName + " set Path = ?, FileName = ?, " +
                            "ExtensionID = ?, TodoItemID = ?, UploadDate = ? " +
                            "where " + keyFieldName + " = ?");
            preparedStatement.setString(1, file.getPath());
            preparedStatement.setString(2, file.getFileName());
            if (null != file.getExtensionId()) {
                preparedStatement.setByte(3, file.getExtensionId());
            } else {
                preparedStatement.setString(3, null);
            }
            preparedStatement.setLong(4, file.getTodoItemID());
            preparedStatement.setString(5, file.getUploadDate().toString(dateFormat));
            preparedStatement.setLong(6, file.getFileId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute FileDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    private File parseResultSetRow(ResultSet resultSet) throws SQLException {
        File file = new File();
        file.setFileId(resultSet.getLong(keyFieldName));
        file.setPath(resultSet.getString("Path"));
        file.setFileName(resultSet.getString("FileName"));
        Object obj = resultSet.getObject("ExtensionID");
        if (null != obj) {
            file.setExtensionId(((Integer) obj).byteValue());
        }
        file.setTodoItemID(resultSet.getLong("TodoItemID"));
        String dateString = resultSet.getString("UploadDate");
        if (null != dateString) {
            file.setUploadDate(DateTime.parse(dateString, dateFormat));
        }
        return file;
    }
}
