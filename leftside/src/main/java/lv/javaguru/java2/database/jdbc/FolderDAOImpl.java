package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FolderDAO;
import lv.javaguru.java2.domain.Folder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SM on 10/18/2014.
 */
public class FolderDAOImpl extends DAOImpl implements FolderDAO {

    @Override
    public void create(Folder folder) throws DBException {
        if (folder == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into FOLDERS values (default, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            if (null != folder.getParentFolderId()) {
                preparedStatement.setLong(1, folder.getParentFolderId());
            } else {
                preparedStatement.setString(1, null);
            }
            preparedStatement.setString(2, folder.getFolderName());
            preparedStatement.setString(3, folder.getPath());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                folder.setFolderId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute FolderDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public Folder getById(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from FOLDERS where FolderID = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Folder folder = null;
            if (resultSet.next()) {
                folder = new Folder();
                folder.setFolderId(resultSet.getLong("FolderID"));
                Object obj = resultSet.getObject("ParentFolderID");
                if (null != obj) {
                    folder.setParentFolderId(((Integer) obj).longValue());
                }
                folder.setFolderName(resultSet.getString("FolderName"));
                folder.setPath(resultSet.getString("Path"));
            }
            return folder;
        } catch (Throwable e) {
            System.out.println("Exception while execute FolderDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<Folder> getAll() throws DBException {
        List<Folder> folders = new ArrayList<Folder>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from FOLDERS");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Folder folder = new Folder();
                folder.setFolderId(resultSet.getLong("FolderID"));
                Object obj = resultSet.getObject("ParentFolderID");
                if (null != obj) {
                    folder.setParentFolderId(((Integer) obj).longValue());
                }
                folder.setFolderName(resultSet.getString("FolderName"));
                folder.setPath(resultSet.getString("Path"));
                folders.add(folder);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list FolderDAOImpl.getList()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return folders;
    }

    @Override
    public void delete(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from FOLDERS where FolderID = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute FolderDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Folder folder) throws DBException {
        if (folder == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update FOLDERS set ParentFolderID = ?, FolderName = ? , Path = ? " +
                            "where FolderID = ?");
            if (null != folder.getParentFolderId()) {
                preparedStatement.setLong(1, folder.getParentFolderId());
            } else {
                preparedStatement.setString(1, null);
            }
            preparedStatement.setString(2, folder.getFolderName());
            preparedStatement.setString(3, folder.getPath());
            preparedStatement.setLong(4, folder.getFolderId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute FolderDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

}
