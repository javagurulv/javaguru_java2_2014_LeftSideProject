package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.PermissionsDAO;
import lv.javaguru.java2.domain.Permission;

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
    public void create(Permission permission) throws DBException {
        if (permission == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into PERMISSIONS values (default, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, permission.getItemId());
            preparedStatement.setByte(2, permission.getItemType());
            preparedStatement.setBoolean(3, permission.isAllowedReading());
            preparedStatement.setBoolean(4, permission.isAllowedWriting());
            preparedStatement.setBoolean(5, permission.isAllowedDeleting());
            preparedStatement.setBoolean(6, permission.isAllowedUpdating());


            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                permission.setPermissionId(rs.getLong(1));
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
    public Permission getById(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from PERMISSIONS where PermissionID = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Permission permission = null;
            if (resultSet.next()) {
                permission = new Permission();
                permission.setPermissionId(resultSet.getLong("PermissionID"));
                permission.setItemId(resultSet.getLong("ItemID"));
                permission.setItemType(resultSet.getByte("ItemType"));
                permission.setAllowedReading(resultSet.getBoolean("AllowedReading"));
                permission.setAllowedWriting(resultSet.getBoolean("AllowedWriting"));
                permission.setAllowedDeleting(resultSet.getBoolean("AllowedDeleting"));
                permission.setAllowedUpdating(resultSet.getBoolean("AllowedUpdating"));
            }
            return permission;
        } catch (Throwable e) {
            System.out.println("Exception while execute PermissionsDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<Permission> getAll() throws DBException {
        List<Permission> permissionsList = new ArrayList<Permission>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from PERMISSIONS");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Permission permission = new Permission();
                permission.setPermissionId(resultSet.getLong("PermissionID"));
                permission.setItemId(resultSet.getLong("ItemID"));
                permission.setItemType(resultSet.getByte("ItemType"));
                permission.setAllowedReading(resultSet.getBoolean("AllowedReading"));
                permission.setAllowedWriting(resultSet.getBoolean("AllowedWriting"));
                permission.setAllowedDeleting(resultSet.getBoolean("AllowedDeleting"));
                permission.setAllowedUpdating(resultSet.getBoolean("AllowedUpdating"));
                permissionsList.add(permission);
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
    public void update(Permission permission) throws DBException {
        if (permission == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update PERMISSIONS set ItemID = ?, ItemType = ? , AllowedReading = ?, AllowedWriting = ?,  " +
                            "AllowedDeleting = ?, AllowedUpdating = ? " +
                            "where PermissionID = ?");
            preparedStatement.setLong(1, permission.getItemId());
            preparedStatement.setByte(2, permission.getItemType());
            preparedStatement.setBoolean(3, permission.isAllowedReading());
            preparedStatement.setBoolean(4, permission.isAllowedWriting());
            preparedStatement.setBoolean(5, permission.isAllowedDeleting());
            preparedStatement.setBoolean(6, permission.isAllowedUpdating());
            preparedStatement.setLong(7, permission.getPermissionId());
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
