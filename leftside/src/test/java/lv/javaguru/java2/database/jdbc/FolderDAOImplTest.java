package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FolderDAO;
import lv.javaguru.java2.domain.Folder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FolderDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();
    private FolderDAO folderDAO = new FolderDAOImpl();

    @Before
    public void setUp() throws Exception {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws Exception {
        Folder folder = createFolder("F", "L");

        folderDAO.create(folder);

        Folder folderFromDB = folderDAO.getById(folder.getFolderId());
        assertNotNull(folderFromDB);
        assertEquals(folder.getFolderId(), folderFromDB.getFolderId());
        assertEquals(folder.getFolderName(), folderFromDB.getFolderName());
        assertEquals(folder.getParentFolderId(), folderFromDB.getParentFolderId());
        assertEquals(folder.getPath(), folderFromDB.getPath());
    }

    @Test
    public void testDelete() throws Exception {
        List<Folder> foldersBefore = folderDAO.getAll();
        Folder folder = createFolder("Test", "Test");
        folderDAO.create(folder);

        List<Folder> allFolders = folderDAO.getAll();
        assertEquals(allFolders.size(), 1 + foldersBefore.size());

        folderDAO.delete(folder.getFolderId());
        allFolders = folderDAO.getAll();
        assertEquals(allFolders.size(), foldersBefore.size());
    }

    @Test
    public void testUpdate() throws Exception {
        Folder expected = createFolder("QQQ", "AAA");
        folderDAO.create(expected);

        expected.setFolderName("ZZZ");
        folderDAO.update(expected);

        Folder actual = folderDAO.getById(expected.getFolderId());
        assertEquals(expected.getFolderName(), actual.getFolderName());
    }

    @Test
    public void testNonExistingFolderDoesNotExist() throws Exception {
        Folder folder = folderDAO.getById(123L);
        assertEquals(folder, null);
    }

    @Test
    public void testMultipleFolderCreation() throws DBException {
        List<Folder> foldersBefore = folderDAO.getAll();
        Folder folder1 = createFolder("F1", "P1");
        Folder folder2 = createFolder("F2", "P2");
        folderDAO.create(folder1);
        folderDAO.create(folder2);
        List<Folder> folders = folderDAO.getAll();
        assertEquals(2 + foldersBefore.size(), folders.size());
    }

    private Folder createFolder(String folderName, String folderPath) {
        Folder folder = new Folder();
        folder.setParentFolderId(null);
        folder.setFolderName(folderName);
        folder.setPath(folderPath);
        return folder;
    }
}