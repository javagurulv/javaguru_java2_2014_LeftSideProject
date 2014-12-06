package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.FileDAO;
import lv.javaguru.java2.domain.File;
import org.springframework.stereotype.Component;

/**
 * Created by SM on 12/6/2014.
 */
@Component("ORM_FileDAO")
public class FileDAOImpl extends DAOImpl<File> implements FileDAO {
}
