package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Permission;
import java.util.List;

/**
 * Created by Sergey on 19.10.14.
 */
public interface PermissionsDAO {

    void create(Permission permission) throws DBException;

    Permission getById(Long id) throws DBException;

    void delete(Long id) throws DBException;

    void update(Permission permission) throws DBException;

    List<Permission> getAll() throws DBException;
}
