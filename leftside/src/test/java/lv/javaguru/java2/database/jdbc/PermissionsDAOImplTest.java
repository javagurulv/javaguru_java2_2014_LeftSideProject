package lv.javaguru.java2.database.jdbc;

import static org.junit.Assert.*;

import java.util.List;

import lv.javaguru.java2.database.PermissionsDAO;
import org.junit.Before;
import org.junit.Test;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Permissions;
/**
 * Created by Sergey on 19.10.14.
 */

public class PermissionsDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private PermissionsDAO permissionsDAO = new PermissionsDAOImpl();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }


    @Test
    public void testNonExistingPermissionDoesNotExist() throws Exception {

        Permissions permissions = permissionsDAO.getById(7777L);
        assertEquals(permissions, null);
    }


    @Test
    public void testDelete() throws Exception {

        Permissions permissions = createPermissions(234L,(byte) 2, true, true, true, true);
        permissionsDAO.create(permissions);

        List<Permissions> allPermissions = permissionsDAO.getAll();
        assertEquals(allPermissions.size(), 1);

        permissionsDAO.delete(permissions.getPermissionId());
        allPermissions = permissionsDAO.getAll();
        assertEquals(allPermissions.size(), 0);
    }

    @Test
    public void testUpdate() throws DBException {

        Permissions expected = createPermissions(23434L,(byte) 1, true, true, true, true);
        permissionsDAO.create(expected);

        expected.setItemId(123123L);
        permissionsDAO.update(expected);

        Permissions actual = permissionsDAO.getById(expected.getPermissionId());
        assertEquals(expected.getItemId(), actual.getItemId());
    }



    @Test
    public void testCreate() throws DBException {
        Permissions permissions = createPermissions(444L, (byte) 1, false, false, false, true);

        permissionsDAO.create(permissions);

        Permissions permissionsFromDB = permissionsDAO.getById(permissions.getPermissionId());
        assertNotNull(permissionsFromDB);
        assertEquals(permissions.getPermissionId(), permissionsFromDB.getPermissionId());
        assertEquals(permissions.getItemId(), permissionsFromDB.getItemId());
        assertEquals(permissions.getItemType(), permissionsFromDB.getItemType());
    }

    @Test
    public void testMultipleUserCreation() throws DBException {
        Permissions permissions1 = createPermissions(244L, (byte) 1, false, false, false, true);
        Permissions permissions2 = createPermissions(3444L, (byte) 2, true, false, false, true);
        permissionsDAO.create(permissions1);
        permissionsDAO.create(permissions2);
        List<Permissions> permissions = permissionsDAO.getAll();
        assertEquals(2, permissions.size());
    }



    private Permissions createPermissions( long itemID, byte itemType, boolean allowedReading,
                                          boolean allowedWriting, boolean allowedDeleting, boolean allowedUpdating) {
        Permissions permissions = new Permissions();
        permissions.setItemId(itemID);
        permissions.setItemType(itemType);
        permissions.setAllowedReading(allowedReading);
        permissions.setAllowedWriting(allowedWriting);
        permissions.setAllowedDeleting(allowedDeleting);
        permissions.setAllowedUpdating(allowedUpdating);
        return permissions;
    }
}
