package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FileDAO;
import lv.javaguru.java2.database.FileExtensionDAO;
import lv.javaguru.java2.database.FolderDAO;
import lv.javaguru.java2.domain.File;
import lv.javaguru.java2.domain.FileExtension;
import lv.javaguru.java2.domain.Folder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FileDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();
    private FileDAO fileDAO = new FileDAOImpl();
    private FileExtension generalFileExtension;
    private Folder rootFolder;


    @Before
    public void setUp() throws Exception {
        FileExtensionDAO fileExtensionDAO = new FileExtensionDAOImpl();
        FolderDAO folderDAO = new FolderDAOImpl();

        databaseCleaner.cleanDatabase();

        this.rootFolder = folderDAO.getById(2l);

        this.generalFileExtension = fileExtensionDAO.getById((byte) 1);
    }

    @Test
    public void testCreate() throws Exception {
        File file = createFile("F");

        fileDAO.create(file);

        File fileFromDB = fileDAO.getById(file.getFileId());
        assertNotNull(fileFromDB);
        assertEquals(file.getFileId(), fileFromDB.getFileId());
        assertEquals(file.getFileName(), fileFromDB.getFileName());
        assertEquals(file.getParentFolderId(), fileFromDB.getParentFolderId());
        assertEquals(file.getExtensionId(), fileFromDB.getExtensionId());
    }

    @Test
    public void testDelete() throws Exception {
        List<File> filesBefore = fileDAO.getAll();

        File file = createFile("Test0");
        fileDAO.create(file);

        List<File> allFiles = fileDAO.getAll();
        assertEquals(allFiles.size(), 1 + filesBefore.size());

        fileDAO.delete(file.getFileId());
        allFiles = fileDAO.getAll();
        assertEquals(allFiles.size(), filesBefore.size());
    }

    @Test
    public void testUpdate() throws Exception {
        File expected = createFile("QQQ");
        fileDAO.create(expected);

        expected.setFileName("ZZZ");
        fileDAO.update(expected);

        File actual = fileDAO.getById(expected.getFileId());
        assertEquals(expected.getFileName(), actual.getFileName());
    }

    @Test
    public void testNonExistingFileDoesNotExist() throws Exception {
        File file = fileDAO.getById(123L);
        assertEquals(file, null);
    }

    @Test
    public void testMultipleFileCreation() throws DBException {
        List<File> filesBefore = fileDAO.getAll();
        File file1 = createFile("F1");
        File file2 = createFile("F2");
        fileDAO.create(file1);
        fileDAO.create(file2);
        List<File> filesAfter = fileDAO.getAll();
        assertEquals(2 + filesBefore.size(), filesAfter.size());
    }

    @Test
    public void testEmptyExtensionFileCreation() throws DBException {
        File file = createFile("F1");
        file.setExtensionId(null);
        fileDAO.create(file);

        File fileFromDB = fileDAO.getById(file.getFileId());
        assertNotNull(fileFromDB);
        assertEquals(file.getFileId(), fileFromDB.getFileId());
        assertEquals(file.getFileName(), fileFromDB.getFileName());
        assertEquals(file.getParentFolderId(), fileFromDB.getParentFolderId());
        assertEquals(file.getExtensionId(), fileFromDB.getExtensionId());
    }

    private File createFile(String fileName) {
        File file = new File();
        file.setParentFolderId(rootFolder.getFolderId());
        file.setExtensionId(generalFileExtension.getExtensionId());
        file.setFileName(fileName);
        return file;
    }
}