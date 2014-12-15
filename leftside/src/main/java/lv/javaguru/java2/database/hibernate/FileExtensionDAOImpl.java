package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.FileExtensionDAO;
import lv.javaguru.java2.domain.FileExtension;
import org.springframework.stereotype.Component;

/**
 * Created by SM on 12/6/2014.
 */
@Component("ORM_FileExtensionDAO")
public class FileExtensionDAOImpl extends DAOImpl<FileExtension> implements FileExtensionDAO {

}
