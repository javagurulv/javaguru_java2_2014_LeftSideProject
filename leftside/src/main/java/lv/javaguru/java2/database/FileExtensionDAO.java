package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.FileExtension;

import java.util.List;

/**
 * Created by SM on 10/18/2014.
 */
public interface FileExtensionDAO {

    void create(FileExtension user) throws DBException;

    FileExtension getById(byte id) throws DBException;

    void delete(byte id) throws DBException;

    void update(FileExtension user) throws DBException;

    List<FileExtension> getAll() throws DBException;
}
