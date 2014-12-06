package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FileDAO;
import lv.javaguru.java2.domain.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by SM on 12/6/2014.
 */
@Component("ORM_FileDAO")
public class FileDAOImpl implements FileDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(File file) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(file);
    }

    @Override
    public File getById(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return (File) session.get(File.class, id);
    }

    @Override
    public void delete(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        File file = new File();
        file.setFileId(id);
        session.delete(file);
    }

    @Override
    public void update(File file) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.update(file);
    }

    @Override
    public List<File> getAll() throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(File.class).list();
    }
}
