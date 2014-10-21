package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.File;
import java.util.List;

/**
 * Created by SM on 10/18/2014.
 */
public interface FileDAO {

    void create(File user) throws DBException;

    File getById(Long id) throws DBException;

    void delete(Long id) throws DBException;

    void update(File user) throws DBException;

    List<File> getAll() throws DBException;

}
