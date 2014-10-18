package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FileExtensionDAO;
import lv.javaguru.java2.domain.FileExtension;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FileExtensionDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();
    private FileExtensionDAO fileExtensionDAO = new FileExtensionDAOImpl();

    @Before
    public void setUp() throws Exception {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws Exception {
        FileExtension fileExtension = createFileExtension("F");

        fileExtensionDAO.create(fileExtension);

        FileExtension fileExtensionFromDB = fileExtensionDAO.getById(fileExtension.getExtensionId());
        assertNotNull(fileExtensionFromDB);
        assertEquals(fileExtension.getExtensionId(), fileExtensionFromDB.getExtensionId());
        assertEquals(fileExtension.getExtension(), fileExtensionFromDB.getExtension());
    }

    @Test
    public void testDelete() throws Exception {
        FileExtension fileExtension = createFileExtension("Test");
        fileExtensionDAO.create(fileExtension);

        List<FileExtension> allFileExtensions = fileExtensionDAO.getAll();
        assertEquals(allFileExtensions.size(), 1);

        fileExtensionDAO.delete(fileExtension.getExtensionId());
        allFileExtensions = fileExtensionDAO.getAll();
        assertEquals(allFileExtensions.size(), 0);
    }

    @Test
    public void testUpdate() throws Exception {
        FileExtension expected = createFileExtension("QQQ");
        fileExtensionDAO.create(expected);

        expected.setExtension("ZZZ");
        fileExtensionDAO.update(expected);

        FileExtension actual = fileExtensionDAO.getById(expected.getExtensionId());
        assertEquals(expected.getExtension(), actual.getExtension());
    }

    @Test
    public void testNonExistingFileExtensionDoesNotExist() throws Exception {
        FileExtension fileExtension = fileExtensionDAO.getById((byte) 52);
        assertEquals(fileExtension, null);
    }

    @Test
    public void testMultipleFileExtensionCreation() throws DBException {
        FileExtension fileExtension1 = createFileExtension("F1");
        FileExtension fileExtension2 = createFileExtension("F2");
        fileExtensionDAO.create(fileExtension1);
        fileExtensionDAO.create(fileExtension2);
        List<FileExtension> fileExtensions = fileExtensionDAO.getAll();
        assertEquals(2, fileExtensions.size());
    }

    private FileExtension createFileExtension(String extension) {
        FileExtension fileExtension = new FileExtension();
        fileExtension.setExtension(extension);
        return fileExtension;
    }
}