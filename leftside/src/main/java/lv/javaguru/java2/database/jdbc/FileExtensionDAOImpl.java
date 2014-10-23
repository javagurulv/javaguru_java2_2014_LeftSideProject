package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FileExtensionDAO;
import lv.javaguru.java2.domain.FileExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SM on 10/18/2014.
 */
public class FileExtensionDAOImpl extends DAOImpl implements FileExtensionDAO {

    @Override
    public void create(FileExtension fileExtension) throws DBException {
        if (fileExtension == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into FileExtensions values (default, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
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
                    .prepareStatement("select * from FileExtensions where ExtensionID = ?");
            preparedStatement.setByte(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            FileExtension fileExtension = null;
            if (resultSet.next()) {
                fileExtension = new FileExtension();
                fileExtension.setExtensionId(resultSet.getByte("ExtensionID"));
                fileExtension.setExtension(resultSet.getString("Extension"));
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
            PreparedStatement preparedStatement = connection.prepareStatement("select * from FileExtensions");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                FileExtension fileExtension = new FileExtension();
                fileExtension.setExtensionId(resultSet.getByte("ExtensionID"));
                fileExtension.setExtension(resultSet.getString("Extension"));
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
                    .prepareStatement("delete from FileExtensions where ExtensionID = ?");
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
                    .prepareStatement("update FileExtensions set Extension = ? " +
                            "where ExtensionID = ?");
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

}
