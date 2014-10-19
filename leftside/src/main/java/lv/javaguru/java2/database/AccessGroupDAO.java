package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.AccessGroup;
import java.util.List;

/**
 * Created by Sergey on 19.10.14.
 */
public interface AccessGroupDAO {

    void create(AccessGroup accessGroup) throws DBException;

    AccessGroup getById(Long id) throws DBException;

    void delete(Long id) throws DBException;

    void update(AccessGroup accessGroup) throws DBException;

    List<AccessGroup> getAll() throws DBException;
}
