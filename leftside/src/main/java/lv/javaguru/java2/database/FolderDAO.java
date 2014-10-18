package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Folder;

import java.util.List;

/**
 * Created by SM on 10/18/2014.
 */
public interface FolderDAO {

    void create(Folder user) throws DBException;

    Folder getById(Long id) throws DBException;

    void delete(Long id) throws DBException;

    void update(Folder user) throws DBException;

    List<Folder> getAll() throws DBException;

}
