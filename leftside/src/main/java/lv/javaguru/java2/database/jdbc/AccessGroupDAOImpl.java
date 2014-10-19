package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.AccessGroupDAO;
import lv.javaguru.java2.domain.AccessGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 19.10.14.
 */
public class AccessGroupDAOImpl extends DAOImpl implements AccessGroupDAO{

    @Override
    public void create(AccessGroup accessGroup) throws DBException {
        if (accessGroup == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into ACCESSGROUPS values (default, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, accessGroup.getAccessGroupName());
            preparedStatement.setLong(2, accessGroup.getUserId());
            preparedStatement.setLong(3, accessGroup.getPermissionId());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                accessGroup.setAccessGroupId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute AccessGroupDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public AccessGroup getById(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from ACCESSGROUPS where accessGroupID = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            AccessGroup accessGroup = null;
            if (resultSet.next()) {
                accessGroup = new AccessGroup();
                accessGroup.setAccessGroupId(resultSet.getLong("accessGroupID"));
                accessGroup.setAccessGroupName(resultSet.getString("accessGroupName"));
                accessGroup.setUserId(resultSet.getLong("userID"));
                accessGroup.setPermissionId(resultSet.getLong("permissionID"));

            }
            return accessGroup;
        } catch (Throwable e) {
            System.out.println("Exception while execute AccessGroupDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<AccessGroup> getAll() throws DBException {
        List<AccessGroup> accessGroups = new ArrayList<AccessGroup>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ACCESSGROUPS");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AccessGroup accessGroup = new AccessGroup();
                accessGroup.setAccessGroupId(resultSet.getLong("accessGroupID"));
                accessGroup.setAccessGroupName(resultSet.getString("accessGroupName"));
                accessGroup.setUserId(resultSet.getLong("userID"));
                accessGroup.setPermissionId(resultSet.getLong("permissionID"));
                accessGroups.add(accessGroup);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list AccessGroupDAOImpl.getList()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return accessGroups;
    }

    @Override
    public void delete(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from ACCESSGROUPS where accessGroupID = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute AccessGroupDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(AccessGroup accessGroup) throws DBException {
        if (accessGroup == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update ACCESSGROUPS set accessGroupName = ?, userID = ?, permissionID = ? " +
                            "where accessGroupID = ?");
            preparedStatement.setString(1, accessGroup.getAccessGroupName());
            preparedStatement.setLong(2, accessGroup.getUserId());
            preparedStatement.setLong(3, accessGroup.getPermissionId());
            preparedStatement.setLong(4, accessGroup.getAccessGroupId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }
}
