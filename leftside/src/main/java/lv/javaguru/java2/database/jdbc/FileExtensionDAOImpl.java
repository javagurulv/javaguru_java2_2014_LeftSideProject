package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FileExtensionDAO;
import lv.javaguru.java2.domain.FileExtension;
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
@Component
public class FileExtensionDAOImpl extends DAOImpl implements FileExtensionDAO {
    private static String tableName = "fileExtensions";
    private static String keyFieldName = "ExtensionID";

    @Override
    public void create(FileExtension fileExtension) throws DBException {
        if (fileExtension == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into " + tableName + " " +
                            "values (default, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, fileExtension.getExtension());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                fileExtension.setExtensionId(rs.getByte(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute FileExtensionDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public FileExtension getById(byte id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from " + tableName + " where " + keyFieldName + " = ?");
            preparedStatement.setByte(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            FileExtension fileExtension = null;
            if (resultSet.next()) {
                fileExtension = parseResultSetRow(resultSet);
            }
            return fileExtension;
        } catch (Throwable e) {
            System.out.println("Exception while execute FileExtensionDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<FileExtension> getAll() throws DBException {
        List<FileExtension> fileExtensions = new ArrayList<FileExtension>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from " + tableName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                FileExtension fileExtension = parseResultSetRow(resultSet);
                fileExtensions.add(fileExtension);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list FileExtensionDAOImpl.getList()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return fileExtensions;
    }

    @Override
    public void delete(byte id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from " + tableName + " where " + keyFieldName + " = ?");
            preparedStatement.setByte(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute FileExtensionDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(FileExtension fileExtension) throws DBException {
        if (fileExtension == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update " + tableName + " set Extension = ? " +
                            "where " + keyFieldName + " = ?");
            preparedStatement.setString(1, fileExtension.getExtension());
            preparedStatement.setLong(2, fileExtension.getExtensionId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute FileExtensionDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    private FileExtension parseResultSetRow(ResultSet resultSet) throws SQLException {
        FileExtension fileExtension = new FileExtension();
        fileExtension.setExtensionId(resultSet.getByte(keyFieldName));
        fileExtension.setExtension(resultSet.getString("Extension"));
        return fileExtension;
    }
}
