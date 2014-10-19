package lv.javaguru.java2.database.jdbc;

import static org.junit.Assert.*;


import java.util.List;

import lv.javaguru.java2.database.AccessGroupDAO;
import org.junit.Before;
import org.junit.Test;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.AccessGroup;
/**
 * Created by Sergey on 19.10.14.
 */
public class AccessGroupDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private AccessGroupDAO accessGroupDAO = new AccessGroupDAOImpl();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }


    @Test
    public void testNonExistingAccessGroupDoesNotExist() throws Exception {

        AccessGroup accessGroup = accessGroupDAO.getById(333L);
        assertEquals(accessGroup, null);
    }


    @Test
    public void testDelete() throws Exception {

        AccessGroup accessGroup = createAccessGroup("AAA", 99L, 22L);
        accessGroupDAO.create(accessGroup);

        List<AccessGroup> allAccessGroups = accessGroupDAO.getAll();
        assertEquals(allAccessGroups.size(), 1);

        accessGroupDAO.delete(accessGroup.getAccessGroupId());
        allAccessGroups = accessGroupDAO.getAll();
        assertEquals(allAccessGroups.size(), 0);
    }

    @Test
    public void testUpdate() throws DBException {

        AccessGroup expected = createAccessGroup("BBB", 222L, 554L);
        accessGroupDAO.create(expected);

        expected.setAccessGroupName("CCCC");
        accessGroupDAO.update(expected);

        AccessGroup actual = accessGroupDAO.getById(expected.getAccessGroupId());
        assertEquals(expected.getAccessGroupName(), actual.getAccessGroupName());
    }



    @Test
    public void testCreate() throws DBException {
        AccessGroup accessGroup = createAccessGroup("NNN", 22122L, 52354L);

        accessGroupDAO.create(accessGroup);

        AccessGroup accessGroupFromDB = accessGroupDAO.getById(accessGroup.getAccessGroupId());
        assertNotNull(accessGroupFromDB);
        assertEquals(accessGroup.getAccessGroupId(), accessGroupFromDB.getAccessGroupId());
        assertEquals(accessGroup.getAccessGroupName(), accessGroupFromDB.getAccessGroupName());
        assertEquals(accessGroup.getUserId(), accessGroupFromDB.getUserId());
    }

    @Test
    public void testMultipleUserCreation() throws DBException {
        AccessGroup accessGroup1 = createAccessGroup("PPPP", 212322L, 55433L);
        AccessGroup accessGroup2 = createAccessGroup("TTTT", 222111L, 554112L);
        accessGroupDAO.create(accessGroup1);
        accessGroupDAO.create(accessGroup2);
        List<AccessGroup> accessGroup = accessGroupDAO.getAll();
        assertEquals(2, accessGroup.size());
    }



    private AccessGroup createAccessGroup(String accessGroupName, long userId, long permissionId) {
            AccessGroup accessGroup = new AccessGroup();
            accessGroup.setAccessGroupName(accessGroupName);
            accessGroup.setUserId(userId);
            accessGroup.setAccessGroupId(permissionId);
            return accessGroup;
        }

    }