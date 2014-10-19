package lv.javaguru.java2.database.jdbc;

import static org.junit.Assert.*;

import java.util.List;

import lv.javaguru.java2.database.PermissionsDAO;
import org.junit.Before;
import org.junit.Test;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Permission;
/**
 * Created by Sergey on 19.10.14.
 */

public class PermissionDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private PermissionsDAO permissionsDAO = new PermissionsDAOImpl();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }


    @Test
    public void testNonExistingPermissionDoesNotExist() throws Exception {

        Permission permission = permissionsDAO.getById(7777L);
        assertEquals(permission, null);
    }


    @Test
    public void testDelete() throws Exception {
        List<Permission> permissionsBefore = permissionsDAO.getAll();
        Permission permission = createPermissions(234L,(byte) 2, true, true, true, true);
        permissionsDAO.create(permission);

        List<Permission> allPermissions = permissionsDAO.getAll();
        assertEquals(allPermissions.size(), 1 + permissionsBefore.size());

        permissionsDAO.delete(permission.getPermissionId());
        allPermissions = permissionsDAO.getAll();
        assertEquals(allPermissions.size(), permissionsBefore.size());
    }

    @Test
    public void testUpdate() throws DBException {

        Permission expected = createPermissions(23434L,(byte) 1, true, true, true, true);
        permissionsDAO.create(expected);

        expected.setItemId(123123L);
        permissionsDAO.update(expected);

        Permission actual = permissionsDAO.getById(expected.getPermissionId());
        assertEquals(expected.getItemId(), actual.getItemId());
    }



    @Test
    public void testCreate() throws DBException {
        Permission permission = createPermissions(444L, (byte) 1, false, false, false, true);

        permissionsDAO.create(permission);

        Permission permissionsFromDB = permissionsDAO.getById(permission.getPermissionId());
        assertNotNull(permissionsFromDB);
        assertEquals(permission.getPermissionId(), permissionsFromDB.getPermissionId());
        assertEquals(permission.getItemId(), permissionsFromDB.getItemId());
        assertEquals(permission.getItemType(), permissionsFromDB.getItemType());
    }

    @Test
    public void testMultiplePermissionCreation() throws DBException {
        List<Permission> permissionsBefore = permissionsDAO.getAll();
        Permission permission1 = createPermissions(244L, (byte) 1, false, false, false, true);
        Permission permission2 = createPermissions(3444L, (byte) 2, true, false, false, true);
        permissionsDAO.create(permission1);
        permissionsDAO.create(permission2);
        List<Permission> permissions = permissionsDAO.getAll();
        assertEquals(2 + permissionsBefore.size(), permissions.size());
    }



    private Permission createPermissions( long itemID, byte itemType, boolean allowedReading,
                                          boolean allowedWriting, boolean allowedDeleting, boolean allowedUpdating) {
        Permission permission = new Permission();
        permission.setItemId(itemID);
        permission.setItemType(itemType);
        permission.setAllowedReading(allowedReading);
        permission.setAllowedWriting(allowedWriting);
        permission.setAllowedDeleting(allowedDeleting);
        permission.setAllowedUpdating(allowedUpdating);
        return permission;
    }
}
