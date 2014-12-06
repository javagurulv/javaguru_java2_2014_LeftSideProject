package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FileDAO;
import lv.javaguru.java2.domain.File;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by SM on 12/6/2014.
 */
@Component("ORM_FileDAO")
public class FileDAOImpl implements FileDAO {
    @Override
    public void create(File user) throws DBException {

    }

    @Override
    public File getById(Long id) throws DBException {
        return null;
    }

    @Override
    public void delete(Long id) throws DBException {

    }

    @Override
    public void update(File user) throws DBException {

    }

    @Override
    public List<File> getAll() throws DBException {
        return null;
    }
}
