package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FileExtensionDAO;
import lv.javaguru.java2.domain.FileExtension;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by SM on 12/6/2014.
 */
@Component("ORM_FileExtensionDAO")
public class FileExtensionDAOImpl implements FileExtensionDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(FileExtension fileExtension) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(fileExtension);
    }

    @Override
    public FileExtension getById(byte id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return (FileExtension) session.get(FileExtension.class, id);
    }

    @Override
    public void delete(byte id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        FileExtension fileExtension = new FileExtension();
        fileExtension.setExtensionId(id);
        session.delete(fileExtension);
    }

    @Override
    public void update(FileExtension fileExtension) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.update(fileExtension);
    }

    @Override
    public List<FileExtension> getAll() throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(FileExtension.class).list();
    }
}
