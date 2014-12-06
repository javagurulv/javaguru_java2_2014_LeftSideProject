package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FileExtensionDAO;
import lv.javaguru.java2.domain.FileExtension;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by SM on 12/6/2014.
 */
@Component("ORM_FileExtensionDAO")
public class FileExtensionDAOImpl implements FileExtensionDAO {
    @Override
    public void create(FileExtension user) throws DBException {

    }

    @Override
    public FileExtension getById(byte id) throws DBException {
        return null;
    }

    @Override
    public void delete(byte id) throws DBException {

    }

    @Override
    public void update(FileExtension user) throws DBException {

    }

    @Override
    public List<FileExtension> getAll() throws DBException {
        return null;
    }
}
