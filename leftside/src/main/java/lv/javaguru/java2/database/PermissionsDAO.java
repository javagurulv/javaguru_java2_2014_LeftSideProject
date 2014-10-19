package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Permissions;
import java.util.List;

/**
 * Created by Sergey on 19.10.14.
 */
public interface PermissionsDAO {

    void create(Permissions permissions) throws DBException;

    Permissions getById(Long id) throws DBException;

    void delete(Long id) throws DBException;

    void update(Permissions permissions) throws DBException;

    List<Permissions> getAll() throws DBException;
}
