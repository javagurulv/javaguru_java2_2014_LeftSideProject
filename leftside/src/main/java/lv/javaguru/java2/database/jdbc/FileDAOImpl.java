package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FileDAO;
import lv.javaguru.java2.domain.File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by file on 10/18/2014.
 */
public class FileDAOImpl extends DAOImpl implements FileDAO {

    @Override
    public void create(File file) throws DBException {
        if (file == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into FILES values (default, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, file.getParentFolderId());
            preparedStatement.setString(2, file.getFileName());
            preparedStatement.setByte(3, file.getExtensionId());

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
                    .prepareStatement("select * from FILES where FileID = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            File file = null;
            if (resultSet.next()) {
                file = new File();
                file.setFileId(resultSet.getLong("FileID"));
                file.setParentFolderId(resultSet.getLong("ParentFolderID"));
                file.setFileName(resultSet.getString("FileName"));
                file.setExtensionId(resultSet.getByte("ExtensionID"));
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
            PreparedStatement preparedStatement = connection.prepareStatement("select * from FILES");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                File file = new File();
                file.setFileId(resultSet.getLong("FileID"));
                file.setParentFolderId(resultSet.getLong("ParentFolderID"));
                file.setFileName(resultSet.getString("FileName"));
                file.setExtensionId(resultSet.getByte("ExtensionID"));
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
                    .prepareStatement("delete from FILES where FileID = ?");
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
                    .prepareStatement("update FILES set ParentFolderID = ?, FileName = ?, ExtensionID = ? " +
                            "where FileID = ?");
            preparedStatement.setLong(1, file.getParentFolderId());
            preparedStatement.setString(2, file.getFileName());
            preparedStatement.setByte(3, file.getExtensionId());
            preparedStatement.setLong(4, file.getFileId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute FileDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

}
