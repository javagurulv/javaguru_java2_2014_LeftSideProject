package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.PermissionsDAO;
import lv.javaguru.java2.domain.Permissions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 19.10.14.
 */
public class PermissionsDAOImpl extends DAOImpl implements PermissionsDAO {

    @Override
    public void create(Permissions permissions) throws DBException {
        if (permissions == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into PERMISSIONS values (default, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, permissions.getItemId());
            preparedStatement.setByte(2, permissions.getItemType());
            preparedStatement.setBoolean(3, permissions.isAllowedReading());
            preparedStatement.setBoolean(4, permissions.isAllowedWriting());
            preparedStatement.setBoolean(5, permissions.isAllowedDeleting());
            preparedStatement.setBoolean(6, permissions.isAllowedUpdating());


            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                permissions.setPermissionId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute PermissionsDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public Permissions getById(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from PERMISSIONS where PermissionID = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Permissions permissions = null;
            if (resultSet.next()) {
                permissions = new Permissions();
                permissions.setPermissionId(resultSet.getLong("PermissionID"));
                permissions.setItemId(resultSet.getLong("ItemID"));
                permissions.setItemType(resultSet.getByte("ItemType"));
                permissions.setAllowedReading(resultSet.getBoolean("AllowedReading"));
                permissions.setAllowedWriting(resultSet.getBoolean("AllowedWriting"));
                permissions.setAllowedDeleting(resultSet.getBoolean("AllowedDeleting"));
                permissions.setAllowedUpdating(resultSet.getBoolean("AllowedUpdating"));
            }
            return permissions;
        } catch (Throwable e) {
            System.out.println("Exception while execute PermissionsDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<Permissions> getAll() throws DBException {
        List<Permissions> permissionsList = new ArrayList<Permissions>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from PERMISSIONS");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Permissions permissions = new Permissions();
                permissions.setPermissionId(resultSet.getLong("PermissionID"));
                permissions.setItemId(resultSet.getLong("ItemID"));
                permissions.setItemType(resultSet.getByte("ItemType"));
                permissions.setAllowedReading(resultSet.getBoolean("AllowedReading"));
                permissions.setAllowedWriting(resultSet.getBoolean("AllowedWriting"));
                permissions.setAllowedDeleting(resultSet.getBoolean("AllowedDeleting"));
                permissions.setAllowedUpdating(resultSet.getBoolean("AllowedUpdating"));
                permissionsList.add(permissions);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list PermissionsDAOImpl.getList()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return permissionsList;
    }

    @Override
    public void delete(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from PERMISSIONS where PermissionID = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute PermissionsDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Permissions permissions) throws DBException {
        if (permissions == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update PERMISSIONS set ItemID = ?, ItemType = ? , AllowedReading = ?, AllowedWriting = ?,  " +
                            "AllowedDeleting = ?, AllowedUpdating = ?" +
                            "where PermissionID = ?");
            preparedStatement.setLong(1, permissions.getItemId());
            preparedStatement.setByte(2, permissions.getItemType());
            preparedStatement.setBoolean(3, permissions.isAllowedReading());
            preparedStatement.setBoolean(4, permissions.isAllowedWriting());
            preparedStatement.setBoolean(5, permissions.isAllowedDeleting());
            preparedStatement.setBoolean(6, permissions.isAllowedUpdating());
            preparedStatement.setLong(7, permissions.getPermissionId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute PermissionsDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }
}
